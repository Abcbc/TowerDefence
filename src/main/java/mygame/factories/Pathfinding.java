/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.factories;

import com.jme3.math.Vector3f;
import utils.Grid;
import utils.Pathfinder;

/**
 *
 * @author Melvyn
 */
public class Pathfinding {
    
    private final static Vector3f CENTER = Vector3f.ZERO;
    private final static int X = 80;
    private final static int Y = 80;
    private final static float DELTA = 4f;
    private final static Pathfinder PATHFINDER = Pathfinder.create(CENTER, X, Y, DELTA);
    
    public static Pathfinder create(){
       return PATHFINDER;
    }
    
    
}
