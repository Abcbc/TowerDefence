/*
 * Copyright (c) 2009-2017 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package cams;

import com.jme3.collision.MotionAllowedListener;
import com.jme3.input.CameraInput;
import com.jme3.input.FlyByCamera;
import com.jme3.input.InputManager;
import com.jme3.input.Joystick;
import com.jme3.input.JoystickAxis;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 * A first-person camera controller.
 *
 * After creation, you (or FlyCamAppState) must register the controller using
 * {@link #registerWithInput(InputManager)}.
 *
 * Controls:
 *  - Move (or, in drag-to-rotate mode, drag) the mouse to rotate the camera
 *  - Mouse wheel for zooming in or out
 *  - WASD keys for moving forward/backward and strafing
 *  - QZ keys raise or lower the camera
 */
public class StrategyCam implements ActionListener, AnalogListener{
    
    private final String LEFT = "StrategyCamLeft";
    private final String RIGHT = "StrategyCamRight";
    private final String UP = "StrategyCamUp";
    private final String DOWN = "StrategyCamDown";
    
    private final String ZOOM_IN = "StrategyCamZoomIn";
    private final String ZOOM_OUT = "StrategyCamZoomOut";
           
    
    private final String[] mappings = {
        LEFT, RIGHT, UP, DOWN, ZOOM_IN, ZOOM_OUT
    };
    private final Camera cam;
    private float strafeSpeed = 5f;
    private float zoomSpeed = 2f;
    private InputManager inputManager;
    
    public StrategyCam(Camera cam){
        this.cam = cam;
    }
    
    public void setSpeed(float speed){
        this.strafeSpeed = speed;
    }
    
    public float getSpeed(float speed){
        return this.strafeSpeed;
    }
    
    public void registerWithInput(InputManager inputManager){
        this.inputManager = inputManager;

        // both mouse and button - rotation of cam
        inputManager.addMapping(LEFT, new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping(RIGHT, new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping(UP, new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping(DOWN, new KeyTrigger(KeyInput.KEY_S));

        // mouse only - zoom in/out with wheel
        inputManager.addMapping(ZOOM_IN, new MouseAxisTrigger(MouseInput.AXIS_WHEEL, false));
        inputManager.addMapping(ZOOM_OUT, new MouseAxisTrigger(MouseInput.AXIS_WHEEL, true));
        
        inputManager.addListener(this, mappings);
        inputManager.setCursorVisible(true);
    }
    
    void zoom(float value){
        Vector3f rel = new Vector3f(0f, value, 0f);
        Vector3f pos = cam.getLocation().clone();
        Vector3f origin    = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.0f);
        Vector3f direction = cam.getWorldCoordinates(inputManager.getCursorPosition(), 0.3f);
        
        direction = direction.subtract(origin).mult(value);

        cam.setLocation(pos.add(direction));
    }
        
    
    void move(float x, float z){
        Vector3f rel = new Vector3f(x, 0f, z);
        Vector3f pos = cam.getLocation().clone();
        
        cam.setLocation(pos.add(rel));
    }
    @Override
    public void onAction(String name, boolean isPressed, float tpf) {

    }
    private float calcZoomSpeed(){
        return zoomSpeed*cam.getLocation().y*0.1f+0.2f;
    }
    private float calcStrafeSpeed(){
        return strafeSpeed*cam.getLocation().y*0.1f;
    }
    @Override
    public void onAnalog(String name, float value, float tpf) {
        
        if(name.equals(LEFT)){
            move(value*calcStrafeSpeed(),0f);  
        }
        else if(name.equals(RIGHT)){
            move(-value*calcStrafeSpeed(),0f);
        }
        else if(name.equals(UP)){
            move(0f, value*calcStrafeSpeed());
        }
        else if(name.equals(DOWN)){    
            move(0f, -value*calcStrafeSpeed());
        }
        else if(name.equals(ZOOM_IN)){
            zoom(value*calcZoomSpeed());
        }
        else if(name.equals(ZOOM_OUT)){
            zoom(-value*calcZoomSpeed());
        }
    }
}
    

