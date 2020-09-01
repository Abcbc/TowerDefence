package utils;


import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class PathFindingPlane extends Node{
    
    private Vector3f center;
    private float[][] grid;
    private float delta;
    public PathFindingPlane(Vector3f center, int sizeX, int sizeY, float delta){
        
        this.delta = delta;
        this.center = center;
        this.grid = new float[sizeX][sizeY];
        
    }
    
}
