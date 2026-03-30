import java.util.*;

public class ParkingLot {

    private Map<Integer, EntryGate> gates = new HashMap<>();
    private List<ParkingFloor> floors = new ArrayList<>();
    private SlotAssignmentStrategy slotStrategy;
    private PricingStrategy pricingStrategy;

    public ParkingLot(SlotAssignmentStrategy slotStrategy, PricingStrategy pricingStrategy) {
        this.slotStrategy = slotStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void addGate(int id, EntryGate gate) {
        gates.put(id, gate);
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public ParkingSlot getAvailableSlot(VehicleType type) {
        return slotStrategy.findAvailableSlot(type, floors);
    }

    // API 1
    public Ticket park(Vehicle vehicle, long entryTime, int gateId) {

        EntryGate gate = gates.get(gateId);
        if (gate == null) {
            throw new RuntimeException("Invalid gate ID: " + gateId);
        }

        ParkingSlot slot = gate.assignSlot(vehicle);

        if (slot == null) {
            throw new RuntimeException("No parking slot available for " + vehicle.getType());
        }

        slot.occupy();

        return new Ticket(vehicle, slot, entryTime);
    }

    // API 2
    public void status() {
        System.out.println("--- Parking Lot Status ---");
        for (ParkingFloor floor : floors) {
            Map<SlotType, Integer> available = new HashMap<>();

            for (ParkingSlot slot : floor.getSlots()) {
                if (!slot.isOccupied()) {
                    available.put(
                        slot.getType(),
                        available.getOrDefault(slot.getType(), 0) + 1
                    );
                }
            }
            System.out.println("Floor " + floor.getId() + " Available slots: " + available);
        }
    }

    // API 3
    public double exit(Ticket ticket, long exitTime) {
        ticket.getSlot().vacate();
        return pricingStrategy.calculateBill(ticket, exitTime);
    }
}