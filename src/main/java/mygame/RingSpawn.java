/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.Random;
import mygame.enemies.KillerBox;
import utils.Grid;

/**
 *
 * @author admin
 */
public class RingSpawn extends Node {
   
    private final float outerRadius;
    private final float innerRadius;
    private final float radius;
    private final Vector3f center;
    private final Random random;
    private final SimpleApplication app;
    
    public RingSpawn(float outerRadius, float innerRadius, Vector3f center, 
            SimpleApplication app){
        super.setName("RingSpawn");
        this.app = app;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.radius = outerRadius-innerRadius;
        this.center = center;
        this.random = new Random();

    }
    
    public void spawn(int num){
        for(int i = 0; i< num; i++){
            spawn();
        }
    }
    void spawn(){
        KillerBox kb = new KillerBox(this.app, getRandomPosition());   
    }
    
    private Vector3f getRandomPosition(){
        float angle = (float)  (FastMath.DEG_TO_RAD * random.nextFloat() * 360.);
        float distance = (float) (random.nextFloat() * this.radius + this.innerRadius);
        float x  = FastMath.cos(angle)*distance;
        float y = FastMath.sin(angle)*distance;
        
        Vector3f pos =  new Vector3f(x, 0.f, y);
           
        return this.center.add(pos);  
        }


}
