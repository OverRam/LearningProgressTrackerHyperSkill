package tracker;

import java.util.HashMap;

public class DataStructuring {

    static HashMap<String, String> studentDataStructuring(String inputData) {
        HashMap<String, String> dataStructuring = new HashMap<>();
        String[] arrayData = inputData.strip().split(" ");

        String firstName = arrayData[0];
        String lastName = inputData.replaceFirst(arrayData[0], "")
                .replaceFirst(arrayData[arrayData.length - 1], "").strip();
        String email = arrayData[arrayData.length - 1];

        dataStructuring.put("FirstName", CheckData.checkFirstName(firstName) ? firstName : null);
        dataStructuring.put("LastName", CheckData.checkLastName(lastName) ? lastName : null);
        dataStructuring.put("Email", CheckData.checkEmail(email) ? email : null);

        return dataStructuring;
    }
}
