package tracker.Users;

import tracker.Statistics;

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

    private boolean notificationDoneJavaSent = false;
    private boolean notificationDoneSpringSent = false;
    private boolean notificationDoneDSASent = false;
    private boolean notificationDoneDatabasesSent = false;

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

    public boolean isCompleteJavaCourse() {
        return javaPoints >= Statistics.JAVA_MAX_POINTS;
    }

    public boolean isCompleteSpringCourse() {
        return springPoints >= Statistics.SPRING_MAX_POINTS;
    }

    public boolean isCompleteDSACourse() {
        return dsaPoints >= Statistics.DSA_MAX_POINTS;
    }

    public boolean isCompleteDatabasesCourse() {
        return databasesLangPoints > Statistics.DATABASE_MAX_POINTS;
    }

    public void setNotificationDoneJavaSent() {
        if (isCompleteJavaCourse()) {
            notificationDoneJavaSent = true;
        }
    }

    public void setNotificationDoneSpringSent() {
        if (isCompleteSpringCourse()) {
            this.notificationDoneSpringSent = true;
        }
    }

    public void setNotificationDoneDSASent() {
        if (isCompleteDSACourse()) {
            this.notificationDoneDSASent = true;
        }
    }

    public void setNotificationDoneDatabasesSent() {
        if (isCompleteDatabasesCourse()) {
            this.notificationDoneDatabasesSent = true;
        }
    }

    public boolean isNotificationDoneJavaSent() {
        return notificationDoneJavaSent;
    }

    public boolean isNotificationDoneSpringSent() {
        return notificationDoneSpringSent;
    }

    public boolean isNotificationDoneDSASent() {
        return notificationDoneDSASent;
    }

    public boolean isNotificationDoneDatabasesSent() {
        return notificationDoneDatabasesSent;
    }

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
