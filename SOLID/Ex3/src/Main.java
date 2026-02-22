import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<EligibilityRule> rules = EligibilityRulesConfig.defineRules();

        System.out.println("=== Placement Eligibility ===");
        StudentProfile s = new StudentProfile("23BCS1001", "Ayaan", 8.10, 72, 18, LegacyFlags.NONE);
        EligibilityEngine engine = new EligibilityEngine(new FakeEligibilityStore(), rules);
        engine.runAndPrint(s);
    }
}
