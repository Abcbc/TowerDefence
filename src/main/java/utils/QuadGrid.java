/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author admin
 */
public class QuadGrid implements Grid{
    private final float CORNER_MULTI = FastMath.sqrt(2f);
    private final Vector3f rightBottom;
    private final Vector3f leftTop;
    private final Vector3f center;
    private final int x;
    private final int y;
    private final float delta;
    private final Map<Vector3f, Quad[][]> cache = new HashMap();
    private final Map<Vector3f, Boolean> changed = new HashMap();
    private final Map<Index2D, Float> blocker = new HashMap();

    private Quad getNearest(List<Quad> neigbors) {
        int index = 0;
        float value = Float.MAX_VALUE; 
        for(int i=0; i < neigbors.size(); i++){
            Quad q = neigbors.get(i);
            if(value > q.value){
                index = i;
            } 
        }
        
        return neigbors.get(index);
    }

    private boolean isBlocked(Index2D i) {
        return blocker.containsKey(i);
    }

    private boolean hasChanged(Vector3f target) {
        if(changed.containsKey(target)){
            return changed.get(target);
        }
        return false;
    }

    
    private static class Quad{
        public Vector3f center = null; 
        public float value = Float.MAX_VALUE;
        public Vector3f direction = null;
        public boolean processed = false;
        
        public Quad(Vector3f center){
            this.center = center;
           
        }
        
    }
    
    public QuadGrid(Vector3f center, int x, int y, float delta){
        this.center = center;
        this.x = x;
        this.y = y;
        this.delta = delta;
        this.leftTop = new Vector3f(-(x*delta/2), 0f,-(y*delta/2));
        this.rightBottom = this.leftTop.negate();
    }
    private List<Quad> getNeigbors(Quad[][] grid, int x, int y) {
        int fromX = x-1;
        int fromY = y-1;
        int toX = x+1;
        int toY = y+1;
        
        List<Quad> result = new ArrayList();
        List<Index2D> indexes = new ArrayList();
        Index2D index = new Index2D(x,y);
        indexes.addAll(cleanList(getCorner(index)));
        indexes.addAll(cleanList(getSides(index)));
        for(Index2D i : indexes){
            result.add(grid[i.x][i.y]);
        }
        return result;
    }
    boolean validIndex(int x, int y){
        return x>0&&x<this.x && y>0&&y<this.y;
    }
    boolean validIndex(Index2D i){
        return this.validIndex((int)i.x, (int)i.y);
    }
    boolean indexEquals(int x1, int x2, int y1, int y2){
        return x1==x2&&y1==y2;
    }

    @Override
    public Vector3f getDirection(Vector3f currentPosition, Vector3f target) {
       Quad[][] grid = getGrid(target);
       Index2D pos = positionToIndex(currentPosition);
       if(!isInside(pos, grid)){
           return Vector3f.ZERO;
       }
       return grid[pos.x][pos.y].direction;
    }
    
    private void calculate(Quad[][] grid, Vector3f target) {
        List<Index2D> toProcess = new ArrayList();
        Index2D pos = positionToIndex(target); 
        Quad start = grid[pos.x][pos.y];
        start.value = 0f;
        toProcess.add(pos);
        //SET VALUES
        while(!toProcess.isEmpty()){
            Index2D curPos = toProcess.get(0);
            Quad curQuad = grid[curPos.x][curPos.y];
            float value = curQuad.value;
            if(!curQuad.processed){
               
            
            for(Index2D i : cleanList(getCorner(curPos))){
               Quad q = grid[i.x][i.y];
               if(isBlocked(i)){ q.value = this.blocker.get(i)+CORNER_MULTI;}
               else if(q.value > value+1f){q.value = value+CORNER_MULTI;}
               toProcess.add(i);
            }
            for(Index2D i : cleanList(getSides(curPos))){
               Quad q = grid[i.x][i.y];
               if(isBlocked(i)){ q.value = this.blocker.get(i);}
               else if(q.value > value+1f){q.value = value+1f;}
               toProcess.add(i);
               
            }
            
                curQuad.processed = true;
            }
            toProcess.remove(0);
        }
        
        //set blocker Values
        //TODO: What if the target is not in grid.
        
        //MAKE Directions
        calcDirections(grid);
        
    }
    
    private void calcDirections(Quad[][] grid){
        System.out.println("calculate");
        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                Quad q1 = grid[i][j];
                Quad q2 = getNearest(getNeigbors(grid, i, j));
                q1.direction = q2.center.subtract(q1.center);
            }
        }
        for(int i=0; i<x;i++){
            for(int j=0; j<y;j++){
                grid[i][j].processed = false;      
            }
        }
        System.out.println("calculate");
        
    }
    
    
    private Quad[][] getGrid(Vector3f target){
        if(!cache.containsKey(target) || hasChanged(target)){   
            System.out.println("getGrid");
            Quad[][] grid = createGrid();
            this.calculate(grid, target);
            cache.put(target, grid);
            changed.put(target, false);
        }
       
        return cache.get(target);
    }
   
    @Override
    public void setBlocker(Vector3f pos, float costs) {
        this.blocker.put(positionToIndex(pos), costs);
        this.changed.keySet().forEach((target) -> {
            this.changed.put(target, Boolean.TRUE);
        });
    }

    
    private Quad[][] createGrid() {
        Quad[][] result = new Quad[x][y];
        for(int j=0;j<y;j++){
            float curY = j*delta+delta*0.5f;
            for(int i=0; i<x;i++){
                float curX = i*delta+delta*0.5f;
                Vector3f v = new Vector3f(curX, 0f, curY);
                v = v.add(leftTop);
                result[i][j] = new Quad(v);
            }
        }
        return result;
    }
    
    private List<Index2D> getCorner(Index2D pos){
        List<Index2D> result = new ArrayList();
        result.add(new Index2D(pos.x-1, pos.y-1));
        result.add(new Index2D(pos.x+1, pos.y+1));
        result.add(new Index2D(pos.x-1, pos.y+1));
        result.add(new Index2D(pos.x+1, pos.y-1));
        
        return result;
    }
    
    private List<Index2D> getSides(Index2D pos){
        List<Index2D> result = new ArrayList();
        result.add(new Index2D(pos.x, pos.y-1));
        result.add(new Index2D(pos.x, pos.y+1));
        result.add(new Index2D(pos.x-1, pos.y));
        result.add(new Index2D(pos.x+1, pos.y));
        
        return result;
    }
    
    List<Index2D> cleanList(List<Index2D> l){
        List<Index2D> result = new ArrayList();
        for(Index2D i: l){
            if(validIndex(i)){
                result.add(i);
            }
        }
        return result;
    }

    
    private Index2D positionToIndex(Vector3f v){
        v = v.add(leftTop.negate());
        int posX = this.round(v.x/delta);
        int posY = this.round(v.z/delta);
        
        return new Index2D(posX,posY);
    }
    
    private boolean isInside(Index2D pos, Quad[][] grid){
        int xSize = grid.length-1;
        int ySize = grid[0].length-1;
        return inBetween(0, pos.x, xSize) && inBetween(0, pos.y, ySize);
        
    }
    
    private boolean inBetween(int left, int mid, int right){
        return mid>=left && mid<=right;
    }
    private int round(float f){
        return (int)FastMath.floor(f+delta*0.5f);
    }
    
    
    
}
