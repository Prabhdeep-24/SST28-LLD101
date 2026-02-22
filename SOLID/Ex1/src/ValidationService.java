import java.util.*;

public class ValidationService {

    List<String> validate(StudentRequestData data){
        String name = data.getName();
        String email = data.getEmail();
        String phoneNo = data.getPhoneNo();
        String program = data.getProgram();

        List<String> errors = new ArrayList<>();
        if (name.isBlank()) errors.add("name is required");
        if (email.isBlank() || !email.contains("@")) errors.add("email is invalid");
        if (phoneNo.isBlank() || !phoneNo.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!(program.equals("CSE") || program.equals("AI") || program.equals("SWE"))) errors.add("program is invalid");

        if (!errors.isEmpty()) {
            return errors;
        }

        return new ArrayList<>();
    }

}
