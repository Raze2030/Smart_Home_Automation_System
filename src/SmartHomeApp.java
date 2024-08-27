// src/SmartHomeApp.java

import java.util.Scanner;

public class SmartHomeApp {
    private static User user = new User("John Doe");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Smart Home Automation System");
            System.out.println(" 1. Add Device");
            System.out.println(" 2. Remove Device");
            System.out.println(" 3. Turn Device On");
            System.out.println(" 4. Turn Device Off");
            System.out.println(" 5. Show Device Status");
            System.out.println(" 6. Exit\n");
            System.out.print("Choose an option : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addDevice(scanner);
                    break;
                case 2:
                    removeDevice(scanner);
                    break;
                case 3:
                    controlDevice(scanner, true);
                    break;
                case 4:
                    controlDevice(scanner, false);
                    break;
                case 5:
                    showDeviceStatus();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addDevice(Scanner scanner) {
        System.out.println("Choose Device Type: ");
        System.out.println(" 1. Light");
        System.out.println(" 2. Fan");
        System.out.println(" 3. Camera");
        System.out.println(" 4. Appliance");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter device name: ");
        String name = scanner.nextLine();

        SmartDevice device = null;
        switch (type) {
            case 1:
                device = new Light(name);
                break;
            case 2:
                device = new Fan(name);
                break;
            case 3:
                device = new Camera(name);
                break;
            case 4:
                device = new Appliance(name);
                break;
            default:
                System.out.println("Invalid device type.");
                return;
        }

        user.addDevice(device);
        System.out.println("Added " + device.getClass().getSimpleName() + ": " + device.getName()+"\n");
    }

    private static void removeDevice(Scanner scanner) {
        System.out.print("Enter device name to remove: ");
        String name = scanner.nextLine();
        user.removeDevice(name);
        System.out.println("Removed device: " + name+"\n");
    }

    private static void controlDevice(Scanner scanner, boolean turnOn) {
        System.out.print("Enter device name to " + (turnOn ? "turn on: " : "turn off: "));
        String name = scanner.nextLine();
        SmartDevice device = user.getDevice(name);
        if (device != null) {
            if (turnOn) {
                device.turnOn();
                System.out.println("Turned on " + device.getName()+"\n");
            } else {
                device.turnOff();
                System.out.println("Turned off " + device.getName()+"\n"+"\n");
            }
        } else {
            System.out.println("Device not found.");
        }
    }

    private static void showDeviceStatus() {
        System.out.println(user.getDeviceStatus());
    }
}
