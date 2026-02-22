import java.util.*;

public class OnboardingService {
    private final StudentRepository db;
    private final ValidationService validateData;
    private final InputParsing parseInput;
    private final OutputRendering outputRender;

    public OnboardingService(StudentRepository db, OutputRendering render) {
        this.db = db;
        outputRender = render;
        validateData = new ValidationService();
        parseInput = new InputParsing();
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        // validation inline, printing inline

        StudentRequestData data = parseInput.parse(raw);
        List<String> errors = validateData.validate(data);

        if(!errors.isEmpty()){
            outputRender.outputErrors(errors);
            return;
        }
        
        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, data);

        db.save(rec);
        outputRender.successMessage(rec, db.count());
    }
}
