public class StudentRequestData {
    private String name, email, phoneNo, program;

    public StudentRequestData(String name, String email, String phoneNo, String program){
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.program = program;
    }

    String getName(){
        return name;
    }

    String getEmail(){
        return email;
    }

    String getPhoneNo(){
        return phoneNo;
    }

    String getProgram(){
        return program;
    }
}
