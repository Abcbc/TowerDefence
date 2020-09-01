/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;
import utils.Grid;

/**
 *
 * @author Melvyn
 */
public class Pathfinding {
    
    private final static Vector3f CENTER = Vector3f.ZERO;
    private final static int X = 250;
    private final static int Y = 250;
    private final static float DELTA = 0.5f;
    private final static Grid GRID = Grid.create(CENTER, X, Y, DELTA);
    
    public static Grid create(){
       return GRID;
    }
    
    
}
