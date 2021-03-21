//STUDENT NAME: Chia-Yu Liu //
//STUDENT ID: 100698737     //
//STUDENT NAME: Keefer Belanger//
//STUDENT ID: 101152085//


package Store;

import java.awt.*;
import java.util.*;


public class StoreManager {

    private HashMap<Product, Integer> products = new HashMap<Product, Integer>();
    //private int[][] cart = new int[][]{};
    private HashMap<Integer, ShoppingCart> shoppingCarts;

    private ShoppingCart cart;

    private int cardID = -1;

    private ArrayList<Inventory> inventorys = new ArrayList<>();

    /**
     * Creates a new inventory
     */
    private Inventory inventory = new Inventory();

    /**
     * Creates StoreManager constructor
     */
    public StoreManager(){
        this(new Inventory());
    }

    public StoreManager(Inventory inventory){
        this.inventory = inventory;
        shoppingCarts = new HashMap<>();
    }

    /**
     * Check how much stock of a given product is in the inventory
     *
     * @param id
     * @void the method has no return value
     *
     */
    public int checkStock(int id){
        int stock = 0;
        for(int i = 0; i< this.getInventories().size(); i++){
            for(Product p: this.getInventories().get(i).getProducts().keySet()){
                int productID = p.getId();
                String name = p.getName();
                double price = p.getPrice();
                int Quantity = this.getInventories().get(i).getQuantity(id);
                if(productID == id){
                    stock = Quantity;
                }



            }
        }
        return stock;
    }


    public void newCart(){
        cardID +=1;
        cart = new ShoppingCart();
        this.shoppingCarts.put(cardID, cart);
    }



    public String command() {
        boolean flag = true;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a command:");
        System.out.println("Options: Browse / Add / Remove / Check out / Quit");
        System.out.println("b/a/r/c/q");
        String command = keyboard.nextLine().toLowerCase();

        while (flag) {
            if (!(command.equals("b")) && !(command.equals("a")) && !(command.equals("r")) &&
                    (!(command.equals("c"))) && !(command.equals("q"))) {
                System.out.println("Please enter valid command");
                System.out.println("b/a/r/c/q");
                command = keyboard.nextLine().toLowerCase();
                flag = true;
            } else {
                flag = false;
            }

        }
        return command;
    }

    //good, tested
    public void browse(){
        StringBuilder iteminfo  = new StringBuilder();

        System.out.println("\\----------BROWSE---------/");
        System.out.println();
        System.out.println("Stock | Product Name | Unit Price" );
        for(int i = 0; i< this.getInventories().size(); i++){
            for(Product p: this.getInventories().get(i).getProducts().keySet()){
                int id = p.getId();
                String name = p.getName();
                double price = p.getPrice();
                int stock = this.getInventories().get(i).getQuantity(id);
                iteminfo.append(stock);
                iteminfo.append(" | ");
                iteminfo.append(name);
                iteminfo.append(" | ");
                iteminfo.append(price);
                iteminfo.append("\n");
            }
        }

        System.out.println(iteminfo);
    }





    //worked, tested
    public void addToCart(ShoppingCart shoppingCart){

        StringBuilder iteminfo  = new StringBuilder();
        int productID = 0;
        int Quantity = 0;
        boolean flag = true;

        System.out.println("\\----------ADD---------/");
        System.out.println();
        System.out.println("Stock | Product Name | Unit Price | Product ID" );
        for(int i = 0; i< this.getInventories().size(); i++){
            for(Product p: this.getInventories().get(i).getProducts().keySet()){
                int id = p.getId();
                String name = p.getName();
                double price = p.getPrice();
                int stock = this.getInventories().get(i).getQuantity(id);
                iteminfo.append(stock);
                iteminfo.append(" | ");
                iteminfo.append(name);
                iteminfo.append(" | ");
                iteminfo.append(price);
                iteminfo.append(" | (");
                iteminfo.append(id);
                iteminfo.append(")\n");
            }
        }

        System.out.println(iteminfo);
        System.out.println("");

        while(flag == true){
            try{
                System.out.println("Please enter Product ID");
                Scanner idKey = new Scanner(System.in);
                productID = idKey.nextInt();
                for(int i = 0; i< this.getInventories().size(); i++){
                    for(Product p: this.getInventories().get(i).getProducts().keySet()){
                        int id = p.getId();
                        if(id == productID){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag == true) {
                    System.out.println("no Product ID on the list, please try again");
                }

            }
            catch(InputMismatchException e){
                System.out.println("invalid ID value, please try again");
            }
        }



        flag = true;
        while(flag == true){
            try{
                System.out.println("Please enter quantity");
                Scanner quantityKey = new Scanner(System.in);
                Quantity = quantityKey.nextInt();
                flag = false;
                for(int i = 0; i< this.getInventories().size(); i++){
                    for(Product p: this.getInventories().get(i).getProducts().keySet()){
                        int id = p.getId();
                        int stock = this.getInventories().get(i).getQuantity(id);
                        if(id == productID){
                            if(stock >= Quantity){
                                flag = false;
                                break;
                            }
                            else{
                                System.out.println("invalid quantity value, please try again");
                                flag = true;
                            }
                        }
                    }
                }

            }
            catch (Exception e){
                System.out.println("invalid quantity value, please try again");
            }
        }

        shoppingCart.addToCart(productID, Quantity);

        for(int j = 0; j < Quantity; j++) {
            for (int i = 0; i < this.getInventories().size(); i++) {
                for (Product p : this.getInventories().get(i).getProducts().keySet()) {
                    if (p.getId() == productID) {
                        this.getInventories().get(i).removeQuantity(productID);
                    }
                }
            }
        }

    }


    public void removeFromCart(ShoppingCart shoppingCart){
        StringBuilder iteminfo  = new StringBuilder();
        boolean flag = true;
        int productID = 0;
        int Quantity = 0;

        System.out.println("\\----------REMOVE---------/");
        System.out.println();
        System.out.println("Quantity | Product Name | Unit Price | Product ID" );
        for(Map.Entry mapElement : shoppingCart.getItems().entrySet()){
            int itemID = (int) mapElement.getKey();

            int quantity = (int) mapElement.getValue();
            if(quantity == 0){
                shoppingCart.getItems().remove(mapElement);
            }

            for(int i = 0; i< this.getInventories().size(); i++){
                for(Product p: this.getInventories().get(i).getProducts().keySet()){
                    int id = p.getId();
                    if(itemID == id){
                        String name = p.getName();
                        double price = p.getPrice();

                        iteminfo.append(quantity);
                        iteminfo.append(" | ");
                        iteminfo.append(name);
                        iteminfo.append(" | ");
                        iteminfo.append(price);
                        iteminfo.append(" | (");
                        iteminfo.append(id);
                        iteminfo.append(")\n");

                    }

                }
            }


        }


        System.out.println(iteminfo);

        System.out.println("");
        while(flag == true){
            try{
                System.out.println("Please enter Product ID");
                Scanner idKey = new Scanner(System.in);
                productID = idKey.nextInt();
                //flag = false;
                for(Map.Entry mapElement : shoppingCart.getItems().entrySet()){
                    int itemID = (int) mapElement.getKey();
                    if(itemID == productID){
                        flag = false;
                        break;
                    }
                }if(flag == true){
                    System.out.println("Product ID not in the cart, please try again");
                }



            }catch(Exception e){
                System.out.println("invalid ID value, please try again");
            }
        }
        flag = true;
        while(flag == true){
            try{
                System.out.println("Please enter quantity");
                Scanner quantityKey = new Scanner(System.in);
                Quantity = quantityKey.nextInt();

                for(Map.Entry mapElement : shoppingCart.getItems().entrySet()){
                    int itemID = (int) mapElement.getKey();
                    int quantity = (int) mapElement.getValue();
                    if(itemID == productID){
                        if(quantity >= Quantity){
                            flag = false;
                            break;
                        }
                    }
                }if(flag == true){
                    System.out.println("invalid quantity in cart, please try again");
                }

            }
            catch(Exception e){
                System.out.println("invalid quantity value, please try again");
            }
        }

        shoppingCart.removeFromCart(productID, Quantity);

        for(int k = 0; k < Quantity; k++) {
            for (int i = 0; i < this.getInventories().size(); i++) {
                for (Product p : this.getInventories().get(i).getProducts().keySet()) {
                    if (p.getId() == productID) {
                        this.getInventories().get(i).addQuantity(productID);
                    }
                }
            }
        }

        //checking if the item in cart is 0, if 0 then remove it out of the cert
        for(Map.Entry mapElement : shoppingCart.getItems().entrySet()) {
            int itemID = (int) mapElement.getKey();

            int quantity = (int) mapElement.getValue();
            if (quantity == 0) {
                shoppingCart.getItems().remove(itemID);
            }
        }

    }

    /**
     * Checks the quantity exists and if it does processes the transaction
     *
     * @param cart
     * @void the method has no return value
     */
    public float processTransaction(ShoppingCart shoppingCart){
        StringBuilder iteminfo  = new StringBuilder();

        System.out.println("\\----------CHECK OUT---------/");
        System.out.println();
        System.out.println("Quantity | Product Name | Unit Price | Product ID" );
        float total = 0;

        for(Map.Entry mapElement : shoppingCart.getItems().entrySet()){

            int itemID = (int) mapElement.getKey();
            int quantity = (int) mapElement.getValue();


            for(int i = 0; i< this.getInventories().size(); i++){
                for(Product p: this.getInventories().get(i).getProducts().keySet()){
                    int id = p.getId();
                    if(itemID == id){

                        String name = p.getName();
                        double price = p.getPrice();
                        total += price * quantity;
                        iteminfo.append(quantity);
                        iteminfo.append(" | ");
                        iteminfo.append(name);
                        iteminfo.append(" | ");
                        iteminfo.append(price);
                        iteminfo.append(" | (");
                        iteminfo.append(id);
                        iteminfo.append(")\n");
                    }
                }
            }


        }
        System.out.println(iteminfo);

        return total;


    }

    public void quit(ShoppingCart shoppingCart, int cardID){

        for(Map.Entry mapElement : shoppingCart.getItems().entrySet()){
            int itemID = (int) mapElement.getKey();

            int quantity = (int) mapElement.getValue();

            for(int k = 0; k < quantity; k++) {
                for (int i = 0; i < this.getInventories().size(); i++) {
                    for (Product p : this.getInventories().get(i).getProducts().keySet()) {
                        if (p.getId() == itemID) {
                            this.getInventories().get(i).addQuantity(itemID);
                        }
                    }
                }
            }


        }
        this.shoppingCarts.remove(cardID);


    }

    //worked, tested, get products from Inventories;
    public ArrayList<Inventory> getInventories(){
        return inventorys;
    }



    public int assignNewCartID(){
        newCart();
        return cardID;
    }

    public void addInventory(Inventory newInventory){
        this.inventorys.add(newInventory);
    }

    public ShoppingCart findCart(){
        //System.out.println("CART >> ");
        ShoppingCart currentCart = new ShoppingCart();
        int cartID = 0;


        boolean flag = true;
        while(flag){
            System.out.println("CART >> ");
            boolean inputIsNum = true;
            while (inputIsNum == true) {
                try {
                    Scanner keyboard = new Scanner(System.in);
                    cartID = keyboard.nextInt();
                    inputIsNum = false;
                } catch (InputMismatchException e) {
                    System.out.println("only accept number input , please try again");
                }
            }

            for(HashMap.Entry entry : shoppingCarts.entrySet()){
                int shoppingCart = (int)entry.getKey();
                if(cartID == shoppingCart){
                    currentCart = shoppingCarts.get(shoppingCart);
                    flag = false;

                }
            }
            if(flag == true) {
                System.out.println("invalid Cart number, please try again");
            }else{
                break;
            }
        }
        System.out.println("found currentCart");
        return currentCart;
    }

}
