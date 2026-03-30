package Elevator.buttons;

import Elevator.models.Elevator;

public class EmergencyButton implements Button {

    private Elevator elevator;

    public EmergencyButton(Elevator e) {
        this.elevator = e;
    }

    public void press() {
        elevator.triggerEmergency();
    }
}