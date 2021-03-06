/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.states;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import de.lessvoid.nifty.Nifty;
import com.jme3.niftygui.NiftyJmeDisplay;
import mygame.controller.GameLoopScreen;

/**
 *  
 *  
 *
 * @author admin 
 */
public class MainMenu extends BaseAppState {
    Nifty nifty;
    
    
    @Override
    protected void initialize(Application app) {
        
        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
                app.getAssetManager(),
                app.getInputManager(),
                app.getAudioRenderer(),
                app.getGuiViewPort());
        nifty = niftyDisplay.getNifty();
        
        nifty.fromXml("Interface/HalloWelt.xml", "start", new GameLoopScreen(nifty, (SimpleApplication)app));

        // attach the nifty display to the gui view port as a processor
        app.getGuiViewPort().addProcessor(niftyDisplay);

        // disable the fly cam
//        flyCam.setEnabled(false);
//        flyCam.setDragToRotate(true);
        app.getInputManager().setCursorVisible(true);
        
        //It is technically safe to do all initialization and cleanup in the         
        //onEnable()/onDisable() methods. Choosing to use initialize() and         
        //cleanup() for this is a matter of performance specifics for the         
        //implementor.        
        //TODO: initialize your AppState, e.g. attach spatials to rootNode    
    }
    @Override
    protected void cleanup(Application app) {
        //TODO: clean up what you initialized in the initialize method,        
        //e.g. remove all spatials from rootNode    
    }
    //onEnable()/onDisable() can be used for managing things that should     
    //only exist while the state is enabled. Prime examples would be scene     
    //graph attachment or input listener attachment.    
    @Override
    protected void onEnable() {
        //Called when the state is fully enabled, ie: is attached and         
        //isEnabled() is true or when the setEnabled() status changes after the         
        //state is attached.    
    }
    
    @Override
    protected void onDisable() {
        //Called when the state was previously enabled but is now disabled         
        //either because setEnabled(false) was called or the state is being         
        //cleaned up.    
    }
    @Override
    public void update(float tpf) {
        //TODO: implement behavior during runtime    
    }
}
