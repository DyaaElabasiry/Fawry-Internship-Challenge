package fawry;

/**
 * Represents a TV, which is a Product that is Shippable but not Expirable.
 */
public class TV extends Product implements Shippable {

    private final double weight;

    public TV(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    
    @Override
    public double getWeight() {
        return this.weight;
    }
}
