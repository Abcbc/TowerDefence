package mygame.level;

import com.jme3.math.Vector3f;

public interface Level {
    static Level create(String type, Vector3f center){
        switch(type){
            case "main":
                return new MainLevelImpl(center);
        }
        return null;
    }
    void load();
}
