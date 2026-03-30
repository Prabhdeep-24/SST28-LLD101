public class Ticket {
    private Vehicle vehicle;
    private ParkingSlot slot;
    private long entryTime;

    public Ticket(Vehicle vehicle, ParkingSlot slot, long entryTime) {
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryTime = entryTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSlot getSlot() {
        return slot;
    }

    public long getEntryTime() {
        return entryTime;
    }
}