package tracker;

import java.util.HashMap;
import java.util.Scanner;

public class AddingPerson {
    private final DataBase dataBase;

    public AddingPerson(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    void addStudents() {
        addUsersData(3, "student");

    }

    void addEmployees() {
        addUsersData(4, "employee");
    }

    private void addUsersData(int userParams, String whoBeAdded) {
        Scanner sc = new Scanner(System.in);

        String personData = "";
        boolean isAdded;
        int numberOfAddedStudents = 0;
        System.out.printf("Enter %s credentials or 'back' to return:\n", whoBeAdded);

        while (!personData.equalsIgnoreCase("back")) {
            personData = sc.nextLine().strip();

            if (personData.equalsIgnoreCase("back")) {
                break;
            }

            if (personData.split(" ").length >= userParams) {
                isAdded = checkPersonData(personData, dataBase);
                if (isAdded) {
                    numberOfAddedStudents++;
                    System.out.printf("The %s has been added.\n", whoBeAdded);
                }
            } else {
                System.out.println("Incorrect credentials.");
            }
        }
        System.out.printf("Total %d %ss have been added.\n", numberOfAddedStudents, whoBeAdded);
    }

    private boolean checkPersonData(String personData, DataBase dataBase) {
        HashMap<String, String> personStructuredData = DataStructuring.studentDataStructuring(personData);

        if (!personData.isBlank() || dataBase != null || CheckData.checkNullInStructuredData(personStructuredData)) {
            assert dataBase != null;

            boolean isTakenEmail = dataBase.getDatabase()
                    .entrySet().stream()
                    .anyMatch(e -> e.getValue().getEmail().equalsIgnoreCase(personStructuredData.get("Email")));

            if (isTakenEmail) {
                System.out.println("This email is already taken.");
                return false;
            }

            if (CheckData.nullCheckerStructure(personStructuredData)) {
                dataBase.addStudent(personStructuredData);
                return true;
            } else {
                CheckData.printWrongParams(personStructuredData);
                return false;
            }
        }
        return false;
    }
}
