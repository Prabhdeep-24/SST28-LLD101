public class DisciplineRule implements EligibilityRule{
    public String isVoilated(StudentProfile s){
        if(s.disciplinaryFlag != LegacyFlags.NONE){
            return "disciplinary flag present";
        }

        return null;
    }
}
