package utils;


import com.jme3.math.Vector3f;
import utils.impls.QuadGrid;
import utils.spatial.Vector2i;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public interface Grid {
    public static Grid create(Vector3f center, int x, int y, float delta){
        return new QuadGrid(center, x, y, delta);
    };

    List<Quad> getNeighbors(Vector2i from);
    List<Quad> getCornerNeighbor(Vector2i from);
    List<Quad> getSideNeighbor(Vector2i from);

    List<Vector2i> getNeighborsPositions(Vector2i from);
    List<Vector2i> getCornerNeighborPositions(Vector2i from);
    List<Vector2i> getSideNeighborPositions(Vector2i from);

    Quad getQuad(Vector2i pos);
    Quad getQuad(Vector3f pos);
    List<Quad> getQuads(List<Vector2i> pos);

    Vector2i getPosition(Vector3f pos);
}
