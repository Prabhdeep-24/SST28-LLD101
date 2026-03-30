package Elevator.system;

import Elevator.models.Elevator;

public class Operator {

    public void setMaintenance(Elevator e, boolean flag) {
        e.setMaintenance(flag);
    }

    public void resetEmergency(Elevator e) {
        e.resetEmergency();
    }
}