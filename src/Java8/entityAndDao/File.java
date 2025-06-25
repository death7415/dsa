package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
@ToString
public class File {
    private String fileId;
    private String fileName;
    private long fileSize;
    private String fileType;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Map<String, String> metadata;
    private List<String> tags;
    private List<Permission> permissions;
    private Map<String, List<Version>> versionHistory;
    private Location location;

    public File(String fileId, String fileName, long fileSize, String fileType, LocalDateTime createdDate, LocalDateTime modifiedDate, Map<String, String> metadata, List<String> tags, List<Permission> permissions, Map<String, List<Version>> versionHistory, Location location) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileType = fileType;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.metadata = metadata;
        this.tags = tags;
        this.permissions = permissions;
        this.versionHistory = versionHistory;
        this.location = location;
    }

    @Getter
    @Setter
    @ToString
    public static class Permission {
        private String userId;
        private String accessLevel;

        public Permission(String userId, String accessLevel) {
            this.userId = userId;
            this.accessLevel = accessLevel;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Version {
        private String versionId;
        private LocalDateTime versionDate;
        private String changes;

        public Version(String versionId, LocalDateTime versionDate, String changes) {
            this.versionId = versionId;
            this.versionDate = versionDate;
            this.changes = changes;
        }
    }


    @Getter
    @Setter
    @ToString
    public static class Location {
        private String locationId;
        private String country;
        private String city;
        private String address;
        private Map<String, Double> coordinates;
        private List<String> nearbyLandmarks;

        public Location(String locationId, String country, String city, String address, Map<String, Double> coordinates, List<String> nearbyLandmarks) {
            this.locationId = locationId;
            this.country = country;
            this.city = city;
            this.address = address;
            this.coordinates = coordinates;
            this.nearbyLandmarks = nearbyLandmarks;
        }
    }


}

class FileManagerDAO {
    private static final List<File> fileManagerList = new ArrayList<>();
    private static final Random random = new Random();

    // Sample arrays for random choices
    private static final String[] OWNERS = {
            "alice", "bob", "charlie", "dave", "eve", "frank", "grace", "heidi",
            "ivan", "judy", "mallory", "oscar", "peggy", "trent", "victor", "walter"
    };

    private static final String[] CATEGORIES = {
            "finance", "hr", "it", "marketing", "sales", "engineering", "legal", "operations"
    };

    private static final String[] TAG_POOL = {
            "important", "confidential", "shared", "backup", "archived", "urgent",
            "review", "approved", "pending", "final", "draft", "obsolete"
    };

    private static final String[] FILE_TYPES = {
            "application/pdf", "image/jpeg", "text/plain", "application/msword",
            "application/vnd.ms-excel", "application/vnd.ms-powerpoint", "application/zip", "audio/mpeg", "video/mp4"
    };

    private static final String[] PERMISSION_ACCESS = {
            "READ", "WRITE", "EXECUTE", "DELETE", "SHARE", "MODIFY"
    };


    static {
        for (int i = 1; i <= 999; i++) {
            // Random metadata values
            Map<String, String> metadata = new HashMap<>();
            String owner = OWNERS[random.nextInt(OWNERS.length)];
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            metadata.put("owner", owner);
            metadata.put("category", category);

            metadata.get("category");

            // Randomly select tags (random number of tags from TAG_POOL)
            List<String> tags = new ArrayList<>();
            int numTags = random.nextInt(TAG_POOL.length) + 1; // at least 1 tag
            Collections.shuffle(Arrays.asList(TAG_POOL));
            for (int j = 0; j < numTags; j++) {
                tags.add(TAG_POOL[j]);
            }

            // Create random permissions list (for simplicity, 2 permissions)
            List<File.Permission> permissions = Arrays.asList(
                    new File.Permission("user" + (char) ('A' + random.nextInt(5)), PERMISSION_ACCESS[random.nextInt(PERMISSION_ACCESS.length)]),
                    new File.Permission("user" + (char) ('F' + random.nextInt(5)), PERMISSION_ACCESS[random.nextInt(PERMISSION_ACCESS.length)])
            );

            // Create random version history
            Map<String, List<File.Version>> versionHistory = new HashMap<>();
            // For two version groups v1.0 and v2.0
            versionHistory.put("v1.0", Arrays.asList(
                    new File.Version("v1.0." + (random.nextInt(5) + 1), randomDate(), "Initial upload"),
                    new File.Version("v1.0." + (random.nextInt(5) + 6), randomDate(), "Metadata update")
            ));
            versionHistory.put("v2.0", Arrays.asList(
                    new File.Version("v2.0." + (random.nextInt(5) + 1), randomDate(), "Bug fixes"),
                    new File.Version("v2.0." + (random.nextInt(5) + 6), randomDate(), "Performance improvements")
            ));

            // Random coordinates
            Map<String, Double> coordinates = new HashMap<>();
            double latitude = 37.0 + random.nextDouble() * 10;   // random latitude between 37 and 47
            double longitude = -122.0 - random.nextDouble() * 10; // random longitude between -122 and -132
            coordinates.put("latitude", latitude);
            coordinates.put("longitude", longitude);
            List<String> nearbyLandmarks = Arrays.asList("Landmark A", "Landmark B", "Landmark C");

            // Create a random location (for simplicity, city and country can be randomized from index)
            File.Location location = new File.Location(
                    "loc" + i,
                    "Country" + (random.nextInt(5) + 1),
                    "City" + (random.nextInt(10) + 1),
                    "Address" + i,
                    coordinates,
                    nearbyLandmarks
            );

            // Generate random file attributes
            String fileType = FILE_TYPES[random.nextInt(FILE_TYPES.length)];
            String fileId = "file" + i;
            var extension = fileType.split("/");
            String fileName = "document" + i + "." + extension[1];
            long fileSize = (long) (random.nextDouble() * 10000) + 24;  // size between 1KB and ~10KB
            LocalDateTime createdDate = randomLocalDateTime();
            LocalDateTime modifiedDate = createdDate.plusHours(random.nextInt(100)); // modified some hours after creation

            fileManagerList.add(new File(
                    fileId,
                    fileName,
                    fileSize,
                    fileType,
                    createdDate,
                    modifiedDate,
                    metadata,
                    tags,
                    permissions,
                    versionHistory,
                    location
            ));
        }
    }

    // Helper to generate random LocalDateTime within the past year
    private static LocalDateTime randomLocalDateTime() {
        long minDay = LocalDateTime.now().minusDays(365).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long maxDay = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofInstant(new Date(randomMillisSinceEpoch).toInstant(), ZoneId.systemDefault());
    }

    // Helper to generate random Date using similar technique
    private static LocalDateTime randomDate() {
        long minDay = System.currentTimeMillis() - 365L * 24 * 60 * 60 * 1000;
        long maxDay = System.currentTimeMillis();
        long randomMillisSinceEpoch = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(randomMillisSinceEpoch),
                ZoneId.systemDefault()
        );
    }

    // Helper to randomly choose a file extension from a fileType
    private static String randomFileExtension(String[] fileTypes) {
        // For simplicity, extract extension from fileType mapping
        // E.g., if fileType contains "pdf", "jpeg", "txt", "doc"
        String[] extensions = {"pdf", "jpeg", "txt", "doc"};
        return extensions[random.nextInt(extensions.length)];
    }

    public static List<File> getAllFiles() {
        return fileManagerList;
    }

    public File getFileById(String fileId) {
        return fileManagerList.stream()
                .filter(file -> file.getFileId().equals(fileId))
                .findFirst()
                .orElse(null);
    }
}