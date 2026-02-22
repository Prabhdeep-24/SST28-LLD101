import java.util.LinkedHashMap;
import java.util.Map;

public class InputParsing {
    StudentRequestData parse(String rawData){
        System.out.println("INPUT: " + rawData);

        Map<String,String> kv = new LinkedHashMap<>();
        String[] parts = rawData.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }

        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        return new StudentRequestData(name, email, phone, program);
    }
}
