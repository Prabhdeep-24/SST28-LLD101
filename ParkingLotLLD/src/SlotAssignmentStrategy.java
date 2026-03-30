import java.util.List;

public interface SlotAssignmentStrategy {
    ParkingSlot findAvailableSlot(VehicleType vehicleType, List<ParkingFloor> floors);
}
