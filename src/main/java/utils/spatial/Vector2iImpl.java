/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.spatial;

import java.util.Objects;

/**
 *
 * @author Melvyn
 */
public class Vector2iImpl implements Vector2i {
    public final int x;
    public final int y;
    
    public Vector2iImpl(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString(){
        return "Position("+x+" ,"+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2iImpl vec = (Vector2iImpl) o;
        return x == vec.x && y == vec.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Vector2i add(Vector2i toAdd) {
        return new Vector2iImpl(this.getX()+toAdd.getX(), this.getY()+toAdd.getY());
    }

    @Override
    public Vector2i sub(Vector2i toSub) {
        return new Vector2iImpl(this.getX()-toSub.getX(), this.getY()-toSub.getY());
    }

    @Override
    public Vector2i mul(Vector2i toMul) {
        return new Vector2iImpl(this.getX()*toMul.getX(), this.getY()*toMul.getY());
    }

    @Override
    public Vector2i div(Vector2i toDiv) {
        return new Vector2iImpl(this.getX()/toDiv.getX(), this.getY()/toDiv.getY());
    }

    @Override
    public boolean inRange(Vector2i left, Vector2i right) {
        return inRange(left.getX(), right.getX(), this.getX()) &&
                inRange(left.getY(), right.getY(), this.getY());
    }
    private boolean inRange(int left, int right, int candidate){
        return candidate >= left && candidate<= right;
    }
}
