package mygame.components;

import com.simsilica.es.EntityComponent;

public class Health implements EntityComponent {
    public int hp;

    public Health(int hp){
        this.hp = hp;
    }

}
