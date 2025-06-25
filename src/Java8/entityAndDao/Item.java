package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@ToString
public class Item {
    private int id;
    private String name;
    private double price;
    private boolean discounted;
    private boolean inStock;

    public Item(int id, String name, double price, boolean discounted, boolean inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discounted = discounted;
        this.inStock = inStock;
    }
}

@Getter
@Setter
@ToString
class Order {
    private int orderId;
    private List<Item> items;
    private double totalPrice;
    private LocalDate orderDate;
    private String customerName;

    public Order(int orderId, List<Item> items, double totalPrice, LocalDate orderDate, String customerName) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

}

class OrderDao {

    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        // Sample Items
        Item item1 = new Item(101, "USB Cable",    5.99, true,  true);
        Item item2 = new Item(102, "HDMI Cable",   8.99, false, true);
        Item item3 = new Item(103, "Mouse",        15.99, true, true);
        Item item4 = new Item(104, "Keyboard",     29.99, false, true);
        Item item5 = new Item(105, "Monitor",      199.99, true, false);
        Item item6 = new Item(106, "Headphones",   49.99, false, true);

        orders.add(new Order(
                1,
                Arrays.asList(item1, item2),
                14.98,
                LocalDate.of(2025, 1, 10),
                "Alice"
        ));

        orders.add(new Order(
                2,
                Arrays.asList(item3),
                15.99,
                LocalDate.of(2025, 1, 11),
                "Bob"
        ));

        orders.add(new Order(
                3,
                Arrays.asList(item4, item5),
                229.98,
                LocalDate.of(2025, 1, 12),
                "Carol"
        ));

        orders.add(new Order(
                4,
                Arrays.asList(item5, item6),
                249.98,
                LocalDate.of(2025, 1, 13),
                "David"
        ));

        orders.add(new Order(
                5,
                Arrays.asList(item2, item4),
                38.98,
                LocalDate.of(2025, 1, 14),
                "Eve"
        ));

        return orders;
    }
}


