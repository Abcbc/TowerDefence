/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.buildings;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import mygame.Controls.BuildingPlacable;
import mygame.factories.Pathfinding;
import utils.Grid;
import utils.Pathfinder;


/**
 *
 * @author admin
 */
public class Building{
    public final Geometry geometry;
    private final Pathfinder pathfinder = Pathfinding.create();
    public Building(SimpleApplication app){
        geometry =createBox(app.getAssetManager());
        geometry.setLocalTranslation(new Vector3f(10f,10f,10f));
        geometry.scale(0.1f,1f,0.1f);
        geometry.setName("Building");
        geometry.addControl(new BuildingPlacable(app, "Ground"));
        
        //geometry.getMaterial().setColor("Diffuse", new ColorRGBA(.2f, .9f, .9f, .9f));
        
        app.getRootNode().attachChild(geometry);
        
        
    }

    private Geometry createBox(AssetManager assetManager) {
       Box b = new Box(2, 2, 2);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        //mat.setColor("Diffuse", ColorRGBA.Blue);
        geom.setMaterial(mat);
        
        return geom;
    }
    
}
