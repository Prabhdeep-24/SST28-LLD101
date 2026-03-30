package Elevator.models;

import Elevator.enums.*;
import java.util.*;

import Elevator.enums.DoorState;

import Elevator.enums.ElevatorState;

public class Elevator {

    private int id;
    private int currentFloor;
    private ElevatorState state;
    private DoorState doorState;
    private double currentWeight;
    private double maxWeight;
    private boolean emergency;
    private boolean maintenance;
    private ElevatorState lastDirection = ElevatorState.UP;

    private TreeSet<Integer> requests = new TreeSet<>();

    public Elevator(int id, int startFloor, double maxWeight) {
        this.id = id;
        this.currentFloor = startFloor;
        this.maxWeight = maxWeight;
        this.state = ElevatorState.IDLE;
        this.doorState = DoorState.CLOSED;
    }

    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorState getState() { return state; }

    public boolean canAcceptRequest() {
        return !maintenance && !emergency && currentWeight <= maxWeight;
    }

    public void requestFloor(int floor) {
        if (!canAcceptRequest()) {
            System.out.println("Elevator " + id + " unavailable.");
            return;
        }
        requests.add(floor);
        processRequests();
    }

    private void processRequests() {
        while (!requests.isEmpty() && canAcceptRequest()) {
            if (requests.contains(currentFloor)) {
                requests.remove(currentFloor);
                openDoor();
                continue;
            }

            Integer target = null;

            if (state == ElevatorState.IDLE) {
                if (lastDirection == ElevatorState.UP) {
                    if (requests.ceiling(currentFloor) != null) {
                        state = ElevatorState.UP;
                    } else {
                        state = ElevatorState.DOWN;
                    }
                } else {
                    if (requests.floor(currentFloor) != null) {
                        state = ElevatorState.DOWN;
                    } else {
                        state = ElevatorState.UP;
                    }
                }
            }

            if (state == ElevatorState.UP) {
                target = requests.ceiling(currentFloor);
                if (target == null) {
                    state = ElevatorState.DOWN;
                    lastDirection = ElevatorState.DOWN;
                    continue;
                }
                lastDirection = ElevatorState.UP;
            } else if (state == ElevatorState.DOWN) {
                target = requests.floor(currentFloor);
                if (target == null) {
                    state = ElevatorState.UP;
                    lastDirection = ElevatorState.UP;
                    continue;
                }
                lastDirection = ElevatorState.DOWN;
            }

            if (target != null) {
                closeDoor();
                System.out.println("Elevator " + id + " moving to " + target);
                currentFloor = target;
                requests.remove(target);
                openDoor();
            }
        }
        
        if (requests.isEmpty() && !maintenance && !emergency) {
            state = ElevatorState.IDLE;
        }
    }

    public void openDoor() {
        doorState = DoorState.OPEN;
        System.out.println("Elevator " + id + " door opened.");
    }

    public void closeDoor() {
        doorState = DoorState.CLOSED;
        System.out.println("Elevator " + id + " door closed.");
    }

    public void stop() {
        state = ElevatorState.IDLE;
        System.out.println("Elevator " + id + " stopped.");
    }

    public void triggerEmergency() {
        emergency = true;
        stop();
        openDoor();
        alarm();
    }

    public void triggerAlarm() {
        stop();
        alarm();
    }

    public void resetEmergency() {
        emergency = false;
    }

    public void setMaintenance(boolean flag) {
        maintenance = flag;
        state = flag ? ElevatorState.MAINTENANCE : ElevatorState.IDLE;
    }

    public void addWeight(double w) {
        currentWeight += w;
        if (currentWeight > maxWeight) {
            stop();
            openDoor();
            alarm();
        }
    }

    public void removeWeight(double w) {
        currentWeight -= w;
        if (currentWeight < 0) currentWeight = 0;
    }

    private void alarm() {
        System.out.println("ALARM: Elevator " + id);
    }
}