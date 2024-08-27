// src/User.java

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<SmartDevice> devices;

    public User(String name) {
        this.name = name;
        this.devices = new ArrayList<>();
    }

    public void addDevice(SmartDevice device) {
        devices.add(device);
    }

    public void removeDevice(String deviceName) {
        devices.removeIf(device -> device.getName().equalsIgnoreCase(deviceName));
    }

    public SmartDevice getDevice(String deviceName) {
        for (SmartDevice device : devices) {
            if (device.getName().equalsIgnoreCase(deviceName)) {
                return device;
            }
        }
        return null;
    }

    public String getDeviceStatus() {
        StringBuilder status = new StringBuilder("Device Status:\n");
        for (SmartDevice device : devices) {
            status.append(device.getStatus()).append("\n");
        }
        return status.toString();
    }
}
