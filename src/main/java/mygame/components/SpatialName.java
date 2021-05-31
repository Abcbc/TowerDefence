package mygame.components;

import com.simsilica.es.EntityComponent;

public class SpatialName implements EntityComponent {
    public String name;

    public SpatialName(String name){
        this.name = name;
    }
}
