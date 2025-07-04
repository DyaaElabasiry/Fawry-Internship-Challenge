package fawry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    // A single instance of ShippingService can be used for all checkouts.
    private static final ShippingService shippingService = new ShippingService();

    /**
     * The main checkout logic that processes a customer's shopping cart.
     */
    public static void checkout(Customer customer, ShoppingCart cart) {
        System.out.println("--- Starting Checkout Process for " + customer.getName() + " ---");

        // 1. VALIDATION CHECKS
        // a) Check if cart is empty
        if (cart.isEmpty()) {
            System.out.println("Error: Cannot checkout with an empty cart.");
            return;
        }

        // b) Check for expired products
        for (Product product : cart.getItems().keySet()) {
            if (product instanceof Expirable && ((Expirable) product).isExpired()) {
                System.out.printf("Error: Product '%s' is expired and cannot be purchased.\n", product.getName());
                return;
            }
        }

        // 2. CALCULATE COSTS
        double subtotal = cart.calculateSubtotal();

        // Collect all individual shippable items for the shipping service
        List<Shippable> itemsToShip = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            if (entry.getKey() instanceof Shippable) {
                // Add the item to the list 'quantity' times
                for (int i = 0; i < entry.getValue(); i++) {
                    itemsToShip.add((Shippable) entry.getKey());
                }
            }
        }
        
        double shippingFee = shippingService.calculateShippingFee(itemsToShip);
        double totalAmount = subtotal + shippingFee;
        
        // c) Check if customer can afford the total amount
        if (!customer.canAfford(totalAmount)) {
            System.out.printf("Error: Insufficient balance. Required: %.2f, Available: %.2f\n",
                totalAmount, customer.getBalance());
            return;
        }

        // 3. PROCESS PAYMENT & FULFILLMENT
        System.out.println("Checkout validation passed. Processing payment...");

        // a) Print shipment notice
        shippingService.printShipmentNotice(itemsToShip);

        // b) Print checkout receipt
        System.out.println("\n** Checkout receipt **");
        cart.getItems().forEach((product, quantity) -> {
            System.out.printf("%dx %-15s %10.2f\n",
                quantity, product.getName(), product.getPrice() * quantity);
        });
        System.out.println("-----------------------------------");
        System.out.printf("%-18s %10.2f\n", "Subtotal", subtotal);
        System.out.printf("%-18s %10.2f\n", "Shipping", shippingFee);
        System.out.printf("%-18s %10.2f\n", "Amount", totalAmount);

        // c) Deduct from balance and update stock
        customer.deductBalance(totalAmount);
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int newQuantity = product.getQuantity() - entry.getValue();
            product.setQuantity(newQuantity);
        }

        System.out.printf("\nPayment successful. %s's new balance: %.2f\n",
            customer.getName(), customer.getBalance());
        System.out.println("--- Checkout Complete ---");
    }

    /**
     * Main method to run the simulation as described in the challenge prompt.
     */
    public static void main(String[] args) {
        // --- Setup our inventory ---
        Product cheese = new Cheese("Cheese", 100.0, 10, 0.2, LocalDate.now().plusMonths(1)); // 200g
        Product biscuits = new Product("Biscuits", 50.0, 20); // Not shippable
        Product tv = new TV("TV", 5000.0, 5, 3.0); // 3kg
        Product scratchCard = new Product("ScratchCard", 20.0, 50); // Not shippable

        // --- Setup customer and cart ---
        Customer customer = new Customer("Dyaa Elabasiry", 9000.0);
        ShoppingCart cart = new ShoppingCart();
        
        // --- Simulate adding items to the cart ---
        System.out.println("--- Populating Cart ---");
        cart.addProduct(cheese, 2);      // Requesting 2 units of cheese
        cart.addProduct(tv, 1);          // This will be grouped into one shipment notice entry with cheese
        cart.addProduct(biscuits, 3);    // This one is not shippable
        cart.addProduct(scratchCard, 1); // Not shippable
        
        // This is a custom addition to show a different product being shipped
        Product anotherCheese = new Cheese("Torki Cheese", 120.0, 8, 0.7, LocalDate.now().plusDays(20)); // 700g
        cart.addProduct(anotherCheese, 1);
        System.out.println("-----------------------");
        
        // --- Run the checkout process ---
        checkout(customer, cart);
    }
}
