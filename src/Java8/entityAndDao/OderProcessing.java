package Java8.entityAndDao;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OderProcessing {

        public static void main(String[] args) {
            // Step 1: Asynchronously fetch user profile details.
            // supplyAsync starts the task in a separate thread.
            CompletableFuture<UserProfile> userProfileFuture = CompletableFuture.supplyAsync(() -> {
                simulateDelay(1000); // simulate a remote call delay
                System.out.println("User profile fetched.");
                return new UserProfile("John Doe", "NY");
            });

            // Step 2: Asynchronously fetch product details.
            CompletableFuture<Product> productFuture = CompletableFuture.supplyAsync(() -> {
                simulateDelay(1500); // simulate delay in fetching product info
                System.out.println("Product details fetched.");
                return new Product("Laptop", 1200.0);
            });

            // Step 3: Asynchronously fetch discount details.
            CompletableFuture<Double> discountFuture = CompletableFuture.supplyAsync(() -> {
                simulateDelay(1200); // simulate delay in fetching discount info
                System.out.println("Discount details fetched.");
                return 10.0; // discount percentage
            });

            // Step 4: Calculate shipping fee based on the user profile.
            // thenCompose is used because shipping fee calculation depends on the user profile result.
            CompletableFuture<Double> shippingFeeFuture = userProfileFuture.thenComposeAsync(userProfile ->
                    CompletableFuture.supplyAsync(() -> {
                        simulateDelay(800);
                        System.out.println("Shipping fee calculated for region: " + userProfile.getRegion());
                        // Calculate fee based on region
                        return userProfile.getRegion().equals("NY") ? 25.0 : 35.0;
                    })
            );

            // Step 5: Combine product details with discount details to calculate discounted price.
            // thenCombine is ideal for two independent asynchronous tasks.
            CompletableFuture<Double> discountedPriceFuture = productFuture.thenCombineAsync(discountFuture, (product, discount) -> {
                double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
                System.out.println("Discounted product price: " + discountedPrice);
                return discountedPrice;
            });

            // Step 6: Combine the discounted price with shipping fee to calculate the total cost.
            CompletableFuture<Double> totalCostFuture = discountedPriceFuture.thenCombineAsync(shippingFeeFuture, (discountedPrice, shippingFee) -> {
                double totalCost = discountedPrice + shippingFee;
                System.out.println("Total cost (price + shipping): " + totalCost);
                return totalCost;
            });

            // Step 7: Apply tax to the total cost.
            // thenApply transforms the result further.
            CompletableFuture<Double> taxedCostFuture = totalCostFuture.thenApplyAsync(totalCost -> {
                double taxRate = 0.08; // 8% tax
                double taxedCost = totalCost + (totalCost * taxRate);
                System.out.println("Final cost after tax: " + taxedCost);
                return taxedCost;
            });

            // Step 8: Handle any exceptions that may have occurred in the pipeline.
            // handle allows you to inspect the result and the exception together.
            CompletableFuture<Double> safeFinalCostFuture = taxedCostFuture.handle((result, ex) -> {
                if (ex != null) {
                    System.err.println("Error in processing order: " + ex.getMessage());
                    // Provide a fallback value in case of error.
                    return 0.0;
                }
                return result;
            });

            // Step 9: Optionally, initiate multiple promotional offers and use anyOf to take the fastest.
            CompletableFuture<String> promoOffer1 = CompletableFuture.supplyAsync(() -> {
                simulateDelay(500);
                return "Promo Offer: 5% cashback!";
            });
            CompletableFuture<String> promoOffer2 = CompletableFuture.supplyAsync(() -> {
                simulateDelay(700);
                return "Promo Offer: Free shipping!";
            });
            // anyOf completes as soon as any one of the promotions is available.
            CompletableFuture<Object> bestPromo = CompletableFuture.anyOf(promoOffer1, promoOffer2);
            String promoMessage = (String) bestPromo.join(); // join to get the fastest promotion result.
            System.out.println("Applied Promotion: " + promoMessage);

            // Step 10: Wait for all the core futures to complete using allOf.
            // This is useful if you need to ensure that all parts of the process have finished.
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                    userProfileFuture, productFuture, discountFuture, shippingFeeFuture, discountedPriceFuture, totalCostFuture, taxedCostFuture
            );
            allFutures.join(); // block until all tasks are complete

            // Step 11: Finally, consume the final cost result.
            // thenAccept is used when you simply want to process the result without returning a new value.
            safeFinalCostFuture.thenAccept(finalCost ->
                    System.out.println("Order processing complete. Final order cost: " + finalCost)
            ).join(); // join to block until the consumption is complete

            // In this pipeline, each method serves a specific purpose:
            // - supplyAsync: Initiates asynchronous tasks.
            // - thenCompose: Chains dependent asynchronous operations.
            // - thenCombine: Merges results from independent async tasks.
            // - thenApply: Transforms the result.
            // - handle: Provides error handling and fallback mechanisms.
            // - anyOf: Selects the fastest result from multiple parallel tasks.
            // - allOf: Waits for multiple asynchronous tasks to finish.
            // - thenAccept: Consumes the final result in a non-blocking fashion.
        }

        // Helper method to simulate delays in asynchronous calls.
        private static void simulateDelay(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Sample user profile class for demonstration.
        static class UserProfile {
            private final String name;
            private final String region;

            public UserProfile(String name, String region) {
                this.name = name;
                this.region = region;
            }

            public String getName() {
                return name;
            }

            public String getRegion() {
                return region;
            }
        }

        // Sample product class for demonstration.
        static class Product {
            private final String name;
            private final double price;

            public Product(String name, double price) {
                this.name = name;
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public double getPrice() {
                return price;
            }
        }
}
