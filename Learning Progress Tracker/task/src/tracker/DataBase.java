package tracker;

import tracker.Users.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class DataBase {
    private int idPerson = 9999;
    private final HashMap<Integer, Student> studentDatabase = new HashMap<>();

    void addStudent(HashMap<String, String> dataStudent) {
        idPerson++;
        Student student = new Student(dataStudent.get("FirstName"), dataStudent.get("LastName"),
                dataStudent.get("Email"), idPerson);
        studentDatabase.put(idPerson, student);
    }

    public HashMap<Integer, Student> getDatabase() {
        return studentDatabase;
    }

    public Student getStudent(int idStudent) {
        return studentDatabase.get(idStudent);
    }
}
