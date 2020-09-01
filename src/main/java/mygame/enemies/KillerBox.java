/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.enemies;

import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import mygame.Controls.Moveable;
import mygame.Controls.SimpleAI;
import mygame.Pathfinding;
import mygame.Utils;
import utils.Grid;

/**
 *
 * @author admin
 */
public class KillerBox {
    public final Geometry geometry;
    
    public KillerBox(SimpleApplication app, Vector3f position){
        geometry = Utils.createBox(app.getAssetManager());
        geometry.setLocalTranslation(position);
        geometry.scale(0.1f);
        
        geometry.addControl(new SimpleAI(Vector3f.ZERO, Pathfinding.create()));
        geometry.addControl(new Moveable(4.f));
        
        //geometry.getMaterial().setColor("Diffuse", new ColorRGBA(.2f, .9f, .9f, .9f));
        app.getRootNode().attachChild(geometry);
        
        
    }
    
}
