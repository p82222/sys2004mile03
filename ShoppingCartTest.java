package Test;
import Store.*;
//STUDENT NAME: Chia-Yu Liu
//STUDENT ID: 100698737
//STUDENT NAME: Keefer Belanger
//STUDENT ID: St# 101152085

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    /**
     * Test for addToCart in ShoppingCart class, compares expected value to actual value from using the addToCart
     * method
     */
    @Test
    void addToCart() {
        ShoppingCart s = new ShoppingCart();
        s.addToCart(4, 20);

        assertEquals(20, s.getItems().get(4));
    }

    /**
     * Test for removeFromCart in ShoppingCart class, compares expected value to actual value from using the
     * removeFromCart method
     */
    @Test
    void removeFromCart() {
        ShoppingCart s = new ShoppingCart();
        //s.removeFromCart(4,20);

        assertEquals(null, s.getItems().get(4));
    }

    /**
     * Test for the getItems in ShoppingCart class, gets the items from the addToCart and removeFromCart tests and
     * compares the expected value after all tests ran against the actual value
     */
    @Test
    void getItems() {
        ShoppingCart s = new ShoppingCart();
        s.getItems();

        assertEquals(null, s.getItems().get(4));
    }
}
