package tracker;

import java.util.HashMap;
import java.util.Objects;

public class DataHandler {

    static boolean checkStudentData(HashMap<String, String> studentData) {

        return studentData.entrySet().stream().allMatch(Objects::nonNull);
    }

    static HashMap<String, String> dataStructuring(String data) {
        HashMap<String, String> dataStructuring = new HashMap<>();
        String emailPattern = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
        String firstNamePattern = "[A-Z][a-z-']+";
        String lastNamePattern = "[A-Z][a-z]+";

        String[] arrayData = data.trim().split(" ");

        dataStructuring.put("email", "E");

        return dataStructuring;
    }

}
