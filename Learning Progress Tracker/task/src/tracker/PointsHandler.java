package tracker;

import tracker.Users.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PointsHandler {
    PersonDataBase personDataBase;
    int numberOfProgrammingLanguages = LanguagesEnum.values().length;

    public PointsHandler(PersonDataBase personDataBase) {
        this.personDataBase = personDataBase;
    }

    private void addPoints(int id, List<Integer> points) {
        Student student = personDataBase.getStudent(id);

        //format java, dsa, dB, spring
        LanguagesEnum[] values = LanguagesEnum.values();

        for (int i = 0; i < values.length; i++) {
            String choiceLang = values[i].toString();
            switch (choiceLang) {
                case "Java":
                    student.addPointsJava(points.get(i));
                    break;
                case "DSA":
                    student.addPointsDSA(points.get(i));
                    break;
                case "Databases":
                    student.addPointsDatabasesLang(points.get(i));
                    break;
                case "Spring":
                    student.addPointsSpring(points.get(i));
                    break;
            }
        }

    }

    void addingPointsInterface() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an id and points or 'back' to return:");

        boolean isNotBack = true;

        while (isNotBack) {
            String[] inpPointsInFormat = sc.nextLine().trim().split(" ");
            if (CheckData.isBack(inpPointsInFormat[0])) {
                isNotBack = false;
            } else {
                addPoints(inpPointsInFormat);
            }
        }

    }

    /**
     * Format is: java dsa dB spring, like in {@link LanguagesEnum}
     **/
    void addPoints(String[] pointsInFormat) {
        int numbOfAllNeedParams = numberOfProgrammingLanguages + 1;
        List<Integer> points = null;
        List<Integer> inputLangPoints;


        boolean isUpdated = false;

        while (!isUpdated) {
            int id = -1;
            boolean isNegativePoints = true;

            //try parse, check: num of params, negative numbers
            try {
                id = Integer.parseInt(pointsInFormat[0]);

                inputLangPoints = Arrays.stream(pointsInFormat).skip(1)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                if (pointsInFormat.length == numbOfAllNeedParams
                        && inputLangPoints.size() == numberOfProgrammingLanguages) {

                    points = inputLangPoints;
                    isNegativePoints = points.stream().anyMatch(x -> x < 0);
                }

            } catch (NumberFormatException ignored) {
            }

            if (id < 0 || personDataBase.getDatabase().get(id) == null) {
                System.out.printf("No student is found for id=%s.\n", pointsInFormat[0]);
            } else if (isNegativePoints || points.size() != numberOfProgrammingLanguages) {
                System.out.println("Incorrect points format.");
            } else {
                addPoints(id, points);
                System.out.println("Points updated.");
                isUpdated = true;
            }
        }
    }

}
