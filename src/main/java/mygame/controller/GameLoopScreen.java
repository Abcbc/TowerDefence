/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.controller;
import mygame.buildings.Building;
import com.jme3.app.SimpleApplication;
import de.lessvoid.nifty.screen.ScreenController;
import de.lessvoid.nifty.screen.Screen;

/**
 *
 * @author admin
 */
import de.lessvoid.nifty.Nifty;import mygame.RingSpawn;
import utils.Grid;
/**
 *
 * @author admin
 */
public class GameLoopScreen implements ScreenController{
    Nifty nifty;
    SimpleApplication app;
    RingSpawn spawner;
    Grid grid;
    
    public GameLoopScreen(Nifty nifty, SimpleApplication app){
        this.nifty = nifty;
        this.app = app;
        this.spawner = (RingSpawn)app.getRootNode().getChild("RingSpawn");
    }
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    public void quit(){
        nifty.gotoScreen("end");
    }
    public void spawn(){
      this.spawner.spawn(1000);
    }
    public void build(){
        Building building = new Building(app);
        System.out.println("Build");
    }
    
    
}
