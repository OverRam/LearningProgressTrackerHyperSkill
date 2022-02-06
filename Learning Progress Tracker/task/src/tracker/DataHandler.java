package tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class DataHandler {

    static boolean checkStudentData(HashMap<String, String> studentData) {

        return studentData.entrySet().stream().allMatch(Objects::nonNull);
    }

    static HashMap<String, String> dataStructuring(String inputData) {
        HashMap<String, String> dataStructuring = new HashMap<>();

        String firstNamePattern = "^[A-z]+[-']?[A-z]+$";
        String lastNamePattern = "^([A-z]+ ?([-']?[A-z]+)*[A-Z]*)+$";
        String emailPattern = "^([\\w\\.-]+)@([\\w&^_]+)+\\.[a-z0-9]+$";
        String[] arrayData = inputData.strip().split(" ");

        putDataTo("first name", arrayData[0], firstNamePattern, dataStructuring);

        inputData = inputData.replaceFirst(arrayData[0], "")
                .replaceFirst(arrayData[arrayData.length - 1], "").strip();

        putDataTo("last name", inputData, lastNamePattern, dataStructuring);


        putDataTo("email", arrayData[arrayData.length - 1], emailPattern, dataStructuring);
        return dataStructuring;
    }

    private static void putDataTo(String keyValue, String valueToAdd, String patternToCheck,
                                  HashMap<String, String> dataStructuring) {
        if (valueToAdd.length() > 1 && Pattern.compile(patternToCheck).matcher(valueToAdd).find()) {
            dataStructuring.put(keyValue, valueToAdd);
        } else
            dataStructuring.put(keyValue, null);
    }

    static boolean checkStructure(HashMap<String, String> structuredData) {
        return structuredData.entrySet()
                .stream()
                .allMatch(e -> e.getValue() != null);
    }

    static void printWrongParams(HashMap<String, String> structuredData) {
        String wrongParam = structuredData.entrySet()
                .stream()
                .filter(e -> e.getValue() == null).findFirst().orElseGet(() -> Map.entry("Ok", "Ok")).getKey();
        System.out.println("Incorrect " + wrongParam);
    }
}
