import java.util.List;

public interface StudentRepository {
    void save(StudentRecord st);
    int count ();
    List<StudentRecord> all();
} 