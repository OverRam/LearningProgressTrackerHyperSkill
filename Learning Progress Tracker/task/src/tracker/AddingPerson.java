package tracker;

import java.util.HashMap;
import java.util.Scanner;

public class AddingPerson {
    private final PersonDataBase personDataBase;

    public AddingPerson(PersonDataBase personDataBase) {
        this.personDataBase = personDataBase;
    }

    void tryAddUsersData() {
        Scanner sc = new Scanner(System.in);

        String personData = "";
        boolean isAdded;
        int numberOfAddedStudents = 0;
        System.out.printf("Enter %s credentials or 'back' to return:\n", "student");

        while (!personData.equalsIgnoreCase("back")) {
            personData = sc.nextLine().strip();


            if (personData.equalsIgnoreCase("back")) {
                break;
            }

            if (personData.split(" ").length >= 3) {
                isAdded = checkPersonData(personData, personDataBase);
                if (isAdded) {
                    numberOfAddedStudents++;
                    System.out.printf("The %s has been added.\n", "student");
                }
            } else {
                System.out.println("Incorrect credentials.");
            }
        }
        System.out.printf("Total %d %ss have been added.\n", numberOfAddedStudents, "student");
    }

    private boolean checkPersonData(String personData, PersonDataBase personDataBase) {
        HashMap<String, String> personStructuredData = InputDataStructuring.studentDataStructuring(personData);

        if (!personData.isBlank() || personDataBase != null || CheckData.checkNullInStructuredData(personStructuredData)) {
            assert personDataBase != null;

            boolean isTakenEmail = personDataBase.getDatabase()
                    .entrySet().stream()
                    .anyMatch(e -> e.getValue().getEmail().equalsIgnoreCase(personStructuredData.get("Email")));

            if (isTakenEmail) {
                System.out.println("This email is already taken.");
                return false;
            }

            if (CheckData.nullCheckerStructure(personStructuredData)) {
                personDataBase.addStudent(personStructuredData);
                return true;
            } else {
                CheckData.printWrongParams(personStructuredData);
                return false;
            }
        }
        return false;
    }
}
