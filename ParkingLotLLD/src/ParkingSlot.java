

public class ParkingSlot {
    private int id;
    private SlotType type;
    private boolean isOccupied;

    public ParkingSlot(int id, SlotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public int getId() {
        return id;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void occupy() {
        this.isOccupied = true;
    }

    public void vacate() {
        this.isOccupied = false;
    }
}