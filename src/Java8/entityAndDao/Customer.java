package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Customer {
    // Getters and setters
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(String customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

class CustomerDAO {
    public List<Customer> getAllCustomers() {
        return Arrays.asList(
                new Customer("C001", "Alice Johnson", "alice.johnson@example.com", "123-456-7890"),
                new Customer("C002", "Bob Smith", "bob.smith@example.com", "234-567-8901"),
                new Customer("C003", "Charlie Brown", "charlie.brown@example.com", "345-678-9012"),
                new Customer("C004", "Diana Prince", "diana.prince@example.com", "456-789-0123")
        );
    }
}

