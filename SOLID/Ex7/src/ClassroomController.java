public class ClassroomController {

    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {

        for (PowerControl p : reg.getDevicesByType(PowerControl.class)) {
            p.powerOn();
        }

        for (BrightnessControl b : reg.getDevicesByType(BrightnessControl.class)) {
            b.setBrightness(60);
        }

        for (TemperatureControl t : reg.getDevicesByType(TemperatureControl.class)) {
            t.setTemperatureC(24);
        }

        for (AttendanceReader a : reg.getDevicesByType(AttendanceReader.class)) {
            System.out.println("Attendance scanned: present=" + a.scanAttendance());
        }
    }

    public void endClass() {

        System.out.println("Shutdown sequence:");
        for (PowerControl p : reg.getDevicesByType(PowerControl.class)) {
            p.powerOff();
        }
    }
}