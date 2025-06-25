package Java8.entityAndDao;

import lombok.Getter;
import java.util.Arrays;
import java.util.List;
import lombok.Setter;

@Getter
@Setter
public class Category {
    // Getters and setters
    private String categoryId;
    private String categoryName;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}

class CategoryDAO {
    public List<Category> getAllCategories() {
        return Arrays.asList(
                new Category("CAT001", "Electronics"),
                new Category("CAT002", "Furniture"),
                new Category("CAT003", "Stationery")
        );
    }
}
