package utils.impls;

import com.jme3.math.Vector3f;
import utils.Grid;
import utils.Pathfinder;
import utils.Quad;

import java.util.*;

public class PathfinderImpl implements Pathfinder {
    private final int CORNER_DISTANCE = 14;
    private final int SIDE_DISTANCE = 10;

    private final Vector3f rightBottom;
    private final Vector3f leftTop;
    private final Vector3f center;
    private final int x;
    private final int y;
    private final float delta;

    private final Grid gridScheme;
    private final Map<Vector3f, Grid> cache = new HashMap<>();
    private final Map<Position, Float> blocker = new HashMap<>();

    public PathfinderImpl(Vector3f center, int x, int y, float delta){
        this.center = center;
        this.x = x;
        this.y = y;
        this.delta = delta;
        this.leftTop = new Vector3f(-(x*delta/2), 0f,-(y*delta/2));
        this.gridScheme = Grid.create(center,x,y,delta);
        this.rightBottom = this.leftTop.negate(); //NOT SURE
    }


    @Override
    public Vector3f getDirection(Vector3f currentPosition, Vector3f target) {
        Grid grid;
        if(!cache.containsKey(target)){
            grid = Grid.create(center,x,y,delta);
            calculate(grid, target);
            cache.put(target, grid);
        }

        return cache.get(target).getQuad(currentPosition).getDirection();
    }

    private void calculate(Grid grid, Vector3f target) {

        createHeatmap(grid, target);
        for(Map.Entry<Position, Float> e :blocker.entrySet()){
            grid.getQuad(e.getKey()).setTargetDistance(Integer.MAX_VALUE);
        }
        createFlowField(grid);
    }

    private void createHeatmap(Grid grid, Vector3f target) {
        List<Position> toProcess = new ArrayList<>();

        Position targetPos = grid.getPosition(target);
        Quad targetQuad = grid.getQuad(targetPos);
        targetQuad.setTargetDistance(0);
        toProcess.add(targetPos);

        while(!toProcess.isEmpty()){
            Position curPos = toProcess.get(0);
            Quad curQuad = grid.getQuad(curPos);
            int cornerValue = curQuad.getTargetDistance() + CORNER_DISTANCE;
            int sideValue = curQuad.getTargetDistance() + SIDE_DISTANCE;

            toProcess.addAll(processQuads(cornerValue, grid, grid.getCornerNeighborPositions(curPos)));
            toProcess.addAll(processQuads(sideValue, grid, grid.getSideNeighborPositions(curPos)));

            curQuad.touch();
            toProcess.remove(0);
        }
    }

    private void createFlowField(Grid grid) {
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                Position pos = new Position(i,j);
                Quad q = grid.getQuad(pos);
                Quad sq = smallestQuad(grid.getNeighbors(pos));
                q.setDirection(sq.getCenter().subtract(q.getCenter()));

                q.unTouch();
            }
        }
    }

    private Quad smallestQuad(List<Quad> neighbors) {
        Quad candidate = neighbors.get(0);
        neighbors.remove(0);

        for(Quad q : neighbors){
            if(q.getTargetDistance() <= candidate.getTargetDistance()){
                candidate = q;
            }
        }

        return candidate;
    }

    private List<Position> processQuads(int value, Grid grid, List<Position> positions){
        List<Position> result = new ArrayList<>();

        for(Position pos : positions){
            Quad q = grid.getQuad(pos);
            if(q.getTargetDistance()> value) {
                q.setTargetDistance(value);
                if(!q.isTouched()){
                    result.add(pos);
                }
            }
        }

        return result;
    }

    @Override
    public void setBlocker(Vector3f pos, float costs) {
        blocker.put(gridScheme.getPosition(pos), costs);
        cache.clear();

    }
}
