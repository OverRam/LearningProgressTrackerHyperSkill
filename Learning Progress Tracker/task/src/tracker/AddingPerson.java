package tracker;

import java.util.HashMap;
import java.util.Scanner;

public class AddingPerson {

    static void addStudentsDataToDataBase(DataBase dataBase) {
        Scanner sc = new Scanner(System.in);
        String studentData = "";
        boolean isAdd;
        int addedStudents = 0;
        int userParams = 3;
        System.out.println("Enter student credentials or 'back' to return:");
        while (!studentData.equalsIgnoreCase("back")) {
            studentData = sc.nextLine().strip();

            if (studentData.equalsIgnoreCase("back")) {
                break;
            }

            if (studentData.split(" ").length >= userParams) {
                isAdd = addPerson(studentData, dataBase);
                addedStudents += isAdd ? 1 : 0;
                if (isAdd) {
                    System.out.println("The student has been added.");
                }
            } else {
                System.out.println("Incorrect credentials.");
            }
        }

        System.out.printf("Total %d students have been added.\n", addedStudents);
    }

    private static boolean addPerson(String personData, DataBase dataBase) {
        HashMap<String, String> structuredStudentData = DataHandler.dataStructuring(personData);

        if (!personData.isBlank() || dataBase != null || DataHandler.checkStudentData(structuredStudentData)) {
            assert dataBase != null;
            if (DataHandler.checkStructure(structuredStudentData)) {
                dataBase.addStudent(structuredStudentData);
                return true;
            } else {
                DataHandler.printWrongParams(structuredStudentData);
                return false;
            }
        }
        return false;
    }

}
