package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class Recipe {
    private String recipeId;
    private String name;
    private String cuisine;
    private List<String> ingredients;
    private Map<String, Object> nutritionFacts;
    private List<Map<String, List<String>>> instructions;
    private Map<String, List<Map<String, Object>>> reviews;

    public Recipe(String recipeId, String name, String cuisine, List<String> ingredients,
                  Map<String, Object> nutritionFacts, List<Map<String, List<String>>> instructions,
                  Map<String, List<Map<String, Object>>> reviews) {
        this.recipeId = recipeId;
        this.name = name;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.nutritionFacts = nutritionFacts;
        this.instructions = instructions;
        this.reviews = reviews;
    }
}

class RecipeDAO {
    private static final List<Recipe> recipeData = new ArrayList<>();

    static {
        for (int i = 1; i <= 10; i++) {
            recipeData.add(new Recipe(
                    "R" + i,
                    "Recipe" + i,
                    i % 2 == 0 ? "Italian" : "Indian",
                    Arrays.asList("Ingredient1", "Ingredient2", "Ingredient3"),
                    Map.of("calories", 200 + i * 10, "protein", 10 + i * 2, "fat", 5 + i),
                    List.of(
                            Map.of("Step 1", List.of("Chop", "Mix"), "Step 2", List.of("Cook", "Serve")),
                            Map.of("Step 3", List.of("Garnish"))
                    ),
                    Map.of(
                            "2024-01-" + i, List.of(
                                    Map.of("user", "User" + i, "rating", i % 5 + 1, "comment", "Delicious!"),
                                    Map.of("user", "User" + (i + 1), "rating", i % 4 + 1, "comment", "Could be better")
                            )
                    )
            ));
        }
    }

    public static List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipeData);
    }

    public Recipe getRecipeById(String recipeId) {
        return recipeData.stream()
                .filter(recipe -> recipe.getRecipeId().equals(recipeId))
                .findFirst()
                .orElse(null);
    }
}

