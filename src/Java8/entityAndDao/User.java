package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class User {
    // Getters and Setters
    private String userId;
    private String username;
    private String email;
    private List<String> roles;
    private Map<String, Object> preferences;
    private List<Map<String, List<String>>> permissions;
    private Map<String, List<Map<String, Object>>> activityLog;

    public User(String userId, String username, String email, List<String> roles,
                Map<String, Object> preferences, List<Map<String, List<String>>> permissions,
                Map<String, List<Map<String, Object>>> activityLog) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.preferences = preferences;
        this.permissions = permissions;
        this.activityLog = activityLog;
    }
}

class UserDAO {
    private static final List<User> userData = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            userData.add(new User(
                    "U" + i,
                    "User" + i,
                    "user" + i + "@example.com",
                    Arrays.asList("USER", i % 2 == 0 ? "ADMIN" : "GUEST"),
                    Map.of("theme", i % 2 == 0 ? "dark" : "light", "notifications", i % 3 == 0),
                    List.of(
                            Map.of("READ", List.of("DASHBOARD", "PROFILE"), "WRITE", List.of("SETTINGS")),
                            Map.of("EXECUTE", List.of("SCRIPTS"))
                    ),
                    Map.of(
                            "2024-01-" + i, List.of(
                                    Map.of("action", "LOGIN", "status", "SUCCESS"),
                                    Map.of("action", "UPDATE", "status", "FAILED", "field", "PASSWORD")
                            )
                    )
            ));
        }
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userData);
    }

    public User getUserById(String userId) {
        return userData.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }
}

