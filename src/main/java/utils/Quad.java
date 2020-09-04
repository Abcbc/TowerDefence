package utils;

import com.jme3.math.Vector3f;
import utils.impls.QuadImpl;

public interface Quad {
    static Quad create(Vector3f center, float delta){return new QuadImpl(center, delta);}

    Vector3f getCenter();
    Vector3f getDirection();
    void setDirection(Vector3f direction);

    float left();
    float right();
    float top();
    float bottom();

    int getTargetDistance();
    void setTargetDistance(int distance);

    int getOriginDistance();
    void setOriginDistance(int distance);

    boolean isTouched();

    void touch();
    void unTouch();

    //int getScale();
    //void setScale(int scale);
}
