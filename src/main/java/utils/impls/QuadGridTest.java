package utils.impls;

import com.jme3.math.Vector3f;
import utils.Grid;

//import utils.spatial.PositionImpl;

class QuadGridTest {
    Grid g1 = Grid.create(new Vector3f(0f,0f,0f), 10, 10, 1f);
    @org.junit.jupiter.api.Test
    void getPosition() {
        Vector3f v = new Vector3f(0f,0f,0f);
        //PositionImpl pos = new PositionImpl(4,4);
        //assertEquals(g1.getPosition(v), pos);
    }
}