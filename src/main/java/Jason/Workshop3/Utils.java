package Jason.Workshop3;

import java.util.Random;

import Jason.Workshop3.model.Transaction;
import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Utils {
    public static synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numChars);
    }

    // Convert java Object to Json Object
    public static JsonObject toJsonObj(Transaction transaction) {
        JsonObject jsonObj = Json.createObjectBuilder()
                .add("transactionId", transaction.getTransactionId())
                .add("date", transaction.getDate().toString())
                .add("from_account", transaction.getFromAcc())
                .add("to_account", transaction.getToAcc())
                .add("amount", transaction.getAmount())
                .build();
        return jsonObj;
    }
}
