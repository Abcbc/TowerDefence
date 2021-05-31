package mygame.processors;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntitySet;
import mygame.components.Position;
import mygame.components.Target;
import mygame.ecs.Processor;
import mygame.factories.Pathfinding;
import utils.Pathfinder;

public class SimpleAI extends Processor {
    private final Vector3f finalTarget;
    Pathfinder pathfinder = Pathfinding.create();

    public SimpleAI(){
        this.finalTarget = new Vector3f(0f,0f,0f);
    }
    @Override
    public void update(float tp) {
        EntitySet entities = this.data.getEntities(Position.class, Target.class);
        entities.stream().parallel().forEach(
                entity -> {
                    Position pos = entity.get(Position.class);
                    Vector3f curPos = new Vector3f(pos.x, pos.y, pos.z);
                    Vector3f direction = pathfinder.getDirection(curPos, this.finalTarget);
                    Vector3f nextTarget = direction.add(curPos);
                    float distance = curPos.distance(finalTarget);

                    Target t = entity.get(Target.class);
                    t.x = nextTarget.x;
                    t.y = nextTarget.y;
                    t.z = nextTarget.z;

                    if(distance<=2.f){
                        //this.spatial.removeFromParent();
                    }
                }
        );
    }
}
