//STUDENT NAME: Chia-Yu Liu
//STUDENT ID: 100698737
//STUDENT NAME: Keefer Belanger
//STUDENT ID: St# 101152085

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {

    @Test
    void checkStock() {
        StoreManager m = new StoreManager();
        m.checkStock(5);

        //Assertions.assertEquals(5, );
    }

    @Test
    void newCart() {
    }


    @Test
    void addToCart() {
        ShoppingCart s = new ShoppingCart();
        s.addToCart(4, 20);

        assertEquals(20, s.getItems().get(4));
    }

    @Test
    void removeFromCart() {
        ShoppingCart s = new ShoppingCart();
        //s.removeFromCart(4,20);

        assertEquals(null, s.getItems().get(4));
    }

    @Test
    void processTransaction() {
    }

    @Test
    void quit() {
    }

    @Test
    void getInventories() {
        ArrayList<Inventory> I = new ArrayList<>();
        StoreManager m = new StoreManager();

        ArrayList<Inventory> expected = new ArrayList<>();
        assertEquals(expected, m.getInventories());
    }

    @Test
    void assignNewCartID() {
        StoreManager m = new StoreManager();

        assertEquals(0, m.assignNewCartID());
    }

    @Test
    void addInventory() {
    }

    @Test
    void findCart() {
    }
}
