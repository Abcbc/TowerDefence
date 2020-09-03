package utils;

import com.jme3.math.Vector3f;
import utils.impls.PathfinderImpl;

public interface Pathfinder {
    public static Pathfinder create(Vector3f center, int x, int y, float delta){
        return new PathfinderImpl( center, x, y, delta);
    };

    Vector3f getDirection(Vector3f currentPosition, Vector3f target);

    void setBlocker(Vector3f pos, float costs);
}
