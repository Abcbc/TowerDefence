package mygame.processors;

import com.simsilica.es.EntitySet;
import mygame.components.Weapon;
import mygame.ecs.Processor;

public class Shooting extends Processor {
    @Override
    public void update(float tpf) {

        EntitySet shooter = this.data.getEntities(Weapon.class);

        shooter.applyChanges();

        shooter.parallelStream().forEach(
                entity -> {

                }
        );

    }

}
