package utils;

import com.jme3.math.Vector2f;

public class QuadTreeImpl<T> implements QuadTree {
    private static class Node<T>{
        private Node<T> parent;

        private Node<T> nw;
        private Node<T> ne;
        private Node<T> se;
        private Node<T> sw;

        private T value;

        private final float x;
        private final float y;
        private final Vector2f leftTop;


        public Node(Vector2f leftTop, float x, float y, Node<T> parent){
            this.parent = parent;
            this.leftTop = leftTop;
            this.x = x;
            this.y = y;

        }
        public Node(Vector2f center, float x, float y){
            this(center, x,y,null);
        }

        void createChildren(){
            float newX = this.x * 0.5f;
            float newY = this.x * 0.5f;

            Vector2f nwBase = new Vector2f(this.leftTop);
            Vector2f neBase = new Vector2f(this.leftTop.x +newX, this.leftTop.y);
            Vector2f seBase = new Vector2f(this.leftTop.x +newX, this.leftTop.y+newY);
            Vector2f swBase = new Vector2f(this.leftTop.x, this.leftTop.y+newY);

            this.nw = new Node<T>(nwBase, newX, newY, this);
            this.ne = new Node<T>(neBase, newX, newY, this);
            this.se = new Node<T>(seBase, newX, newY, this);
            this.sw = new Node<T>(swBase, newX, newY, this);
        }



    }

    private final Node<T> root;

    public QuadTreeImpl(Vector2f leftTop, float x, float y) {
        this.root = new Node(leftTop, x, y);
    }
}
