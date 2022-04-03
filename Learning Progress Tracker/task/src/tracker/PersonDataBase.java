package tracker;

import tracker.Users.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDataBase {
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

    void notifyStudentsDoneCurse() {
        Map<Student, List<LanguagesEnum>> doneCuresByStudent = setStudentDoneCures();

        doneCuresByStudent.forEach(((student, courseList) -> courseList.forEach(courseName -> {
            MessageToStudentDoneCourse(student, courseName.toString());
            setStatusNotificationSend(student, courseName);
        })));

        System.out.printf("Total %d students have been notified.%n", doneCuresByStudent.size());

    }

    private void setStatusNotificationSend(Student student, LanguagesEnum courseName) {

        switch (courseName) {
            case JAVA:
                student.setNotificationDoneJavaSent();
                break;
            case DSA:
                student.setNotificationDoneDSASent();
                break;
            case DATABASE:
                student.setNotificationDoneDatabasesSent();
                break;
            case SPRING:
                student.setNotificationDoneSpringSent();
                break;
            default:
                throw new RuntimeException("Wrong course name.");
        }
    }

    private Map<Student, List<LanguagesEnum>> setStudentDoneCures() {
        HashMap<Student, List<LanguagesEnum>> doneCuresByStudent = new HashMap<>();

        studentDatabase.values().forEach(s -> {
            List<LanguagesEnum> addedCourseList = new ArrayList<>();

            if (s.isCompleteJavaCourse() && !s.isNotificationDoneJavaSent()) {
                addedCourseList.add(LanguagesEnum.JAVA);
            }
            if (s.isCompleteDSACourse() && !s.isNotificationDoneDSASent()) {
                addedCourseList.add(LanguagesEnum.DSA);
            }
            if (s.isCompleteDatabasesCourse() && !s.isNotificationDoneDatabasesSent()) {
                addedCourseList.add(LanguagesEnum.DATABASE);
            }
            if (s.isCompleteSpringCourse() && !s.isNotificationDoneSpringSent()) {
                addedCourseList.add(LanguagesEnum.SPRING);
            }

            if (addedCourseList.size() > 0) {
                doneCuresByStudent.put(s, addedCourseList);
            }
        });

        return doneCuresByStudent;
    }

    void MessageToStudentDoneCourse(Student student, String courseName) {
        System.out.printf("To: %s%n" +
                        "Re: Your Learning Progress%n" +
                        "Hello, %s %s! You have accomplished our %s course!%n",
                student.getEmail(), student.getFirstName(), student.getLastName(), courseName);
    }

}
