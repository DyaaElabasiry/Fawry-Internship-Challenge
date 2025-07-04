# Fawry Internship Challenge: E-commerce Simulation

This project is a simple command-line e-commerce simulation developed in Java. It models basic online shopping functionalities, including managing products, a shopping cart, and a customer checkout process. The primary goal is to showcase object-oriented programming (OOP) principles and clean code practices.

## Key Features

*   **Object-Oriented Design**: Utilizes interfaces (`Shippable`, `Expirable`), inheritance (`Cheese` and `TV` from `Product`), and polymorphism to create a flexible and extensible domain model.
*   **Product Categorization**: Differentiates between various product types:
    *   Standard products.
    *   **Shippable** products with weight-based shipping fees.
    *   **Expirable** products with an expiration date.
*   **Shopping Cart**: Allows adding products and calculates the subtotal.
*   **Customer Management**: Manages customer balance and validates affordability.
*   **Checkout Process**: A comprehensive checkout sequence that includes:
    *   Cart validation (checking for empty carts or expired items).
    *   Calculating total cost, including shipping fees.
    *   Processing payment by deducting from the customer's balance.
    *   Updating product stock.
*   **Shipping Logic**: A dedicated `ShippingService` calculates fees based on the total weight of shippable items.
*   **Unit Tests**: Includes JUnit tests for the `ShoppingCart` functionality.

## Domain Model

The project is structured around a clear and decoupled domain model, promoting separation of concerns.

*   `Product`: The base class for all items, containing common attributes like name, price, and quantity.
*   `Shippable`: An interface for products that can be shipped, requiring them to have a weight.
*   `Expirable`: An interface for products with an expiration date.
*   `Cheese`: A concrete class that extends `Product` and implements both `Shippable` and `Expirable`.
*   `TV`: A concrete class that extends `Product` and implements `Shippable`.
*   `ShoppingCart`: Holds a collection of products and their quantities for a customer.
*   `Customer`: Represents a user with a name and a balance.
*   `ShippingService`: Calculates shipping costs and handles shipment-related logic.
*   `App`: The main application class that simulates a shopping scenario and orchestrates the checkout process.

A visual representation of the class relationships can be seen in `Main.png`.

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Prerequisites

*   Java Development Kit (JDK) 17 or later.
*   Apache Maven.

### Installation & Build

1.  **Clone the repository:**
    ```sh
    git clone <repository-url>
    ```

2.  **Navigate to the project directory:**
    ```sh
    cd Fawry_Internship_Challenge/ecommerce
    ```

3.  **Compile and package the project using Maven:**
    ```sh
    mvn clean install
    ```

## Usage

To run the e-commerce simulation, execute the `main` method in the `fawry.App` class.

You can run the application directly from your IDE (like IntelliJ IDEA or Eclipse) or use Maven from the command line:

```sh
mvn compile exec:java -Dexec.mainClass="fawry.App"
```

### Sample Output

When you run the application, it will simulate a customer adding various items to their cart and proceeding to checkout. The output will show:
- Items being added to the cart.
- A detailed checkout receipt with subtotal, shipping fees, and total amount.
- A shipment notice for all shippable items.
- The customer's new balance after the transaction.

An example of the final output is available in `Output.png`.

## Running Tests

The project includes unit tests for the `ShoppingCart` class. To run these tests, use the following Maven command:

```sh
mvn test
```
This command will execute all tests located in the `src/test/java` directory. The results from testing some edge cases can be seen in `TestingEdgeCases.png`.

The tests cover scenarios such as:
*   Adding products to the cart.
*   Calculating the subtotal correctly.
*   Attempting to add more products than are available in stock.
*   Adding products with zero or negative quantities.
