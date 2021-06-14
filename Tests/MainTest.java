import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class MainTest {

    @Test
    void noArgs() {
        List<String> items = new LinkedList<String>();
        List<String> prices = Arrays.asList("Apple, 0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");

        Basket basket = new Basket(prices, items);
        Float price = basket.price();
        Assertions.assertEquals(0, price);
    }

    @Test
    void validArgs() {
        List<String> prices = Arrays.asList("Apple, 0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Float price = basket.price();
        Assertions.assertEquals(2.35f, price);
    }

    @Test
    void invalidArgs() {
        List<String> items = new LinkedList<String>();
        items.add("Banaynay");
        items.add("Banaynay");
        List<String> prices = Arrays.asList("Apple, 0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");

        List<String> exp = Arrays.asList();

        Basket basket = new Basket(prices, items);
        Float price = basket.price();
        Assertions.assertEquals(exp, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void mixedArgs() {
        List<String> items = new LinkedList<String>();
        items.add("Banaynay");
        items.add("Banana");
        items.add("Apple");
        items.add("Lyme");
        items.add("Lyme");
        items.add("Lemon");
        items.add("Melon");

        List<String> exp = Arrays.asList("Banana", "Apple", "Melon");

        List<String> prices = Arrays.asList("Apple, 0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");

        Basket basket = new Basket(prices, items);
        Float price = basket.price();
        Assertions.assertNotEquals(null, System.err);
        Assertions.assertEquals(1.05f, price);
        Assertions.assertEquals(exp, items);
    }

    @Test
    void noPrices() {
        List<String> prices = Arrays.asList();
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void invalidPrice() {
        List<String> prices = Arrays.asList("Apple, 0.a35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void invalidDiscount() {
        List<String> prices = Arrays.asList("Apple, 0.35, 2", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void invalidOffer() {
        List<String> prices = Arrays.asList("Apple, 0.35, tlc", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void negPrice() {
        List<String> prices = Arrays.asList("Apple, -0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void negDiscount() {
        List<String> prices = Arrays.asList("Apple, 0.35, -0.5", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void extraDPPrice() {
        List<String> prices = Arrays.asList("Apple, 0.357, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        List<String> items = new LinkedList<String>();
        items.add("Apple");
        items.add("Banana");
        items.add("Apple");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Lime");
        items.add("Melon");
        items.add("Melon");
        items.add("Melon");

        Basket basket = new Basket(prices, items);
        Assertions.assertNotEquals(null, System.err);
    }

    // TODO - Security manager (allow testing for attempts to "System.exit", rather than testing for non-empty "System.err")
}