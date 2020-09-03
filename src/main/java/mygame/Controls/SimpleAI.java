/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Controls;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import mygame.factories.Pathfinding;
import utils.Pathfinder;

import java.io.IOException;


/**
 *
 * @author admin
 */
public class SimpleAI extends AbstractControl {
    //Any local variables should be encapsulated by getters/setters so they
    //appear in the SDK properties window and can be edited.
    //Right-click a local variable to encapsulate it with getters and setters.
    
    Pathfinder pathfinder = Pathfinding.create();
    Vector3f finalTarget;
    
    public SimpleAI(Vector3f target){
        this.finalTarget = target;
    }
    
    @Override
    protected void controlUpdate(float tpf) {
        
        Vector3f curPos = this.spatial.getLocalTranslation();
        Vector3f direction = pathfinder.getDirection(curPos, this.finalTarget);


        
        Vector3f nextTarget = direction.add(curPos);


        float distance = curPos.distance(finalTarget);
       
        spatial.setUserData("target", nextTarget);

        if(distance<=2.f){
            this.spatial.removeFromParent();
        }
       
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
    protected void controlRender(RenderManager rm, ViewPort vp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
