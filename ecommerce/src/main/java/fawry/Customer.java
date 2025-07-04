package fawry;

/**
 * Represents a customer with a name and an account balance.
 */
public class Customer {

    private final String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    
    public String getName() {
        return name;
    }

    
    public double getBalance() {
        return balance;
    }

    
    public boolean canAfford(double amount) {
        return this.balance >= amount;
    }

    
    public void deductBalance(double amount) {
        if (canAfford(amount)) {
            this.balance -= amount;
        } else {
            System.out.println("Error: Insufficient balance for payment.");
        }
    }
}
