package utils;


import com.jme3.math.Vector3f;
import utils.impls.Position;
import utils.impls.QuadGrid;

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

    List<Quad> getNeighbors(Position from);
    List<Quad> getCornerNeighbor(Position from);
    List<Quad> getSideNeighbor(Position from);

    List<Position> getNeighborsPositions(Position from);
    List<Position> getCornerNeighborPositions(Position from);
    List<Position> getSideNeighborPositions(Position from);

    Quad getQuad(Position pos);
    Quad getQuad(Vector3f pos);
    List<Quad> getQuads(List<Position> pos);

    Position getPosition(Vector3f pos);
}
