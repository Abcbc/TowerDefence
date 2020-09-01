/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author admin
 */
public class Utils {
    
    public static Geometry createBox(AssetManager assetManager){
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        //mat.setColor("Diffuse", ColorRGBA.Blue);
        geom.setMaterial(mat);
        
        return geom;
    }
    
}
