package fawry;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the ShoppingCart class.
 */
public class ShoppingCartTest {

    private ShoppingCart cart;
    private Product cheese;
    private Product tv;

    
    @Before
    public void setUp() {
        cart = new ShoppingCart();
        cheese = new Product("Cheese", 100.0, 10); // name, price, available quantity
        tv = new Product("TV", 5000.0, 5);
    }

    @Test
    public void testNewCartIsEmpty() {
        assertTrue("A new cart should be empty", cart.isEmpty());
        assertEquals("Subtotal of a new cart should be 0", 0.0, cart.calculateSubtotal(), 0.001);
    }

    @Test
    public void testAddProductSuccessfully() {
        cart.addProduct(cheese, 2);
        assertFalse("Cart should not be empty after adding a product", cart.isEmpty());
        assertEquals("Cart should contain 1 type of item", 1, cart.getItems().size());
        assertEquals("Quantity of cheese should be 2", 2, (int) cart.getItems().get(cheese));
    }

    @Test
    public void testAddProductMoreThanStock() {
        cart.addProduct(cheese, 11); // Stock is only 10
        assertTrue("Cart should remain empty if stock is insufficient", cart.getItems().isEmpty());
    }

    @Test
    public void testAddSameProductMultipleTimesUpdatesQuantity() {
        cart.addProduct(cheese, 2);
        cart.addProduct(cheese, 3); // Add the same product again
        assertEquals("Cart should still contain only 1 type of item", 1, cart.getItems().size());
        assertEquals("Quantity of cheese should be summed up to 5", 5, (int) cart.getItems().get(cheese));
    }

    @Test
    public void testCalculateSubtotal() {
        cart.addProduct(cheese, 2); // 2 * 100.0 = 200.0
        cart.addProduct(tv, 1);     // 1 * 5000.0 = 5000.0
        assertEquals("Subtotal should be the sum of all item prices", 5200.0, cart.calculateSubtotal(), 0.001);
    }

    @Test
    public void testAddZeroOrNegativeQuantity() {
        cart.addProduct(cheese, 0);
        assertTrue("Cart should not add items with zero quantity", cart.getItems().isEmpty());

        cart.addProduct(cheese, -1);
        assertTrue("Cart should not add items with negative quantity", cart.getItems().isEmpty());
    }
}