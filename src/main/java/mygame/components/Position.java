package mygame.components;

import com.simsilica.es.EntityComponent;

public class Position implements EntityComponent{
    public float x;
    public float y;
    public float z;

    public Position(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


}
