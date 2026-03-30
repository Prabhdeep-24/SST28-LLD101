public class EntryGate {

    private int id;
    private ParkingLot lot;

    public EntryGate(int id, ParkingLot lot) {
        this.id = id;
        this.lot = lot;
    }

    public int getId() {
        return id;
    }

    public ParkingSlot assignSlot(Vehicle vehicle) {
        return lot.getAvailableSlot(vehicle.getType());
    }
}