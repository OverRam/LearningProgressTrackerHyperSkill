package tracker;

import tracker.Users.Student;

import java.util.Scanner;

public class UserMenu {
    PersonDataBase personDataBase;
    Scanner sc = new Scanner(System.in);
    String userInp;

    void run() {
        personDataBase = new PersonDataBase();
        System.out.println("Learning Progress Tracker");
        String userInp = sc.nextLine().strip();

        //String userInp = "statistics";
        //testData();

        while (!userInp.equalsIgnoreCase("exit")) {
            menu(userInp);
            userInp = sc.nextLine().strip();
        }
        System.out.println("Bye!");

    }

    private void menu(String userInp) {
        switch (userInp) {

            case "add students":
                new AddingPerson(personDataBase).tryAddUsersData();
                break;

            case "":
                System.out.println("No input");
                break;

            case "back":
                System.out.println("Enter 'exit' to exit the program");
                break;

            case "list":
                if (personDataBase.getDatabase().size() < 1) {
                    System.out.println("No students found");
                } else {
                    System.out.println("Students:");
                    personDataBase.getDatabase().forEach((k, v) -> System.out.println(k));
                }
                break;

            case "add points":
                PointsHandler pointsHandler = new PointsHandler(personDataBase);
                pointsHandler.addingPointsInterface();
                break;

            case "find":
                find();
                break;

            case "statistics":
                Statistics statistics = new Statistics(personDataBase);
                statistics.menuLoop();
                break;

            default:
                System.out.println(userInp + " Unknown command!");
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

                if (personDataBase.getDatabase().get(id) != null) {
                    printStudentScoreById(id);
                } else {
                    System.out.printf("No student is found for id=%s.\n", inp);
                }

            }
        }
    }


    private void printStudentScoreById(int id) {
        Student student = personDataBase.getDatabase().get(id);
        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d\n", id,
                student.getJavaPoints(), student.getDSAPoints(), student.getDatabasesLangPoints(), student.getSpringPoints());
    }


    void testData() {
        /////// test data
        userInp = "add students";
        //10000
        personDataBase.addStudent(InputDataStructuring.studentDataStructuring("John Doe johnd@email.net"));
        //10001
        personDataBase.addStudent(InputDataStructuring.studentDataStructuring("Jane Sark jspark@yahoo.com"));


        PointsHandler pointsHandler = new PointsHandler(personDataBase);
        pointsHandler.addPoints("10000 8 7 7 5".split(" "));
        pointsHandler.addPoints("10000 7 6 9 7".split(" "));
        pointsHandler.addPoints("10000 6 5 5 0".split(" "));

        pointsHandler.addPoints("10001 8 0 8 6".split(" "));
        pointsHandler.addPoints("10001 7 0 0 0".split(" "));
        pointsHandler.addPoints("10001 9 0 0 5".split(" "));
    }
}