package tracker;

import java.util.Scanner;

public class UserInterFace {
    DataBase dataBase;
    Scanner sc = new Scanner(System.in);

    void run() {
        dataBase = new DataBase();
        System.out.println("Learning Progress Tracker");
        String userInp = sc.nextLine().strip();
//        String userInp = "add students";

        while (!userInp.equalsIgnoreCase("exit")) {
            menu(userInp, dataBase);
            userInp = sc.nextLine().strip();
        }
        System.out.println("Bye!");

    }

    private void menu(String userInp, DataBase dataBase) {
        switch (userInp) {
            case "add students":
                AddingPerson.addStudentsDataToDataBase(dataBase);
                break;
            case "":
                System.out.println("No input");
                break;
            case "back":
                System.out.println("Enter 'exit' to exit the program");
                break;
            default:
                System.out.println("Unknown command!");
        }
    }
}
