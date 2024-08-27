// src/Camera.java

public class Camera extends SmartDevice {
    public Camera(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return "Camera: " + getName() + ", Status: " + (isOn() ? "On" : "Off");
    }
}
