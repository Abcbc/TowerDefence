package mygame.processors;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.es.EntitySet;
import mygame.components.Position;
import mygame.components.SpatialName;
import mygame.components.Speed;
import mygame.components.Target;
import mygame.ecs.Processor;

public class Movement extends Processor {
    @Override
    public void update(float tpf) {
        EntitySet entities = this.data.getEntities(Position.class, Target.class, Speed.class, SpatialName.class);
        entities.stream().parallel().forEach(
                entity -> {
                    Target t = entity.get(Target.class);
                    Position pos = entity.get(Position.class);
                    float speed = entity.get(Speed.class).mps;
                    Vector3f curPos = new Vector3f(pos.x, pos.y, pos.z);
                    Vector3f nextTarget = new Vector3f(t.x, t.y, t.z);
                    Spatial spatial = this.root.getChild(entity.get(SpatialName.class).name);
                    spatial.lookAt(nextTarget, Vector3f.UNIT_Y);
                    float distance = curPos.distance(nextTarget);

                    float progress = speed/distance;
                    Vector3f newPos = FastMath.interpolateLinear(progress*tpf,curPos, nextTarget);
                    spatial.setLocalTranslation(newPos);
                    pos.x = newPos.x;
                    pos.y = newPos.y;
                    pos.z = newPos.z;


                }
        );
    }
}
