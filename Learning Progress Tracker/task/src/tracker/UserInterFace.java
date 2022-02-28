package tracker;

import tracker.Users.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static tracker.CheckData.isBack;

public class UserInterFace {
    DataBase dataBase;
    Scanner sc = new Scanner(System.in);

    void run() {
        dataBase = new DataBase();
        System.out.println("Learning Progress Tracker");
        String userInp = sc.nextLine().strip();
//        String userInp = "add students";

        while (!userInp.equalsIgnoreCase("exit")) {
            menu(userInp);
            userInp = sc.nextLine().strip();
        }
        System.out.println("Bye!");

    }

    private void menu(String userInp) {
        switch (userInp) {
            case "add students":
                new AddingPerson(dataBase).addStudents();
                break;
            case "":
                System.out.println("No input");
                break;
            case "back":
                System.out.println("Enter 'exit' to exit the program");
                break;
            case "list":
                if (dataBase.getDatabase().size() < 1) {
                    System.out.println("No students found");
                } else {
                    System.out.println("Students:");
                    dataBase.getDatabase().forEach((k, v) -> System.out.println(k));
                }
                break;
            case "add points":
                menuAddingPoints();
                break;
            case "find":
                find();
                break;
            default:
                System.out.println("Unknown command!");
        }
    }

    private void find() {
        System.out.println("Enter an id or 'back' to return:");

        String inp;
        boolean isNotBack = true;
        while (isNotBack) {

            boolean isBadInp = true;
            int id = -1;

            while (isBadInp) {
                inp = sc.nextLine();
                try {
                    if (!inp.equalsIgnoreCase("back")) {
                        id = Integer.parseInt(inp);
                    } else {
                        isNotBack = false;
                        break;
                    }

                    isBadInp = false;
                } catch (NumberFormatException ignored) {
                }

                if (dataBase.getDatabase().get(id) != null) {
                    printStudentScoreById(id);
                } else {
                    System.out.printf("No student is found for id=%s.\n", inp);
                }

            }
        }
    }


    private void printStudentScoreById(int id) {
        Student student = dataBase.getDatabase().get(id);
        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n",id,
                student.getJava(), student.getDSA(), student.getDatabasesLangPoints(), student.getSpringPoints());
    }

    private void menuAddingPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        int numberOfProgrammingLanguages = 4;
        int numbOfAllNeedParams = numberOfProgrammingLanguages + 1;
        boolean isUpdated;
        boolean isNotBack = true;
        List<Integer> points = null;

        while (isNotBack) {
            isUpdated = false;

            while (!isUpdated) {
                int id = -1;
                String[] userInputs = sc.nextLine().split(" ");
                List<Integer> inputLangPoints;
                boolean isNegativePoints = true;


                if (isBack(userInputs[0])) {
                    isNotBack = false;
                    break;
                }

                try {
                    id = Integer.parseInt(userInputs[0]);

                    inputLangPoints = Arrays.stream(userInputs).skip(1)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

                    if (userInputs.length == numbOfAllNeedParams && inputLangPoints.size() == numberOfProgrammingLanguages) {
                        points = inputLangPoints;
                        isNegativePoints = points.stream().anyMatch(x -> x < 0);
                    }

                } catch (NumberFormatException ignored) {
                }


                if (id < 0 || dataBase.getDatabase().get(id) == null) {
                    System.out.printf("No student is found for id=%s.\n", userInputs[0]);
                } else if (isNegativePoints || points.size() != numberOfProgrammingLanguages) {
                    System.out.println("Incorrect points format.");
                } else {
                    dataBase.getStudent(id).addPoints(points.get(0), points.get(1), points.get(2), points.get(3));
                    System.out.println("Points updated.");
                    isUpdated = true;
                }

            }
        }

    }
}