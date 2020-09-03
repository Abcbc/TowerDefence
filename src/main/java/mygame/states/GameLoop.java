/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppStateManager;
import com.jme3.app.state.BaseAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial.CullHint;
import mygame.RingSpawn;
import utils.impls.Utils;

/**
 *
 * @author admin
 */
public class GameLoop extends BaseAppState{
   private SimpleApplication app;
   private AppStateManager stateManager;
   private RingSpawn spawner;
   private float currentTime = 0;
   private final float SPAWN_TIME = 3;
   
   @Override    
   public void update(float tpf) {
      currentTime+=tpf;
      if(currentTime>SPAWN_TIME){
          //spawner.spawn(500);
          currentTime = 0.f;
          //System.out.println("Spawn!");
      }
   }    
   

   @Override    
   public void initialize(Application app) {
       this.app = (SimpleApplication)app;
       this.stateManager = app.getStateManager();
       this.spawner = new RingSpawn(19.f, 60.f, Vector3f.ZERO, this.app);
       Geometry center = Utils.createBox(this.app.getAssetManager());
       center.setName("Center");
       this.app.getRootNode().attachChild(center);
       AmbientLight ambient = new AmbientLight();    
       this.app.getRootNode().addLight(ambient);    
       DirectionalLight sun = new DirectionalLight();   
       sun.setDirection(new Vector3f(1.4f, -1.4f, -1.4f));   
       this.app.getRootNode().addLight(sun); 
       this.app.getRootNode().attachChild(spawner);
       Geometry ground = Utils.createBox(this.app.getAssetManager());
       ground.scale(2000f, 0.01f, 20000f);
       ground.setName("Ground");
       ground.setCullHint(CullHint.Always);
       System.out.println(ground.getName());
       this.app.getRootNode().attachChild(ground);
       
   } 

    @Override
    protected void cleanup(Application app) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onEnable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void onDisable() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
