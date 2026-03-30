package Elevator.panels;

import Elevator.buttons.*;
import Elevator.enums.Direction;
import Elevator.controllers.ElevatorController;

public class FloorPanel {

    private OutsideButton up;
    private OutsideButton down;

    public FloorPanel(int floor, ElevatorController controller) {
        up = new OutsideButton(floor, Direction.UP, controller);
        down = new OutsideButton(floor, Direction.DOWN, controller);
    }

    public void pressUp() { up.press(); }
    public void pressDown() { down.press(); }
}