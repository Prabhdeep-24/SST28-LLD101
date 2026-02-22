public class CreditsRule implements EligibilityRule{
    public String isVoilated(StudentProfile s){
        if(s.earnedCredits < RuleInput.minCredits){
            return "credits below 20";
        }

        return null;
    }
}
