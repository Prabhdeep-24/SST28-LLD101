package Elevator.buttons;

import Elevator.models.Elevator;

public class InsideButton implements Button {

    private int destination;
    private Elevator elevator;

    public InsideButton(int destination, Elevator elevator) {
        this.destination = destination;
        this.elevator = elevator;
    }

    @Override
    public void press() {
        elevator.requestFloor(destination);
    }
}