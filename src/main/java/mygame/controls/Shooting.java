/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controls;

import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;
import mygame.equipment.Weapon;

import java.io.IOException;

/**
 *
 * @author admin
 */
public class Shooting extends AbstractControl{
    private final SimpleApplication app;
    private final Weapon weapon;
    private final BoundingSphere range;


    public Shooting(SimpleApplication app, float range, Weapon weapon, Vector3f pos){
        this.app = app;
        this.weapon = weapon;
        this.range = new BoundingSphere(range, pos);

    }
    
    @Override
    protected void controlUpdate(float tpf) {
        if (weapon.isEmpty()){
            weapon.reload(tpf);
        }
        CollisionResults results = new CollisionResults();
        try {
            this.range.collideWith(app.getRootNode(), results);
            for(CollisionResult r: results){
                if("KillerBox".equals(r.getGeometry().getName())){
                    r.getGeometry().removeFromParent();
                    break;
                }
            }
        }
        catch (Exception e){
            //e.printStackTrace();
        }


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
}
