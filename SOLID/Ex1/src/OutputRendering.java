import java.util.List;

public class OutputRendering {
    void outputErrors(List<String> errors){
        System.out.println("ERROR: cannot register");
        for (String e : errors) System.out.println("- " + e);
    }

    void successMessage(StudentRecord record, int count){
        System.out.println("OK: created student " + record.id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }
}
