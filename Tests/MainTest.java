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
        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Float price = basket.price();
        Assertions.assertEquals(0, price);
    }

    @Test
    void validArgs() {
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

        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Float price = basket.price();
        Assertions.assertEquals(2.35f, price);
    }

    @Test
    void invalidArgs() {
        List<String> items = new LinkedList<String>();
        items.add("Banaynay");
        items.add("Banaynay");
        List<String> exp = Arrays.asList();
        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
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
        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Float price = basket.price();
        Assertions.assertNotEquals(null, System.err);
        Assertions.assertEquals(1.05f, price);
        Assertions.assertEquals(exp, items);
    }

    @Test
    void noPrices() {
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

        List<Product> products = Arrays.asList();
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void invalidDiscount() {
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

        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "2"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void invalidOffer() {
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
        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "tlc"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void negPrice() {
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
        List<Product> products = Arrays.asList(new Product("Apple", -0.35f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void negDiscount() {
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
        List<Product> products = Arrays.asList(new Product("Apple", 0.35f, "-0.4"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    @Test
    void extraDPPrice() {
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
        List<Product> products = Arrays.asList(new Product("Apple", 0.357f, "0"), new Product("Banana", 0.20f, "0"), new Product("Melon", 0.50f, "tfo"), new Product("Lime", 0.15f, "tft"));
        Basket basket = new Basket(items, products);
        Assertions.assertNotEquals(null, System.err);
    }

    // TODO - Security manager (allow testing for attempts to "System.exit", rather than testing for non-empty "System.err")
}