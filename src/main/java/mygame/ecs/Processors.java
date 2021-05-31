package mygame.ecs;

import com.jme3.scene.Node;
import com.simsilica.es.EntityData;

import java.util.LinkedList;
import java.util.List;

public class Processors {
    List<Processor> processorList = new LinkedList<>();
    protected EntityData data;
    protected Node root;

    public Processors(EntityData data, Node root){
        this.data = data;
        this.root = root;
    }
    public void add(Processor p){
        p.init(this.data,
                this.root);
        this.processorList.add(p);
    }
    public void update(float tpf){
        for(Processor p : processorList){
            p.update(tpf);
        }
    }

}
