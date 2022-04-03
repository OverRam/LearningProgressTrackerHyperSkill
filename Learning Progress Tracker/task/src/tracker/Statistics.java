package tracker;

import tracker.Users.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {

    private final Map<String, DataLanguagesInfo> langMap = new HashMap<>();
    private final List<Student> javaLangInfo = new ArrayList<>();
    private final List<Student> springLangInfo = new ArrayList<>();
    private final List<Student> dataBaseLangInfo = new ArrayList<>();
    private final List<Student> dSALangInfo = new ArrayList<>();
    public final static int JAVA_MAX_POINTS = 600;
    public final static int SPRING_MAX_POINTS = 550;
    public final static int DATABASE_MAX_POINTS = 480;
    public final static int DSA_MAX_POINTS = 400;

    private final PersonDataBase personDataBase;
    final String noDetails = "n/a";


    public Statistics(PersonDataBase personDataBase) {
        this.personDataBase = personDataBase;
        setStatisticsData();
        printGeneralStatistic();
    }

    void menuLoop() {
        Scanner sc = new Scanner(System.in);
        String userInp = sc.nextLine();
        while (!"back".equalsIgnoreCase(userInp)) {
            printDetailsLanguageStudents(userInp);
            userInp = sc.nextLine();
        }
    }

    void printGeneralStatistic() {
        List<DataLanguagesInfo> langList = langMap.values().stream().filter(x -> x.numOfMembers > 0)
                .collect(Collectors.toList());

        List<String> popularLang = getLangByPopular(langList);
        String mPopular = popularLang.get(0);
        String lPopular = popularLang.get(1);

        List<String> activityLang = getLangByCompletedTask(langList);
        String hActivity = activityLang.get(0);
        String lActivity = activityLang.get(1);

        List<String> averagePointsLang = getLangByAverageGradePerAssignment(langList);
        String easiest = averagePointsLang.get(0);
        String hardest = averagePointsLang.get(1);

        System.out.printf("Type the name of a course to see details or 'back' to quit:\n" +
                "Most popular:%s\n" +
                "Least popular:%s\n" +
                "Highest activity:%s\n" +
                "Lowest activity:%s\n" +
                "Easiest course:%s\n" +
                "Hardest course:%s\n", mPopular, lPopular, hActivity, lActivity, easiest, hardest);
    }

    /**
     * @return list index 0 is most activity 1 is last activity
     */
    List<String> getLangByCompletedTask(List<DataLanguagesInfo> langList) {
        langList.sort(Comparator.comparing(DataLanguagesInfo::getCompletedTasks).reversed());
        List<String> langByActivity = new LinkedList<>();


        if (langList.size() == 0) {
            langByActivity.add(noDetails);
            langByActivity.add(noDetails);
        } else {
            int maxCompletedTask = langList.get(0).getCompletedTasks();

            langByActivity.add(langList.stream()
                    .filter(x -> x.getCompletedTasks() == maxCompletedTask && x.getCompletedTasks() > 0)
                    .map(DataLanguagesInfo::getLangName)
                    .reduce("", (a, b) -> a + " " + b));

            if (langList.size() > 1) {
                if (langList.get(0).getCompletedTasks() > langList.get(langList.size() - 1).getCompletedTasks()) {
                    int minActivity = langList.get(langList.size() - 1).getCompletedTasks();
                    langByActivity.add(langList.stream()
                            .filter(x -> x.getCompletedTasks() == minActivity && x.getCompletedTasks() > 0)
                            .map(DataLanguagesInfo::getLangName)
                            .reduce("", (a, b) -> a + " " + b));
                } else {
                    langByActivity.add(noDetails);
                }
            }

        }
        return langByActivity;

    }

    /**
     * @return list index 0 is most average  1 is last average, average of points per completed task
     */
    List<String> getLangByAverageGradePerAssignment(List<DataLanguagesInfo> langList) {
        langList.sort(Comparator.comparing(DataLanguagesInfo::getAveragePointsPerCompletedTask).reversed());
        List<String> langByPointsAverage = new LinkedList<>();


        if (langList.size() == 0) {
            langByPointsAverage.add(noDetails);
            langByPointsAverage.add(noDetails);
        } else {
            double maxPointsAverage = langList.get(0).getAveragePointsPerCompletedTask();

            langByPointsAverage.add(langList.stream()
                    .filter(x -> x.getAveragePointsPerCompletedTask() == maxPointsAverage &&
                            x.getAveragePointsPerCompletedTask() > 0)
                    .map(DataLanguagesInfo::getLangName)
                    .reduce("", (a, b) -> a + " " + b));

            if (langList.size() > 1) {
                if (langList.get(0).getAveragePointsPerCompletedTask() >
                        langList.get(langList.size() - 1).getAveragePointsPerCompletedTask()) {

                    double minPointsAverage = langList.get(langList.size() - 1).getAveragePointsPerCompletedTask();
                    langByPointsAverage.add(langList.stream()
                            .filter(x -> x.getAveragePointsPerCompletedTask() == minPointsAverage &&
                                    x.getAveragePointsPerCompletedTask() > 0)
                            .map(DataLanguagesInfo::getLangName)
                            .reduce("", (a, b) -> a + " " + b));
                } else {
                    langByPointsAverage.add(noDetails);
                }
            }

        }
        return langByPointsAverage;

    }

    /**
     * @return list index 0 is most popular 1 is last popular
     */
    List<String> getLangByPopular(List<DataLanguagesInfo> langList) {
        langList.sort(Comparator.comparing(DataLanguagesInfo::getNumOfMembers).reversed());
        List<String> popularLang = new LinkedList<>();

        if (langList.size() == 0) {
            popularLang.add(noDetails);
            popularLang.add(noDetails);
        } else {
            int maxMembers = langList.get(0).getNumOfMembers();

            popularLang.add(langList.stream()
                    .filter(x -> x.getNumOfMembers() == maxMembers && x.getNumOfMembers() > 0)
                    .map(DataLanguagesInfo::getLangName)
                    .reduce("", (a, b) -> a + " " + b));

            if (langList.size() > 1) {
                if (langList.get(0).getNumOfMembers() > langList.get(langList.size() - 1).getNumOfMembers()) {
                    int minMembers = langList.get(langList.size() - 1).getNumOfMembers();
                    popularLang.add(langList.stream()
                            .filter(x -> x.getNumOfMembers() == minMembers && x.getNumOfMembers() > 0)
                            .map(DataLanguagesInfo::getLangName)
                            .reduce("", (a, b) -> a + " " + b));
                } else {
                    popularLang.add(noDetails);
                }
            }

        }
        return popularLang;
    }

    private void setStatisticsData() {

        personDataBase.getDatabase().forEach((id, student) -> {

            if (student.getJavaPoints() > 0) {
                javaLangInfo.add(student);
            }

            if (student.getSpringPoints() > 0) {
                springLangInfo.add(student);
            }

            if (student.getDatabasesLangPoints() > 0) {
                dataBaseLangInfo.add(student);
            }

            if (student.getDSAPoints() > 0) {
                dSALangInfo.add(student);
            }
        });

        langMap.put("Java", new DataLanguagesInfo(LanguagesEnum.JAVA, javaLangInfo, JAVA_MAX_POINTS));
        langMap.put("Spring", new DataLanguagesInfo(LanguagesEnum.SPRING, springLangInfo, SPRING_MAX_POINTS));
        langMap.put("Databases", new DataLanguagesInfo(LanguagesEnum.DATABASE, dataBaseLangInfo, DATABASE_MAX_POINTS));
        langMap.put("DSA", new DataLanguagesInfo(LanguagesEnum.DSA, dSALangInfo, DSA_MAX_POINTS));
    }


    void printDetailsLanguageStudents(String langToCheck) {
        String textFormat = "%5d %3d        %3.1f%s"; //id, points, done course in %;

        switch (langToCheck.trim().toLowerCase()) {
            case "java":
                System.out.println("Java\nid    points    completed");
                javaLangInfo.sort(Comparator.comparing(Student::getJavaPoints).reversed().thenComparing(Student::getId));
                javaLangInfo.forEach(s -> System.out.printf(textFormat, s.getId(), s.getJavaPoints(),
                        (double) s.getJavaPoints() * 100 / langMap.get("Java").getMaxPoints(), "%\n"));
                break;

            case "databases":

                System.out.println("Databases\nid    points    completed");
                dataBaseLangInfo
                        .forEach(s -> System.out.printf(textFormat, s.getId(), s.getDatabasesLangPoints(),
                                (double) s.getDatabasesLangPoints() * 100 / langMap.get("Databases").getMaxPoints(), "%\n"));
                break;

            case "spring":
                System.out.println("Spring\nid    points    completed");
                springLangInfo
                        .forEach(s -> System.out.printf(textFormat, s.getId(), s.getSpringPoints(),
                                (double) s.getSpringPoints() * 100 / langMap.get("Spring").getMaxPoints(), "%\n"));
                break;

            case "dsa":
                System.out.println("DSA\nid    points    completed");
                dSALangInfo
                        .forEach(s -> System.out.printf(textFormat, s.getId(), s.getDSAPoints(),
                                (double) s.getDSAPoints() * 100 / langMap.get("DSA").getMaxPoints(), "%\n"));
                break;

            default:
                System.out.println("Unknown course.");
        }
    }

    private static class DataLanguagesInfo {
        private final LanguagesEnum langName;
        private final List<Student> studentList;
        private final int numOfMembers;
        private final int maxPoints;
        private final int completedTasks;
        private final double averagePointsPerCompletedTask;

        DataLanguagesInfo(LanguagesEnum langName, List<Student> studentList, int maxPoints) {
            this.langName = langName;
            this.studentList = studentList;
            numOfMembers = studentList.size();
            this.maxPoints = maxPoints;
            completedTasks = setCompletedTasks();
            int sumOfStudentPointsThisLang = setAllStudentsPointsThisLang();

            if (sumOfStudentPointsThisLang > 0 && completedTasks > 0) {
                averagePointsPerCompletedTask = Math.floorDiv(sumOfStudentPointsThisLang, completedTasks);
            } else {
                averagePointsPerCompletedTask = 0;
            }
        }

        int getCompletedTasks() {
            return completedTasks;
        }

        double getAveragePointsPerCompletedTask() {
            return averagePointsPerCompletedTask;
        }

        private int setCompletedTasks() {
            Stream<Student> stream = studentList.stream();
            switch (langName) {
                case JAVA:
                    return stream.reduce(0, (sum, student) -> sum + student.getJavaCompletedTasks(), Integer::sum);
                case SPRING:
                    return stream.reduce(0, (sum, student) -> sum + student.getSpringCompletedTasks(), Integer::sum);
                case DATABASE:
                    return stream.reduce(0, (sum, student) -> sum + student.getDatabasesLangCompletedTasks(), Integer::sum);
                case DSA:
                    return stream.reduce(0, (sum, student) -> sum + student.getDsaCompletedTasks(), Integer::sum);
                default:
                    return 0;
            }
        }

        private int setAllStudentsPointsThisLang() {
            Stream<Student> stream = studentList.stream();
            switch (langName) {
                case JAVA:
                    return stream.reduce(0, (sum, student) -> sum + student.getJavaPoints(), Integer::sum);
                case SPRING:
                    return stream.reduce(0, (sum, student) -> sum + student.getSpringPoints(), Integer::sum);
                case DATABASE:
                    return stream.reduce(0, (sum, student) -> sum + student.getDatabasesLangPoints(), Integer::sum);
                case DSA:
                    return stream.reduce(0, (sum, student) -> sum + student.getDSAPoints(), Integer::sum);
                default:
                    return 0;
            }
        }

        String getLangName() {
            return langName.toString();
        }

        int getNumOfMembers() {
            return numOfMembers;
        }

        public int getMaxPoints() {
            return maxPoints;
        }
    }

}
