/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.impls;

import java.util.Objects;

/**
 *
 * @author Melvyn
 */
public class Position {
    public final int x;
    public final int y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    Position add(Position pos){return new Position(x+pos.x, y+pos.y); }

    Position sub(Position pos){return new Position(x-pos.x, y-pos.y);}

    boolean inRange(Position left, Position right){
        return left.x <= this.x && left.y <=this.y &&
                right.x >= this.x && right.y >= this.y;
    }
    @Override
    public String toString(){
        return "Position("+x+" ,"+y+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
