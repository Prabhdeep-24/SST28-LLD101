public class StudentRecord {
    public final String id;
    public final String name;
    public final String email;
    public final String phone;
    public final String program;

    public StudentRecord(String id, StudentRequestData data) {
        this.id = id; 
        this.name = data.getName(); 
        this.email = data.getEmail(); 
        this.phone = data.getPhoneNo(); 
        this.program = data.getProgram();
    }

    @Override
    public String toString() {
        return "StudentRecord{id='" + id + "', name='" + name + "', email='" + email + "', phone='" + phone + "', program='" + program + "'}";
    }
}
