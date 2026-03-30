package Elevator.buttons;

import Elevator.models.Elevator;

public class CloseButton implements Button {

    private Elevator elevator;

    public CloseButton(Elevator e) {
        this.elevator = e;
    }

    public void press() {
        elevator.closeDoor();
    }
}