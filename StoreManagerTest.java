package Test;

//STUDENT NAME: Chia-Yu Liu
//STUDENT ID: 100698737
//STUDENT NAME: Keefer Belanger
//STUDENT ID: St# 101152085

import Store.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {

    private static StoreManager sm;

    private static Inventory inv1;
    private static Inventory inv2;
    private static Inventory inv3;

    private static Product p1;
    private static Product p2;
    private static Product p3;

    private static ShoppingCart cart;

    private static int cartID;

    /**
     * Sets all products and inventory quantities before running tests
     */
    @BeforeAll
    public static void init(){

        p1 = new Product("apple", 101, 1.0);
        p2 = new Product("banana", 202, 2.0);
        p3 = new Product("iPhone", 303, 3.0);

        inv1 = new Inventory(p1, 10);
        inv2 = new Inventory(p2, 20);
        inv3 = new Inventory(p3, 30);

        sm = new StoreManager();

        sm.addInventory(inv1);


        cartID = sm.assignNewCartID();

        cart = new ShoppingCart();

        cart.addToCart(101, 5);

    }

    /**
     * Tests CheckStock by comparing expected value from using the checkStock method against the actual value
     */
    @Test
    public void TestCheckStock() {
        sm.addInventory(inv1);
        assertEquals(10, sm.checkStock(101),"Either the store manager's  " +
                "checkStock method is not working or inventory cannot get quantity properly");
    }

    @Test
    public void newCart() {
    }

    /**
     * Tests addToCart by comparing expected output against actual output
     */
    @Test
    void addToCart() {
        ShoppingCart s = new ShoppingCart();
        s.addToCart(4, 20);
        assertEquals(20, s.getItems().get(4));
    }

    /**
     * Tests removeFromCart by assigning a variable to the cart and removing a specific item from the BeforeAll, and
     * comparing the expected value from doing this to the actual value from calling the method
     */
    @Test
    public void TestRemoveFromCart() {
        ShoppingCart s = new ShoppingCart();
        s = cart;
        s.removeFromCart(101,4);

        assertEquals(1, s.getItems().get(101), "Either the store manager's  " +
                "removeFromCart method is not working or shopping cart cannot add items properly");
    }

    /**
     * Tests processTransaction by using the assertEqual to compare expected output to actual output
     */
    @Test
    public void TestProcessTransaction() {
        ShoppingCart s = new ShoppingCart();
        s = cart;

        assertEquals(5.0, sm.processTransaction(s), "Either the store manager's  " +
                "processTransaction method is not working or shopping cart cannot iterate cart items properly");
    }

    /**
     * Tests quit by adding an item to the cart and quitting out of the store to check if item returns back to stock
     */
    @Test
    public void TestQuit() {
        ShoppingCart s2 = new ShoppingCart();
        s2.addToCart(202, 10);
        sm.quit(s2, cartID);

        assertEquals(0, sm.checkStock(202), "Either the store manager's  " +
                "quit method is not working or shopping cart cannot be removed properly");
    }

    /**
     * Tests getInventory by creating a new inventory and compares it to the expected Inventory
     */
    @Test
    void getInventories() {
        ArrayList<Inventory> I = new ArrayList<>();
        StoreManager m = new StoreManager();

        ArrayList<Inventory> expected = new ArrayList<>();
        assertEquals(expected, m.getInventories());
    }

    /**
     * Tests assignNewCart by assigning a new cart and comparing it to expected of an empty cart
     */
    @Test
    void assignNewCartID() {
        StoreManager m = new StoreManager();

        assertEquals(0, m.assignNewCartID());
    }
}
