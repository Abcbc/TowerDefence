/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.ecs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public class Result implements Iterator{
    Map<Class<?>, List<Object>> components;
    
    public Result(){
        components = new HashMap();
    }
    
    public <T extends Component> void set(Class<T> c, List<T> components){
        
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object next() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //public <T extends Component> T get
    
    
}
