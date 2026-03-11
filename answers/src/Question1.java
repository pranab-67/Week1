import java.util.*;

class UsernameChecker {
    private final Map<String, String> usernameMap = new HashMap<>();
    private final Map<String, Integer> attemptFrequency = new HashMap<>();

    public void registerUser(String username, String userId) {
        usernameMap.put(username, userId);
    }

    public boolean checkAvailability(String username) {
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
        return !usernameMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String alt = username + i;
            if (!usernameMap.containsKey(alt)) suggestions.add(alt);
        }
        if (!username.contains(".")) suggestions.add(username.replace("_", "."));
        return suggestions;
    }

    public String getMostAttempted() {
        return attemptFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}

public class Question1 {
    public static void main(String[] args) {
        UsernameChecker checker = new UsernameChecker();
        checker.registerUser("john_doe", "u1001");
        checker.registerUser("admin", "u1000");

        System.out.println(checker.checkAvailability("john_doe"));
        System.out.println(checker.checkAvailability("jane_smith"));
        System.out.println(checker.suggestAlternatives("john_doe"));
        for (int i = 0; i < 10543; i++) checker.checkAvailability("admin");
        System.out.println(checker.getMostAttempted());
    }
}