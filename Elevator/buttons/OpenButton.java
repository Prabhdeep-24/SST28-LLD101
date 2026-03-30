package Elevator.buttons;

import Elevator.models.Elevator;

public class OpenButton implements Button {

    private Elevator elevator;

    public OpenButton(Elevator e) {
        this.elevator = e;
    }

    public void press() {
        elevator.openDoor();
    }
}