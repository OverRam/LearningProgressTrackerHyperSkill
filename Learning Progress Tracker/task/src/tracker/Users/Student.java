package tracker.Users;

public class Student {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final int id;
    private int javaPoints = 0;
    private int dsaPoints = 0;
    private int databasesLangPoints = 0;
    private int springPoints = 0;
    private int javaCompletedTasks = 0;
    private int dsaCompletedTasks = 0;
    private int databasesLangCompletedTasks = 0;
    private int springCompletedTasks = 0;

    public int getJavaCompletedTasks() {
        return javaCompletedTasks;
    }

    public int getDsaCompletedTasks() {
        return dsaCompletedTasks;
    }

    public int getDatabasesLangCompletedTasks() {
        return databasesLangCompletedTasks;
    }

    public int getSpringCompletedTasks() {
        return springCompletedTasks;
    }

    public Student(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public void addPointsJava(int pointsJava) {
        if (pointsJava > 0) {
            javaCompletedTasks++;
            this.javaPoints += pointsJava;
        }
    }

    public void addPointsDSA(int dSAPoints) {
        if (dSAPoints > 0) {
            dsaCompletedTasks++;
            this.dsaPoints += dSAPoints;
        }
    }

    public void addPointsDatabasesLang(int databasesLangPoints) {
        if (databasesLangPoints > 0) {
            databasesLangCompletedTasks++;
            this.databasesLangPoints += databasesLangPoints;
        }
    }

    public void addPointsSpring(int springPoints) {
        if (springPoints > 0) {
            springCompletedTasks++;
            this.springPoints += springPoints;
        }
    }

    //format java, dsa, dB, spring
    public void addAllPointsInFormat(int java, int dsa, int db, int spring) {
        addPointsJava(java);
        addPointsDSA(dsa);
        addPointsDatabasesLang(db);
        addPointsSpring(spring);
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public int getDSAPoints() {
        return dsaPoints;
    }

    public int getDatabasesLangPoints() {
        return databasesLangPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
