import java.util.List;

public class DefaultSlotAssignmentStrategy implements SlotAssignmentStrategy {

    @Override
    public ParkingSlot findAvailableSlot(VehicleType vehicleType, List<ParkingFloor> floors) {
        for (ParkingFloor floor : floors) {
            // Priority 1: Exact match or optimal fit
            ParkingSlot slot = findSlotByType(floor, getBestFitSlotType(vehicleType));
            if (slot != null) return slot;

            // Fallbacks for Bike picking Medium or Large if Small is full
            if (vehicleType == VehicleType.BIKE) {
                slot = findSlotByType(floor, SlotType.MEDIUM);
                if (slot != null) return slot;
                slot = findSlotByType(floor, SlotType.LARGE);
                if (slot != null) return slot;
            } else if (vehicleType == VehicleType.CAR) {
                // Car picking Large if Medium is full
                slot = findSlotByType(floor, SlotType.LARGE);
                if (slot != null) return slot;
            }
        }
        return null;
    }

    private ParkingSlot findSlotByType(ParkingFloor floor, SlotType type) {
        for (ParkingSlot slot : floor.getSlots()) {
            if (!slot.isOccupied() && slot.getType() == type) {
                return slot;
            }
        }
        return null;
    }

    private SlotType getBestFitSlotType(VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE: return SlotType.SMALL;
            case CAR: return SlotType.MEDIUM;
            case BUS: return SlotType.LARGE;
            default: return SlotType.LARGE;
        }
    }
}
