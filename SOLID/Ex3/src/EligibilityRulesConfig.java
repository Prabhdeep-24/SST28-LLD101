import java.util.List;

public class EligibilityRulesConfig {
    public static List<EligibilityRule> defineRules(){
        List<EligibilityRule> rules = List.of(
            new AttendanceRule(),
            new CgrRule(),
            new CreditsRule(),
            new DisciplineRule()
        );

        return rules;
    }
}