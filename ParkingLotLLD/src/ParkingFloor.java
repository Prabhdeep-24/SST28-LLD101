

import java.util.List;

public class ParkingFloor {
    private int id;
    private List<ParkingSlot> slots;

    public ParkingFloor(int id, List<ParkingSlot> slots) {
        this.id = id;
        this.slots = slots;
    }

    public int getId() {
        return id;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }
}