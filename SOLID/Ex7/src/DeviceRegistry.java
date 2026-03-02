import java.util.*;

public class DeviceRegistry {
    private final java.util.List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    public <T> List<T> getDevicesByType(Class<T> type) {
        List<T> result = new ArrayList<>();

        for (Object device : devices) {
            if (type.isInstance(device)) {
                result.add(type.cast(device));
            }
        }

        return result;
    }
}
