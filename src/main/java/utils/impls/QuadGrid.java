/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.impls;

import com.jme3.math.Vector3f;
import utils.Grid;
import utils.Quad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author admin
 */
public class QuadGrid implements Grid {
    private final Vector3f leftTop;
    private final Vector3f center;
    private final int x;
    private final int y;
    private final float delta;
    private final float halfDelta;

    private final Quad[][] grid;
    private final Position leftPos;
    private final Position rightPos;

    public Map<Position, Integer> test = new HashMap<>();

    public QuadGrid(Vector3f center, int x, int y, float delta){
        this.y = y;
        this.x = x;
        this.delta = delta;
        this.center = center;
        this.halfDelta = delta*0.5f;
        this.leftTop = new Vector3f(center.x-(x*halfDelta),0.f,center.z-(y*halfDelta));
        this.grid = createGrid();

        this.leftPos = new Position(0,0);
        this.rightPos = new Position(x-1, y-1);

    }

    private Quad[][] createGrid() {
        Quad[][] result = new Quad[x][y];

        for(int j=0;j<y;j++){
            float curY = j*delta+delta*0.5f;
            for(int i=0; i<x;i++){
                float curX = i*delta+halfDelta;
                Vector3f v = new Vector3f(curX, 0f, curY);
                v = v.add(leftTop);
                result[i][j] = Quad.create(v, delta);
            }
        }
        return result;
    }
    @Override
    public List<Quad> getNeighbors(Position pos) {

        return getQuads(getNeighborsPositions(pos));
    }

    @Override
    public List<Quad> getCornerNeighbor(Position from) { return getQuads(getCornerNeighborPositions(from)); }

    @Override
    public List<Position> getCornerNeighborPositions(Position from){
        int xs[] = {-1, 1};
        int ys[] = {-1, 1};

        return createPositions(xs, ys, from);
    }

    @Override
    public List<Quad> getSideNeighbor(Position from) {
        return getQuads(getSideNeighborPositions(from));
    }

    @Override
    public List<Position> getSideNeighborPositions(Position from){
        int xs[] = {0};
        int ys[] = {-1, 1};
        List<Position> positions = new ArrayList<>();

        positions.addAll(createPositions(xs, ys, from));
        positions.addAll(createPositions(ys, xs, from));

        return positions;

    }

    private List<Position> createPositions(int xs[], int[] ys, Position from){
        List<Position> result = new ArrayList<>();

        for(int x : xs){
            for(int y : ys){
                Position p = new Position(x,y).add(from);
                if(p.inRange(leftPos, rightPos)){
                    result.add(p);
                }
            }
        }

        return result;
    }

    @Override
    public Quad getQuad(Position pos) {
        return grid[pos.x][pos.y];
    }

    @Override
    public List<Quad> getQuads(List<Position> positions){
        List<Quad> quads = new ArrayList<>();

        for(Position position :  positions){
            quads.add(getQuad(position));
        }

        return quads;
    }

    @Override
    public Quad getQuad(Vector3f pos){
        return getQuad(getPosition(pos));
    }

    @Override
    public List<Position> getNeighborsPositions(Position from){
        List<Position> result = new ArrayList<>();

        result.addAll(this.getCornerNeighborPositions(from));
        result.addAll(this.getSideNeighborPositions(from));

        return result;
    }

    @Override
    public Position getPosition(Vector3f pos){
        System.out.println(pos);
        Vector3f indexVector = pos.subtract(leftTop);
        System.out.println(indexVector);
        ;
        Position result = new Position((int)(indexVector.x/delta), (int)(indexVector.z/delta));

        return result;
    }

    @Override
    public String toString(){
        String result = "Grid(";
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                result += grid[i][j]+" ";
            }
            result += "\n";
        }
        return result;
    }

}
