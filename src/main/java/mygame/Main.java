package mygame;

import mygame.cams.StrategyCam;
import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import mygame.states.GameLoop;
import mygame.states.MainMenu;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    
    private StrategyCam sCam;
    
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
        
        this.getStateManager().attach(new GameLoop());
        this.getStateManager().attach(new MainMenu());
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
