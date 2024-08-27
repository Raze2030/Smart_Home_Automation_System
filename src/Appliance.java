// src/Appliance.java

public class Appliance extends SmartDevice {
    public Appliance(String name) {
        super(name);
    }

    @Override
    public String getStatus() {
        return "Appliance: " + getName() + ", Status: " + (isOn() ? "On" : "Off");
    }
}
