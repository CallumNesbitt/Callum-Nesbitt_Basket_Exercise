import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Item price+offer must follow the format "ItemName, price, discount/offer Code"
        List<String> prices = Arrays.asList("Apple, 0.35, 0", "Banana, 0.20, 0", "Melon, 0.50, tfo", "Lime, 0.15, tft");
        // Check prices are given
        try{
            String t = prices.get(0);
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.err.println("Missing prices.");
            System.exit(1);
        }
        // We define our list of valid offer codes
        List<String> validOffers = Arrays.asList("tft"/* three-for-two*/, "tfo"/*two-for-one*/);
        checkPrices(prices, validOffers);
        LinkedList<String> items = new LinkedList<>();
        // If command line args given, add them to the "items" list
        if(args.length > 0){
            items.addAll(Arrays.asList(args));
        }
        // Create a new basket containing any items given in the command line which uses the specified prices
        Basket basket = new Basket(prices, items);
        // If no command line args given, request items are added to the basket
        if(args.length == 0){
            System.out.println("Your basket is empty");
            basket.addToBasket();
        }
        else {
            // Check if finished adding items to the basket
            basket.checkComp();
        }
        // Calculate and display the total price of the basket
        float price = basket.price();
        System.out.printf("\nBasket total: £%.2f\n", price);
    }

    // Checks the list of item prices and offers for any invalid entries
    public static void checkPrices(List<String> prices, List<String> validOffers){
        float price;
        float discount;
        List<String> products = new ArrayList<>();
        // For every element in the "prices" list do the following:
        for(int i = 0; i < prices.size(); i++){
            String str = prices.get(i);
            String[] parts = str.split(",");
            parts[2] = parts[2].replace(" ", "");
            products.add(parts[0]);
            // 1. Check that the price is a valid number
            try{
                price = Float.parseFloat(parts[1]);
                // i. Check that the price is a positive value
                if(price < 0){
                    System.err.println("Negative price found in \"" + prices.get(i) + "\"");
                    System.exit(1);
                }
                // ii. Check that at most 2 decimal places are given in the price
                String[] priceParts = parts[1].split("\\.");
                String pennies = priceParts[1];
                if(pennies.length() > 2){
                    System.err.println("Invalid price found in \"" + prices.get(i) + "\"");
                    System.exit(1);
                }
            }
            catch(NumberFormatException e){
                System.err.println("Invalid price found in \"" + prices.get(i) + "\"");
                System.exit(1);
            }
            // 2. Check whether the offer is a numeric, record it in the "discount" variable if so
            try{
                discount = Float.parseFloat(parts[2]);
                // i. Check that the discount is between 0 and 100%
                if(discount < 0 || discount > 1){
                    System.err.println("Invalid discount found in \"" + prices.get(i) + "\"");
                    System.exit(1);
                }
            }
            // 3. If not, check that it is a valid offer code
            catch(NumberFormatException e){
                if(!(validOffers.contains(parts[2]))){
                    System.err.println("Invalid offer code found in \"" + prices.get(i) + "\"");
                    System.exit(1);
                }
            }
        }
        // Check for duplicate definitions of price/offer for a single item
        // Convert list of items in "prices" list to set, check lengths are equal
        Set<String> prodSet = new HashSet<>(products);
        if(prodSet.size() != products.size()){
            System.err.println("Duplicate product price found");
            System.exit(1);
        }
    }
}

// TODO - (Optional) Allow for quick multiples of items (eg "10xApples" rather than "Apple" 10 times)
// TODO - (Optional) Make case/syntax-insensitive