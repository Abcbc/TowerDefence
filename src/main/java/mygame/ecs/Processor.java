package mygame.ecs;

import com.jme3.scene.Node;
import com.simsilica.es.EntityData;
public abstract class Processor{
    protected EntityData data;
    protected Node root;

    public void init(EntityData data, Node root){
        this.data = data;
        this.root = root;
    }
    public abstract void update(float tpf);

}
