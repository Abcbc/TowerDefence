/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author Melvyn
 */
public class Index2D{
    public final int x;
    public final int y;
    
    public Index2D(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "Index2D("+x+" ,"+y+")";
    }
    
}
