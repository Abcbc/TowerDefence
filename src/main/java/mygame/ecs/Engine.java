/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.ecs;

import java.util.List;

/**
 *
 * @author admin
 */
public interface Engine {
    
    <T extends Component> T getComponent(Class<T> c);
    <T extends Component> Result getComponents(List<Class<T>> cs);

 

    
    
}
