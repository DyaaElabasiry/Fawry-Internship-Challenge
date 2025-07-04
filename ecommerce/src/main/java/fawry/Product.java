package fawry;


public class Product {

    private String name;    // e.g. “Cheese”
    private double price;   // unit price
    private int quantity;   // units in stock

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /* ---------- Getters ---------- */
    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public int    getQuantity() { return quantity; }

    /* ---------- Setters ---------- */
    public void setName(String name)         { this.name = name; }
    public void setPrice(double price)       { this.price = price; }
    public void setQuantity(int quantity)    { this.quantity = quantity; }

    /* ---------- Convenience ---------- */
    @Override
    public String toString() {
        return String.format("%s (%.2f) – %d in stock", name, price, quantity);
    }
}