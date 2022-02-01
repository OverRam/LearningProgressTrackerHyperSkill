package tracker;

import java.util.HashMap;

public class UserInterFace {
    DataBase dataBase;

    void run() {
        dataBase = new DataBase();
        System.out.println("Learning Progress Tracker");


    }

    private boolean addPerson(String studentData) {
        HashMap<String, String> structuredStudentData = DataHandler.dataStructuring(studentData);

        if (DataHandler.checkStudentData(structuredStudentData)) {
            dataBase.addStudent(structuredStudentData);
            return true;
        }
        return false;
    }
}
