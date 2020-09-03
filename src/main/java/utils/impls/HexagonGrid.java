package utils.impls;


import com.jme3.math.Vector3f;
import utils.Grid;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public abstract class HexagonGrid implements Grid {

    
    private static class Hexagon<T>{
        private Vector3f center = null;
        private T value = null;
        
        public Hexagon(Vector3f center){
            this.center = center;
        }
    }
    private Vector3f center;
    private int x;
    private int y;
    private float delta;
    private Hexagon[][] grid;
    
    public HexagonGrid(Vector3f center, int x, int y, float delta){
        this.center = center;
        this.x = x;
        this.y = y;
        this.delta = delta;
        this.grid = createGrid();
    }
    
    private Hexagon[][] createGrid() {
        Hexagon[][] result = new Hexagon[x][y];
        
        for(int i=0; i<y; i++){
            float currentY = i*delta-(y*delta/2);
            boolean isOdd = i%2==0; 
            for(int j=0; j<x; j++){
                float currentX = j*delta-(x*delta/2);
                if(isOdd){
                    currentX += delta*0.5;
                }
                Vector3f currentHexagon = new Vector3f(currentX, 0f, currentY);
                result[i][j] = new Hexagon(currentHexagon.add(center.divide(2)));
                System.out.println(result[i][j].center);
            }
        }
        
        return result;
    }
    public List<Vector3f> getVectors(){
        List<Vector3f> result = new ArrayList();
         for(int i=0; i<y; i++){
           
            for(int j=0; j<x; j++){
                result.add(grid[i][j].center);
            }
        }
        return result;
    }
    
}
