package factories;


import com.jme3.app.SimpleApplication;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Buldings {
    /*
        Building Types.
    */
    public final static String TEST_BUILDING = "TestBuilding";
    private static SimpleApplication app = null;
   
    public static Geometry create(String type, Vector3f position){
       
        switch(type){
            
            case TEST_BUILDING: return createTestBuilding(app, position);
           
        }
        return null;
        
    }
   

    private static Geometry createTestBuilding(SimpleApplication app, Vector3f position) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
