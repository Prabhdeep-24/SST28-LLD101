package Elevator.buttons;

import Elevator.models.Elevator;

public class AlarmButton implements Button {

    private Elevator elevator;

    public AlarmButton(Elevator e) {
        this.elevator = e;
    }

    @Override
    public void press() {
        elevator.triggerAlarm();
    }
}
