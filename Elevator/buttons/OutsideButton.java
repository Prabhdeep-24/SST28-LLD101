package Elevator.buttons;

import Elevator.controllers.ElevatorController;
import Elevator.enums.Direction;

public class OutsideButton implements Button {

    private int floor;
    private Direction direction;
    private ElevatorController controller;

    public OutsideButton(int floor, Direction direction, ElevatorController controller) {
        this.floor = floor;
        this.direction = direction;
        this.controller = controller;
    }

    @Override
    public void press() {
        controller.requestElevator(floor, direction);
    }
}