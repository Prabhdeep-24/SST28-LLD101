import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Setup Strategy
        SlotAssignmentStrategy slotStrategy = new DefaultSlotAssignmentStrategy();
        
        Map<SlotType, Double> rates = new HashMap<>();
        rates.put(SlotType.SMALL, 10.0);
        rates.put(SlotType.MEDIUM, 20.0);
        rates.put(SlotType.LARGE, 30.0);
        PricingStrategy pricingStrategy = new HourlyPricingStrategy(rates);

        // Create parking lot
        ParkingLot lot = new ParkingLot(slotStrategy, pricingStrategy);

        // Create floors and slots
        List<ParkingSlot> floor1Slots = Arrays.asList(
                new ParkingSlot(101, SlotType.SMALL),
                new ParkingSlot(102, SlotType.MEDIUM),
                new ParkingSlot(103, SlotType.LARGE)
        );
        ParkingFloor floor1 = new ParkingFloor(1, floor1Slots);
        
        List<ParkingSlot> floor2Slots = Arrays.asList(
                new ParkingSlot(201, SlotType.SMALL),
                new ParkingSlot(202, SlotType.LARGE)
        );
        ParkingFloor floor2 = new ParkingFloor(2, floor2Slots);

        lot.addFloor(floor1);
        lot.addFloor(floor2);

        // Create gate
        EntryGate gate1 = new EntryGate(1, lot);
        lot.addGate(1, gate1);

        // Print initial status
        lot.status();

        // Create vehicle
        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);
        System.out.println("\nParking CAR " + car.getNumber());

        // PARK
        long entryTime = System.currentTimeMillis();
        Ticket ticket = lot.park(
                car,
                entryTime,
                1
        );

        System.out.println("Vehicle parked at Floor/Slot ID: " + ticket.getSlot().getId());

        // STATUS
        System.out.println("\nStatus after parking CAR:");
        lot.status();

        // EXIT after 1.5 hours (should bill for 2 hours)
        long exitTime = entryTime + (long)(1.5 * 60 * 60 * 1000);

        System.out.println("\nExiting CAR " + car.getNumber());
        double bill = lot.exit(ticket, exitTime);

        System.out.println("Total bill: Rs " + bill);

        // STATUS again
        System.out.println("\nStatus after exiting CAR:");
        lot.status();
    }
}