package utils;


import com.jme3.math.Vector3f;
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
public interface Grid {
    public static Grid create(Vector3f center, int x, int y, float delta){
        return new QuadGrid( center, x, y, delta);
    };

    Vector3f getDirection(Vector3f currentPosition, Vector3f target);
    
    void setBlocker(Vector3f pos, float costs);
    
}
