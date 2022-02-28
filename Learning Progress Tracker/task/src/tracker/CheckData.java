package tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class CheckData {
    static boolean checkFirstName(String firstName) {
        String firstNamePattern = "^[A-z]+[-']?[A-z]+$";
        return checkDataWithPattern(firstName, firstNamePattern);
    }

    static boolean checkLastName(String lastname) {
        String lastNamePattern = "^([A-z]+ ?([-']?[A-z]+)*[A-Z]*)+$";
        return checkDataWithPattern(lastname, lastNamePattern);
    }

    static boolean checkEmail(String email) {
        String emailPattern = "^([\\w\\.-]+)@([\\w&^_]+)+\\.[a-z0-9]+$";
        return checkDataWithPattern(email, emailPattern);
    }

    private static boolean checkDataWithPattern(String data, String pattern) {
        return data.length() > 1 && Pattern.compile(pattern).matcher(data).find();
    }

    static boolean nullCheckerStructure(HashMap<String, String> structuredData) {
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

    static boolean checkNullInStructuredData(HashMap<String, String> studentData) {
        return studentData.entrySet().stream().allMatch(Objects::nonNull);
    }

    static boolean isBack(String str) {
        return str.equalsIgnoreCase("back");
    }
}
