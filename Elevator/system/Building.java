package Elevator.system;

import Elevator.controllers.ElevatorController;
import Elevator.models.Floor;
import java.util.*;

public class Building {

    private List<Floor> floors = new ArrayList<>();
    private ElevatorController controller = new ElevatorController();

    public ElevatorController getController() {
        return controller;
    }

    public void addFloor(int floor) {
        floors.add(new Floor(floor));
    }
}