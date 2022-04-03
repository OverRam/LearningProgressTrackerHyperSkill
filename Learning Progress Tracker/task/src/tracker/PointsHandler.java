package tracker;

import tracker.Users.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PointsHandler {
    private final PersonDataBase personDataBase;
    private final int numberOfProgrammingLanguages = LanguagesEnum.values().length;

    public PointsHandler(PersonDataBase personDataBase) {
        this.personDataBase = personDataBase;
    }

    void addPoints(int id, List<Integer> points) {
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
                default:
                    throw new RuntimeException("Need add new enum");

            }
        }

    }

    /**
     * Input format is string: id student java dsa dB spring..., like in {@link LanguagesEnum}
     **/
    void addingPointsLoop() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an id and points or 'back' to return:");

        boolean isNotBack = true;

        while (isNotBack) {
            String[] input = sc.nextLine().trim().split(" ");

            List<Integer> points = parsePointsToInt(Arrays.asList(Arrays.copyOfRange(input, 1, input.length)));

            Integer id = parseIdToInt(input[0]);


            if (CheckData.isBack(input[0])) {
                isNotBack = false;

            } else if (id == null || personDataBase.getStudent(id) == null) {
                System.out.printf("No student is found for id=%s.\n", input[0]);

            } else if (points == null || points.size() != numberOfProgrammingLanguages ||
                    points.stream().anyMatch(x -> x < 0)) {
                System.out.println("Incorrect points format.");

            } else {
                addPoints(id, points);
                System.out.println("Points updated.");
            }
        }

    }

    /**
     * @param pointsFormat is: java=int DSA=int Databases=int Spring=int
     * @return a null if bad parameters
     */
    static List<Integer> parsePointsToInt(List<String> pointsFormat) {
        int numOfLang = LanguagesEnum.values().length;

        if (pointsFormat.size() == numOfLang) {
            try {
                return pointsFormat.stream().map(Integer::parseInt).collect(Collectors.toList());
            } catch (NumberFormatException ignore) {
            }
        }
        return null;
    }

    static Integer parseIdToInt(String id) {
        try {
            return Integer.parseInt(id);
        } catch (NumberFormatException ignore) {
        }
        return null;
    }

}
