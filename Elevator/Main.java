package Elevator;

import Elevator.models.*;
import Elevator.controllers.*;
import Elevator.panels.*;

public class Main {
    public static void main(String[] args) {

        ElevatorController controller = new ElevatorController();

        Elevator e1 = new Elevator(1, 0, 700);
        Elevator e2 = new Elevator(2, 5, 800);

        controller.addElevator(e1);
        controller.addElevator(e2);

        FloorPanel floor0 = new FloorPanel(0, controller);

        // Outside request
        floor0.pressUp();

        // Inside request
        CarPanel panel = new CarPanel(e1, 10);
        panel.pressFloor(5);

        // Weight Limit Check
        System.out.println("\n--- Adding Weight Test ---");
        e1.addWeight(800); // Exceeds 700kg limit

        System.out.println("\n--- Trying to Request Floor when Overweight ---");
        panel.pressFloor(6); // Should not accept request

        System.out.println("\n--- Removing Weight ---");
        e1.removeWeight(200); // Down to 600kg, below limit

        System.out.println("\n--- Requesting Floor again ---");
        panel.pressFloor(6); // Now it should move

        System.out.println("\n--- Testing Alarm Button ---");
        panel.pressAlarm(); // Stops and alarms
    }
}