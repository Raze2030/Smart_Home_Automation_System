// src/Light.java

public class Light extends SmartDevice {
    public Light(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return "Light: " + getName() + ", Status: " + (isOn() ? "On" : "Off");
    }
}
