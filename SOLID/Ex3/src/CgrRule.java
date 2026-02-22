public class CgrRule implements EligibilityRule{
    public String isVoilated(StudentProfile s){
        if(s.cgr < RuleInput.minCgr){
            return "CGR below 8.0";
        }

        return null;
    }
}
