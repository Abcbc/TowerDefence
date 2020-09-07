/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.enemies;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import mygame.Controls.Moveable;
import mygame.Controls.SimpleAI;
import mygame.factories.Pathfinding;
import utils.impls.Utils;

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
        //Controls
        geometry.addControl(new SimpleAI(Vector3f.ZERO));
        geometry.addControl(new Moveable(4.f));
        geometry.setName("KillerBox");
        geometry.getMesh().setBound(new BoundingSphere());

        //visuals
        //geometry.getMaterial().setColor("Diffuse", new ColorRGBA(.2f, .9f, .9f, .9f));
        app.getRootNode().attachChild(geometry);
        
        
    }
    
}
