// src/Fan.java

public class Fan extends SmartDevice {
    public Fan(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return "Fan: " + getName() + ", Status: " + (isOn() ? "On" : "Off");
    }
}
