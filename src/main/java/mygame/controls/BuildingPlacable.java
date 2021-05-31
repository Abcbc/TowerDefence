/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.input.InputManager;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import mygame.equipment.TestGun;
import mygame.factories.Pathfinding;
import utils.Pathfinder;

import java.io.IOException;

/**
 *
 * @author admin
 */
public class BuildingPlacable extends AbstractControl implements ActionListener {
    private final SimpleApplication app;
    private final InputManager inputManager;
    private final Camera cam;
    private final Pathfinder pathfinder = Pathfinding.create();
    String clippingLayer;
    public final String LEFT_MOUSE_BUTTON = "LeftMouseButton";
    String[] mappings = {LEFT_MOUSE_BUTTON};

    
    public BuildingPlacable(SimpleApplication app, String clippingLayer){
        this.app = app;
        this.cam = this.app.getCamera();
        this.clippingLayer = clippingLayer;
        this.inputManager = this.app.getInputManager();
        
        this.registerWithInput();
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        Vector3f origin    = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);
        Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
        direction.subtractLocal(origin).normalizeLocal();

        Ray ray = new Ray(origin, direction);
        CollisionResults results = new CollisionResults();
        app.getRootNode().collideWith(ray, results);
       
        Vector3f target = findGeometryHit(results, clippingLayer);
        this.spatial.setLocalTranslation(target);
    }
    
    Vector3f findGeometryHit(CollisionResults results, String name){
        for(int i =0; i< results.size(); i++){
            if(results.getCollision(i).getGeometry().getName().equals(name)){
                
                return results.getCollision(i).getContactPoint();
            }
        }

        return new Vector3f(10f,10f,10f);
    }
    
    @Override
    public void read(JmeImporter im) throws IOException {
        super.read(im);
        InputCapsule in = im.getCapsule(this);
        //TODO: load properties of this Control, e.g.
        //this.value = in.readFloat("name", defaultValue);
    }
    
    @Override
    public void write(JmeExporter ex) throws IOException {
        super.write(ex);
        OutputCapsule out = ex.getCapsule(this);
        //TODO: save properties of this Control, e.g.
        //out.write(this.value, "name", defaultValue);
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {}
    
    public void registerWithInput(){


        // both mouse and button - rotation of cam
        inputManager.addMapping(LEFT_MOUSE_BUTTON, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));

        inputManager.addListener(this, mappings);
        inputManager.setCursorVisible(true);


        }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
       if(name.equals(LEFT_MOUSE_BUTTON) && !isPressed && this.spatial != null){
           Vector3f pos = this.spatial.getLocalTranslation();
           pathfinder.setBlocker(pos, Float.MAX_VALUE);
           System.out.print("Set");
           this.spatial.addControl(new Shooting(app,5f, new TestGun(), pos));
           this.spatial.removeControl(this);


           
       }
           
    }
    
    
}
