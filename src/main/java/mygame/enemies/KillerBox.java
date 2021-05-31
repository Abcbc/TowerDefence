/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.enemies;

import com.jme3.bounding.BoundingSphere;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.simsilica.es.EntityId;
import mygame.Main;
import mygame.components.*;
import utils.impls.Utils;

import java.util.UUID;

/**
 *
 * @author admin
 */
public class KillerBox {
    public final Geometry geometry;
    
    public KillerBox(Main app, Vector3f position){
        geometry = Utils.createBox(app.getAssetManager());
        geometry.setName(String.valueOf(UUID.randomUUID()));
        //Controls
        EntityId e = app.data.createEntity();
        app.data.setComponent(e, new Direction(0f,0f,0f));
        app.data.setComponent(e, new Position(position.x, position.y, position.z));
        app.data.setComponent(e, new Speed(2));
        app.data.setComponent(e, new SpatialName(geometry.getName()));
        app.data.setComponent(e, new Target(0f, 0f, 0f));
        geometry.getMesh().setBound(new BoundingSphere());

        //visuals
        //geometry.getMaterial().setColor("Diffuse", new ColorRGBA(.2f, .9f, .9f, .9f));
        geometry.setLocalTranslation(position);
        geometry.scale(0.1f);
        app.getRootNode().attachChild(geometry);
        
        
    }
    
}
