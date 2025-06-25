package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class Transaction {
    // Getters and setters
    private String transactionId;
    private String userId;
    private double amount;
    private LocalDateTime timestamp;
    private String status; // e.g., "SUCCESS", "FAILED", "PENDING"

    public Transaction(String transactionId, String userId, double amount, LocalDateTime timestamp, String status) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
        this.status = status;
    }

}


class TransactionDAO {
    public static List<Transaction> getAllTransactions() {
        return Arrays.asList(
                new Transaction("T001", "U001", 250.75, LocalDateTime.of(2025, 1, 10, 14, 30), "SUCCESS"),
                new Transaction("T002", "U002", 125.50, LocalDateTime.of(2025, 1, 11, 9, 15), "FAILED"),
                new Transaction("T003", "U001", 300.00, LocalDateTime.of(2025, 1, 12, 16, 45), "PENDING"),
                new Transaction("T004", "U003", 450.25, LocalDateTime.of(2025, 1, 13, 11, 0), "SUCCESS"),
                new Transaction("T005", "U002", 500.00, LocalDateTime.of(2025, 1, 14, 13, 20), "SUCCESS")
        );
    }
}
