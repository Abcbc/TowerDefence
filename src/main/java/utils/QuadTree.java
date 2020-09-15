package utils;

import com.jme3.math.Vector2f;

public interface QuadTree<T> {
    static <T> QuadTree<T> create(Vector2f center, float x, float y){
        return new QuadTreeImpl(center, x, y);
    }
}
