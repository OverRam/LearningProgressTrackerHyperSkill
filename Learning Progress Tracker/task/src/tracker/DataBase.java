package tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    private final ArrayList<HashMap<String, String>> database = new ArrayList<>();

    void addStudent(HashMap<String, String> dataStudent) {
        database.add(dataStudent);
    }

    public ArrayList<HashMap<String, String>> getDatabase() {
        return database;
    }
}
