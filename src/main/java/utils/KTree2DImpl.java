package utils;

import com.jme3.math.Vector2f;
import utils.impls.RangeImpl;
import utils.spatial.Vector2i;

import java.util.List;

public class KTree2DImpl<T> implements KTree2D<T>{



    static public class Node<T>{
        private static int DIM = 3;
        private final static int NUM_OF_CHILDREN = DIM * DIM;
        private final Vector2f leftTop;
        private final float xDim;
        private final float yDim;

        private final Node<T> parent;

        private T value;

        private final Node<T>[] children = new Node[NUM_OF_CHILDREN];

        public Node(Vector2f leftTop, float xDim, float yDim, Node<T> parent){
            this.parent = parent;
            this.leftTop = leftTop;
            this.xDim = xDim;
            this.yDim = yDim;
        }
        private Node(Vector2f leftTop, float xDim, float yDim){
            this(leftTop, xDim, yDim, null);
        }
        void subdivide(){
            float xDelta = xDim/DIM;
            float yDelta = yDim/DIM;

            for(int i=0; i<DIM; i++){
                for(int j=0; j<DIM; j++){
                    int index = i * DIM + j;
                    float x = this.leftTop.x+i*xDelta;
                    float y = this.leftTop.y+j*yDelta;
                    Vector2f leftTop = new Vector2f(x, y);
                    children[index] = new Node<T>(leftTop, xDelta, yDelta, this);
                }
            }
        }

    }

    public KTree2DImpl(Vector2f leftTop, float xDim, float yDim, int dim){
        Node.DIM = dim;
        Node<T> node = new Node<>(leftTop, xDim, yDim);
        node.subdivide();
    }
    public KTree2DImpl(Vector2f leftTop, float xDim, float yDim){
        this(leftTop, xDim, yDim, 3);
    }
    @Override
    public List<T> inRange(RangeImpl range) {
        return null;
    }

    @Override
    public void insert(Vector2i pos) {

    }

    @Override
    public List<T> nearestNeighbor() {
        return null;
    }
}
