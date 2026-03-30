package Elevator.models;

public class Floor {
    private int floorNumber;
    private boolean underMaintenance;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.underMaintenance = false;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public void setMaintenance(boolean flag) {
        this.underMaintenance = flag;
    }
}