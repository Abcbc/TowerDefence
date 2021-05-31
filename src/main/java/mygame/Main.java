package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.simsilica.es.EntityData;
import com.simsilica.es.base.DefaultEntityData;
import mygame.cams.StrategyCam;
import mygame.ecs.Processors;
import mygame.processors.Movement;
import mygame.processors.SimpleAI;
import mygame.states.GameLoop;
import mygame.states.MainMenu;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    private StrategyCam sCam;
    public Processors processors;
    public EntityData data = new DefaultEntityData();
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        this.flyCam.setEnabled(false);
        this.cam.setLocation(new Vector3f(0f,8f,0f));
        this.cam.lookAt(new Vector3f(0f,0f,4f), cam.getUp());
        this.sCam = new StrategyCam(this.cam);
        this.sCam.registerWithInput(inputManager);

        this.processors = new Processors(data,this.rootNode);
        this.processors.add(new Movement());
        this.processors.add(new SimpleAI());
        this.getStateManager().attach(new GameLoop());
        this.getStateManager().attach(new MainMenu());
        RingSpawn ring = new RingSpawn(9.f, 60.f, Vector3f.ZERO, this);
        /*for(int i=0; i<4000; i++){
            String name = "KB"+ i;
            EntityId e = this.data.createEntity();
            KillerBox kb = new KillerBox(this, ring.getRandomPosition());
            kb.geometry.setName(name);
            this.data.setComponent(e, new Speed(5));
            this.data.setComponent(e, new Target(0f,0f,0f));
            Vector3f pos = kb.geometry.getLocalTranslation();
            this.data.setComponent(e, new Position(pos.x, pos.y, pos.z ));
            this.data.setComponent(e, new SpatialName(name));
        }*/

    }

    @Override
    public void simpleUpdate(float tpf) {
        this.processors.update(tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
