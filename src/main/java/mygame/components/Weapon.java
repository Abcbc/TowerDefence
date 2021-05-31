package mygame.components;

import com.simsilica.es.EntityComponent;

public class Weapon implements EntityComponent {
    public float range;
    public float damage;
    public float reloadTime;

    public Weapon(float range, float reloadTime, float damage){
        this.range = range;
        this.damage = damage;
        this.reloadTime = reloadTime;
    }
}
