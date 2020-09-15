package utils.spatial;

public interface Vector2i {
    static Vector2i create(int x, int y){
        return new Vector2iImpl(x, y);
    }
    int getX();
    int getY();
    Vector2i add(Vector2i toAdd);
    Vector2i sub(Vector2i toSub);
    Vector2i mul(Vector2i toMul);
    Vector2i div(Vector2i toDiv);

    boolean inRange(Vector2i left, Vector2i right);
}
