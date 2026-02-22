public class AttendanceRule implements EligibilityRule{
    public String isVoilated(StudentProfile s){
        if(s.attendancePct < RuleInput.minAttendance){
            return "attendance below 75";
        }

        return null;
    }
}
