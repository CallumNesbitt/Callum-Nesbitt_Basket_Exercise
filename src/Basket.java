import java.util.*;

public class Basket {
    private List<String> items;
    private List<Product> products;

    Basket(List<String> items, List<Product> products){
        this.items = items;
        this.products = products;
    }

    // Add more items to the existing basket
    public void addToBasket(){
        System.out.println("Please add items to your basket (Separated by commas)");
        Scanner scanner = new Scanner(System.in);
        String newItems = scanner.nextLine();
        //Remove "space" characters
        newItems = newItems.replace(" ", "");
        // Split the string at commas, resulting in an array of the desired items
        String[] nits = newItems.split(",");
        // Add the given items to the basket
        for(int i = 0; i < nits.length; i++){
            items.add(nits[i]);
        }
        // Check that the user has finished
        this.checkComp();
    }

    // Count the number of instances of a given item in the basket
    public int countItem(String item){
        int num = Collections.frequency(items, item);
        return(num);
    }


    public Float OOItemTotal(Product item, int n){
        // Calculate total spent on this item based on offer code
        try{
            float discount = Float.parseFloat(item.offer);
            return(n * item.price * (1 - discount));
        }
        catch(NumberFormatException e){
            switch(item.offer){
                case "tft":
                    return(((n % 3) + ((2.0f / 3.0f) * (n - (n % 3)))) * item.price);
                case "tfo":
                    return(((n % 2) + ((n - (n % 2)) / 2)) * item.price);
                default:
                    System.err.println("Invalid offer found in product \"" + item.name + "\"");
                    System.exit(1);
                    return(0f);
            }
        }
    }

    /* LEGACY // Calculate the total price of all instances of an item in the basket
    public float itemTotal(String item, int n){
        String itemInfo = null;
        String[] split = {};
        // Find the entry in "prices" corresponding to the requested item
        for(int i = 0; i < prices.size(); i++){
            if(prices.get(i).startsWith(item+",")){
                // Record its information
                itemInfo = prices.get(i);
                break;
            }
        }
        // If the item is not found, produce error
        if(itemInfo == null) {
            items.removeIf(item::equals);
            System.err.println("An item in your basket (" + item + ") is invalid and has been removed.");
            return(0.f);
        }
        // If the item is valid (has a price+offer specified) separate its information at commas
        split = itemInfo.split(", ");
        Float price = Float.parseFloat(split[1]);
        String offer = split[2];

        // Calculate total spent on this item based on offer code
        switch(offer){
            case "tft":
                return(((n % 3) + ((2.0f / 3.0f) * (n - (n % 3)))) * price);
            case "tfo":
                return(((n % 2) + ((n - (n % 2)) / 2)) * price);
            default:
                return((1 - Float.parseFloat(offer)) * price * n);
        }
    } */

    // Calculate the total price of all items in the basket
    public float price(){
        float price = 0;
        // Create list containing one of each item found in the basket
        Set<String> itemSet = new HashSet<>(items);
        List<String> uniqItems = new LinkedList<>(itemSet);
        // Calculate the total cost of each item found in the basket
        for(int i = 0; i < uniqItems.size(); i++){
            for(int j = 0; j < products.size(); j++){
                if(products.get(j).name.equals(uniqItems.get(i))){
                    price += OOItemTotal(products.get(j), countItem(uniqItems.get(i)));
                    break;
                } else if(j+1 == products.size()){
                    // If the item is not found, produce an error
                    items.removeIf(uniqItems.get(i)::equals);
                    System.out.println("An item in your basket (" + uniqItems.get(i) + ") is invalid and has been removed.");
                    System.out.println(items);
                }
            }
        }
        return(price);
    }

    // Check that the user is finished shopping
    public void checkComp(){
        System.out.println("Does this complete your order?:\n" + items);
        System.out.println("Y/N");
        Scanner scan = new Scanner(System.in);
        String ans = scan.nextLine();
        if (ans.equals("Y") || ans.equals("y")){
            return;
        }
        // If user wishes to continue, allow more items to be added
        else if(ans.equals("N") || ans.equals("n")){
            this.addToBasket();
        }
        // Exit program on any other input
        else{System.exit(1);}
    }
}
