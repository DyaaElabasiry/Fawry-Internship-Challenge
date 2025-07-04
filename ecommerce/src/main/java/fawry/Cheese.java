package fawry;

import java.time.LocalDate;

/**
 * Represents Cheese, which is a Product that is both Shippable and Expirable.
 */
public class Cheese extends Product implements Shippable, Expirable {

    private final double weight; // in kg
    private final LocalDate expirationDate;

    public Cheese(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity); // Calls the constructor of the parent Product class
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    // --- Implementation of Shippable interface ---
    @Override
    public double getWeight() {
        return this.weight;
    }

    // --- Implementation of Expirable interface ---
    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }
}
