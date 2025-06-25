package Java8.entityAndDao;
import lombok.Data;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents a newly joined employee entity with highly complex nested data
 * to cover scenarios mentioned in the snapshot:
 *  - Filter new employees
 *  - Check if training activity is pending
 *  - Get email ID
 *  - Possibly send notifications, etc.
 */
@Data
public class NewEmployee {

    private String employeeId;
    private String fullName;
    private String department;
    private LocalDate dateOfJoining;
    private String email;

    /**
     * Indicates if the training is pending for this new employee
     * (as per "check if training activity is pending for Employee")
     */
    private boolean trainingPending;
    /**
     * A map containing key = TrainingModuleName, value = Some other map with details about progress or deadlines
     * Showcases a map containing another map: map -> map
     */
    private Map<String, Map<String, String>> trainingProgress;

    /**
     * A map from trainingCategory -> list of training titles under that category
     * Demonstrates a map -> list
     */
    private Map<String, List<String>> categoryToTrainings;

    /**
     * A list of maps ( e.g. each map is <"notificationType", "content">, etc.)
     * Demonstrates a list -> map
     */
    private List<Map<String, String>> notifications;

    /**
     * A map where each key is an arbitrary "attribute" and the value is a *list of maps*.
     * This is more nested complexity: map -> list -> map
     */
    private Map<String, List<Map<String, Object>>> attributes;

    // Constructor
    public NewEmployee(
            String employeeId,
            String fullName,
            String department,
            LocalDate dateOfJoining,
            String email,
            boolean trainingPending,
            Map<String, Map<String, String>> trainingProgress,
            Map<String, List<String>> categoryToTrainings,
            List<Map<String, String>> notifications,
            Map<String, List<Map<String, Object>>> attributes
    ) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.department = department;
        this.dateOfJoining = dateOfJoining;
        this.email = email;
        this.trainingPending = trainingPending;
        this.trainingProgress = trainingProgress;
        this.categoryToTrainings = categoryToTrainings;
        this.notifications = notifications;
        this.attributes = attributes;
    }

    // Getters & setters omitted for brevity (add as needed)

    @Override
    public String toString() {
        return "NewEmployee{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", department='" + department + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", email='" + email + '\'' +
                ", trainingPending=" + trainingPending +
                ", trainingProgress=" + trainingProgress +
                ", categoryToTrainings=" + categoryToTrainings +
                ", notifications=" + notifications +
                ", attributes=" + attributes +
                '}';
    }
}

/**
 * DAO that simulates fetching "NewEmployee" data from a database or external store.
 * It programmatically builds a List of 500 complex "NewEmployee" objects,
 * each containing deeply nested list/map structures.
 */
class NewEmployeeDAO {

    private static final List<String> FIRST_NAMES = Arrays.asList(
            "Alice", "Bob", "Catherine", "Daniel", "Elena", "Farhan",
            "Grace", "Hector", "Irene", "Jacob", "Kylie", "Leo", "Monica",
            "Naresh", "Olivia", "Paul", "Quincy", "Rachel", "Steven", "Tina"
    );

    private static final List<String> LAST_NAMES = Arrays.asList(
            "Anderson", "Brown", "Clarke", "Dawson", "Evans", "Fischer",
            "Green", "Hughes", "Irving", "Jackson", "Kennedy", "Larson",
            "Miller", "Norris", "Olsen", "Peterson", "Quentin", "Russell",
            "Smith", "Taylor"
    );

    private static final List<String> DEPARTMENTS = Arrays.asList(
            "HR", "Engineering", "Sales", "Marketing", "Finance", "Operations",
            "CustomerSuccess", "Research", "ITSupport"
    );

    private static final List<String> TRAINING_CATEGORIES = Arrays.asList(
            "Orientation", "Technical", "SoftSkills", "Compliance"
    );

    private static final List<String> TRAINING_MODULES = Arrays.asList(
            "JavaBasics", "Cloud101", "DevOpsIntro", "AgileMethodology",
            "CommunicationSkills", "CustomerSupportBasics", "Security",
            "ComplianceTraining", "TeamCollaboration", "DataSecurity"
    );

    /**
     * Generates 500 "NewEmployee" records, each with deeply nested structures.
     *
     * @return List of NewEmployee
     */
    public static List<NewEmployee> getAllNewEmployees() {
        List<NewEmployee> newEmployees = new ArrayList<>();

        Random random = new Random();
        int numberOfRecords = 999; // or 1000 if you prefer

        for (int i = 1; i <= numberOfRecords; i++) {

            // Build a random name
            String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
            String lastName = LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
            String fullName = firstName + " " + lastName;

            // Department
            String department = DEPARTMENTS.get(random.nextInt(DEPARTMENTS.size()));

            // Date of joining: random date in the past ~365 days
            LocalDate dateOfJoining = LocalDate.now().minusDays(random.nextInt(365) + 1);

            // Email
            String email = (firstName + "." + lastName + "." +UUID.randomUUID() +"@example.com").toLowerCase();

            // Is training pending? 50-50 chance
            boolean isTrainingPending = random.nextBoolean();

            // Build training progress: key = module name, value = map with "status", "deadline"
            Map<String, Map<String, String>> trainingProgress = new HashMap<>();
            int numModulesInProgress = random.nextInt(5) + 1; // up to 5 modules
            for (int t = 0; t < numModulesInProgress; t++) {
                String module = TRAINING_MODULES.get(random.nextInt(TRAINING_MODULES.size()));
                Map<String, String> detailsMap = new HashMap<>();
                detailsMap.put("status", (random.nextBoolean()) ? "In Progress" : "Not Started");
                detailsMap.put("deadline", LocalDate.now().plusDays(random.nextInt(30) + 1).toString());
                trainingProgress.put(module, detailsMap);
            }

            // Build category -> training modules map
            Map<String, List<String>> categoryToTrainings = new HashMap<>();
            int catCount = random.nextInt(TRAINING_CATEGORIES.size()) + 1; // up to total categories
            List<String> usedCategories = new ArrayList<>(TRAINING_CATEGORIES);
            Collections.shuffle(usedCategories);
            usedCategories = usedCategories.subList(0, catCount);
            for (String cat : usedCategories) {
                int modCount = random.nextInt(3) + 1;
                List<String> assignedModules = new ArrayList<>();
                for (int x = 0; x < modCount; x++) {
                    assignedModules.add(TRAINING_MODULES.get(random.nextInt(TRAINING_MODULES.size())));
                }
                categoryToTrainings.put(cat, assignedModules);
            }

            // Build notifications (list of maps)
            List<Map<String, String>> notifications = new ArrayList<>();
            int notifCount = random.nextInt(3); // up to 2-3 notifications
            for (int n = 0; n < notifCount; n++) {
                Map<String, String> notif = new HashMap<>();
                notif.put("type", random.nextBoolean() ? "Reminder" : "General");
                notif.put("content", "Training is " + (random.nextBoolean() ? "overdue" : "assigned"));
                notifications.add(notif);
            }

            // Build attributes (map -> list -> map)
            // e.g. key = "profile" or "preferences", value = list of maps
            Map<String, List<Map<String, Object>>> attributes = new HashMap<>();
            List<Map<String, Object>> profileList = new ArrayList<>();
            // We'll add a couple random maps to it
            Map<String, Object> profileData1 = new HashMap<>();
            profileData1.put("field", "hobbies");
            profileData1.put("value", Arrays.asList("Reading", "Music", "Travel"));
            Map<String, Object> profileData2 = new HashMap<>();
            profileData2.put("field", "experience");
            profileData2.put("value", random.nextInt(5) + " years in " + department);
            profileList.add(profileData1);
            profileList.add(profileData2);
            attributes.put("profile", profileList);

            // We can add another key, like "preferences"
            List<Map<String, Object>> preferencesList = new ArrayList<>();
            Map<String, Object> pref1 = new HashMap<>();
            pref1.put("notificationChannel", random.nextBoolean() ? "Email" : "Slack");
            pref1.put("preferredLanguage", random.nextBoolean() ? "English" : "Spanish");
            preferencesList.add(pref1);
            attributes.put("preferences", preferencesList);

            NewEmployee employee = new NewEmployee(
                    "E" + i,                   // employeeId
                    fullName,
                    department,
                    dateOfJoining,
                    email,
                    isTrainingPending,
                    trainingProgress,
                    categoryToTrainings,
                    notifications,
                    attributes
            );

            newEmployees.add(employee);
        }

        return newEmployees;
    }
}

