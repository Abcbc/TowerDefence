package mygame.equipment;

public class TestGun implements Weapon{
    @Override
    public boolean isEmpty() {
        //System.out.println("CLICK.");
        return false;
    }
    @Override
    public void reload(float tpf) {
        //System.out.println("PIFF!");
    }
}
