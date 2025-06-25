package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Session {
    private String sessionId;
    private String userId;
    private Date createdAt;
    private Date expiresAt;
    private Map<String, Object> metadata;
    private List<String> activeDevices;
    private List<Map<String, List<String>>> accessHistory;
    private Map<String, List<Map<String, Object>>> permissions;

    public Session(String sessionId, String userId, Date createdAt, Date expiresAt, Map<String, Object> metadata,
                   List<String> activeDevices, List<Map<String, List<String>>> accessHistory,
                   Map<String, List<Map<String, Object>>> permissions) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.metadata = metadata;
        this.activeDevices = activeDevices;
        this.accessHistory = accessHistory;
        this.permissions = permissions;
    }

    // Getters and Setters
}

class SessionDAO {
    private static final List<Session> sessions = new ArrayList<>();

    static {
        for (int i = 1; i <= 12; i++) {
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("ip", "192.168.1." + i);
            metadata.put("location", "Location" + i);
            metadata.put("deviceType", "Device" + (i % 3));

            List<String> activeDevices = Arrays.asList("DeviceA", "DeviceB", "DeviceC");

            List<Map<String, List<String>>> accessHistory = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                Map<String, List<String>> accessMap = new HashMap<>();
                accessMap.put("2025-01-" + j, Arrays.asList("Login", "Logout"));
                accessHistory.add(accessMap);
            }

            Map<String, List<Map<String, Object>>> permissions = new HashMap<>();
            for (int k = 1; k <= 2; k++) {
                List<Map<String, Object>> permList = new ArrayList<>();
                for (int l = 1; l <= 2; l++) {
                    Map<String, Object> perm = new HashMap<>();
                    perm.put("resource", "Resource" + l);
                    perm.put("accessLevel", l % 2 == 0 ? "READ" : "WRITE");
                    permList.add(perm);
                }
                permissions.put("Role" + k, permList);
            }

            sessions.add(new Session(
                    "session-" + i,
                    "user-" + i,
                    new Date(),
                    new Date(System.currentTimeMillis() + 3600000),
                    metadata,
                    activeDevices,
                    accessHistory,
                    permissions
            ));
        }
    }

    public static List<Session> getAllSessions() {
        return sessions;
    }
}

