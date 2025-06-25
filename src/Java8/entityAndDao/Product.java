package Java8.entityAndDao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Product {
    // Getters and setters (or use Lombok if you prefer)
    private String name;
    private String category;
    private double price;
    private int stockQuantity;

    public Product(String name, String category, double price, int stockQuantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

}

@Data
@ToString
class ProductWithNameAndPrice{
    private String name;
    private double price;

    public ProductWithNameAndPrice(String name, double price){
        this.name = name;
        this.price = price;
    }
}

class ProductDao {

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop A", "Electronics", 999.99, 4));
        products.add(new Product("Laptop B", "Electronics", 799.49, 2));
        products.add(new Product("Phone X",  "Mobile",      499.99, 0));
        products.add(new Product("Phone Y",  "Mobile",      299.99, 0));
        products.add(new Product("Shirt S",  "Clothing",    29.99, 9));
        products.add(new Product("Shirt M",  "Clothing",    35.00, 8));
        products.add(new Product("Jeans L",  "Clothing",    49.99, 4));
        return products;
    }
}

