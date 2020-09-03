package utils.impls;

import com.jme3.math.Vector3f;
import utils.Quad;

public class QuadImpl implements Quad {
    private final Vector3f center;
    private final float delta;
    private final float half_delta;
    
    private boolean touched = false;
    private Vector3f direction;
    private int targetDistance;
    private int originDistance;
    
    public QuadImpl(Vector3f center, float delta){
        this.delta = delta;
        this.half_delta = delta * 0.5f;
        this.center = center;
        this.targetDistance = Integer.MAX_VALUE;
    }
    
    @Override
    public Vector3f getCenter() {return center;}

    @Override
    public Vector3f getDirection() {return direction;}

    @Override
    public void setDirection(Vector3f direction){this.direction = direction;}

    @Override
    public float left() {return center.x-half_delta;}

    @Override
    public float right() {return center.x+half_delta;}

    @Override
    public float top() {return center.z+half_delta;}

    @Override
    public float bottom() {return center.z-half_delta;}

    @Override
    public int getTargetDistance() {return targetDistance;}

    @Override
    public void setTargetDistance(int distance){ this.targetDistance = distance;}

    @Override
    public int getOriginDistance() {return originDistance; }

    @Override
    public void setOriginDistance(int distance){this.originDistance = distance;}

    @Override
    public boolean isTouched() {return touched;}

    @Override
    public void touch() {touched = true;}

    @Override
    public void unTouch() {touched = false;}




}
