package Elevator.panels;

import Elevator.buttons.*;
import Elevator.models.Elevator;
import java.util.*;

public class CarPanel {

    private List<InsideButton> buttons = new ArrayList<>();
    private OpenButton open;
    private CloseButton close;
    private EmergencyButton emergency;
    private AlarmButton alarm;

    public CarPanel(Elevator elevator, int totalFloors) {
        for (int i = 0; i < totalFloors; i++) {
            buttons.add(new InsideButton(i, elevator));
        }
        open = new OpenButton(elevator);
        close = new CloseButton(elevator);
        emergency = new EmergencyButton(elevator);
        alarm = new AlarmButton(elevator);
    }

    public void pressFloor(int floor) {
        buttons.get(floor).press();
    }

    public void pressOpen() { open.press(); }
    public void pressClose() { close.press(); }
    public void pressEmergency() { emergency.press(); }
    public void pressAlarm() { alarm.press(); }
}