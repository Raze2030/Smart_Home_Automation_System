// src/SmartHomeGUI.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartHomeGUI extends JFrame {
    private User user;

    private DefaultListModel<String> deviceListModel;
    private JList<String> deviceList;
    private JButton addDeviceButton, removeDeviceButton, turnOnButton, turnOffButton, showStatusButton;

    public SmartHomeGUI(User user) {
        this.user = user;

        setTitle("Smart Home Automation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Device List
        deviceListModel = new DefaultListModel<>();
        deviceList = new JList<>(deviceListModel);
        JScrollPane scrollPane = new JScrollPane(deviceList);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));

        addDeviceButton = new JButton("Add Device");
        addDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDevice();
            }
        });

        removeDeviceButton = new JButton("Remove Device");
        removeDeviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDevice();
            }
        });

        turnOnButton = new JButton("Turn On");
        turnOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlDevice(true);
            }
        });

        turnOffButton = new JButton("Turn Off");
        turnOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlDevice(false);
            }
        });

        showStatusButton = new JButton("Show Status");
        showStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDeviceStatus();
            }
        });

        buttonPanel.add(addDeviceButton);
        buttonPanel.add(removeDeviceButton);
        buttonPanel.add(turnOnButton);
        buttonPanel.add(turnOffButton);
        buttonPanel.add(showStatusButton);

        add(buttonPanel, BorderLayout.EAST);
    }

    private void addDevice() {
        String[] options = {"Light", "Fan", "Camera", "Appliance"};
        String deviceType = (String) JOptionPane.showInputDialog(this, "Choose Device Type:",
                "Add Device", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (deviceType != null) {
            String deviceName = JOptionPane.showInputDialog(this, "Enter device name:");
            if (deviceName != null && !deviceName.isEmpty()) {
                SmartDevice device;
                switch (deviceType) {
                    case "Light":
                        device = new Light(deviceName);
                        break;
                    case "Fan":
                        device = new Fan(deviceName);
                        break;
                    case "Camera":
                        device = new Camera(deviceName);
                        break;
                    case "Appliance":
                        device = new Appliance(deviceName);
                        break;
                    default:
                        return;
                }
                user.addDevice(device);
                deviceListModel.addElement(device.getName());
            }
        }
    }

    private void removeDevice() {
        String selectedDevice = deviceList.getSelectedValue();
        if (selectedDevice != null) {
            user.removeDevice(selectedDevice);
            deviceListModel.removeElement(selectedDevice);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a device to remove.");
        }
    }

    private void controlDevice(boolean turnOn) {
        String selectedDevice = deviceList.getSelectedValue();
        if (selectedDevice != null) {
            SmartDevice device = user.getDevice(selectedDevice);
            if (turnOn) {
                device.turnOn();
            } else {
                device.turnOff();
            }
            JOptionPane.showMessageDialog(this, selectedDevice + " is now " + (turnOn ? "On" : "Off"));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a device to control.");
        }
    }

    private void showDeviceStatus() {
        String status = user.getDeviceStatus();
        JOptionPane.showMessageDialog(this, status);
    }

    public static void main(String[] args) {
        User user = new User("John Doe");
        SmartHomeGUI gui = new SmartHomeGUI(user);
        gui.setVisible(true);
    }
}
