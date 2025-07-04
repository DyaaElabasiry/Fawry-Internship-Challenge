package fawry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a customer's shopping cart.
 * It holds products and the desired quantity for each.
 */
public class ShoppingCart {

    private final Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new HashMap<>();
    }

    
    public void addProduct(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            System.out.println("Error: Invalid product or quantity.");
            return;
        }

        
        if (quantity > product.getQuantity()) {
            System.out.printf("Error: Not enough stock for %s. Available: %d, Requested: %d\n",
                product.getName(), product.getQuantity(), quantity);
            return;
        }

        
        items.put(product, items.getOrDefault(product, 0) + quantity);
        System.out.printf("Added %d x %s to cart.\n", quantity, product.getName());
    }

    
    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    
    public double calculateSubtotal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
