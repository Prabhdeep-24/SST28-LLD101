package Elevator.controllers;

import Elevator.models.*;
import Elevator.enums.*;
import java.util.*;

public class ElevatorController {

    private List<Elevator> elevators = new ArrayList<>();

    public void addElevator(Elevator e) {
        elevators.add(e);
    }

    public void requestElevator(int floor, Direction direction) {
        Elevator best = findBestElevator(floor);

        if (best == null) {
            System.out.println("No elevator available.");
            return;
        }

        System.out.println("Assigned Elevator " + best.getId());
        best.requestFloor(floor);
    }

    private Elevator findBestElevator(int floor) {
        Elevator best = null;
        int minDist = Integer.MAX_VALUE;

        for (Elevator e : elevators) {
            if (!e.canAcceptRequest()) continue;

            int dist = Math.abs(e.getCurrentFloor() - floor);
            if (dist < minDist) {
                minDist = dist;
                best = e;
            }
        }
        return best;
    }

    public void requestFromInside(int id, int floor) {
        for (Elevator e : elevators) {
            if (e.getId() == id) {
                e.requestFloor(floor);
                return;
            }
        }
    }
}