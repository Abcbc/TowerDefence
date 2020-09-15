package utils;

import utils.impls.RangeImpl;
import utils.spatial.Vector2i;

import java.util.List;

public interface KTree2D<T> {
    List<T> inRange(RangeImpl range);
    void insert(Vector2i pos);
    List<T> nearestNeighbor();
}
