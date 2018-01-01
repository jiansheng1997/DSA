package client;
import ADT.ListInterface;
import ADT.StackImplementation;
import ADT.StackInterface;
import entity.Affiliate;
import entity.Menu;
import java.util.Scanner;
import static client.FoodDeliveryService.affiliateList;
import static client.FoodDeliveryService.menuList;


public class ModuleA {

                    FoodDeliveryService FCS=new FoodDeliveryService();
    StackInterface<Menu> menuStack = new StackImplementation<>();
    public ModuleA(){
    }
    
    public void menu(){

        String backMain;
 
        //System.out.println(run.menuList.CheapestFood(menuList));
        Scanner scanner1 = new Scanner(System.in);
        do{
            System.out.println("Please select the following: ");
            System.out.println("1. Register as affiliate");
            System.out.println("2. Add item to menu");
            System.out.println("3. Remove item from menu");
            System.out.println("4. Update item from menu");
            System.out.println("5. Display menu in descending price order");
            System.out.println("0. Back to Main Menu\n");            
            System.out.print("Your choice: ");
            String selection = scanner1.nextLine();
            while(!selection.matches("[0-5]") || selection.trim().isEmpty()){
                System.out.print("Your choice: ");
                selection = scanner1.nextLine();
            }
            int choice = Integer.parseInt(selection);
            switch (choice) {
                case 0: 
                    FCS.MainMenu();
                    break;
                case 1:
                    registerAsAffiliate();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    removeItem();
                    break;
                case 4:
                    updateItem();
                    break;
                case 5:
                    displayPriceInDescending(menuList);
                    break;

            }
            if(choice==0){
                break;
            }
            Scanner scanner2 = new Scanner(System.in);
            System.out.print("Back to main menu (y/n)? ");
            backMain = scanner2.nextLine();
            backMain = backMain.toLowerCase();
            while(!backMain.matches("[yn]") || backMain.trim().isEmpty()){ 
                System.out.print("Please decide! Back to main menu (y/n)? ");
                backMain = scanner2.nextLine();
                backMain = backMain.toLowerCase();
            }
        } while(backMain.charAt(0) == 'y');
        
    }                      //main program to run
    
    public void registerAsAffiliate(){                           
        Scanner scanner2 = new Scanner(System.in); 
        System.out.print("Please enter owner name: ");
        String ownerName = scanner2.nextLine();
        while(!ownerName.matches("[A-Za-z ]+") || ownerName.trim().isEmpty()){
            System.out.print("Alphabets only!\nPlease enter owner name: ");
            ownerName = scanner2.nextLine();
        }
        scanner2.reset();
        System.out.print("Please enter contact number: ");
        String contactNo = scanner2.nextLine();
        while(!contactNo.matches("\\d{10,11}") || contactNo.trim().isEmpty()){
            System.out.print("10 or 11 digits only!\nPlease enter contact number: ");
            contactNo = scanner2.nextLine();
        }
        scanner2.reset();
        System.out.print("Please enter your IC number: ");
        String ICNumber = scanner2.nextLine();
        while(!ICNumber.matches("\\d{12}") || ICNumber.trim().isEmpty()){
            System.out.print("12 Digits only!\nPlease enter your IC number: ");
            ICNumber = scanner2.nextLine();
        }
        scanner2.reset();
        System.out.print("Please enter your email address: ");
        String email = scanner2.nextLine();
        while(!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}") || email.trim().isEmpty()){
            System.out.print("Invalid format!\nPlease enter your email address: ");
            email = scanner2.nextLine();
        }
        scanner2.reset();
        System.out.print("Please enter your restaurant name: ");
        String restaurantName = scanner2.nextLine();
        while(restaurantName.trim().isEmpty() || affiliateList.findSame(restaurantName)){                                                               
            if (restaurantName.trim().isEmpty())
                System.out.print("Please enter a correct restaurant name!\nPlease enter your restaurant name: ");
            else if (affiliateList.findSame(restaurantName))
                System.out.print("The restaurant already exists. Please enter another name. Please enter your restaurant name: ");
            restaurantName = scanner2.nextLine();
        }
        String affiliateID = "A000"+(affiliateList.getNumberOfEntries()+1);
        affiliateList.add(new Affiliate(affiliateID,ownerName, contactNo, ICNumber, email, restaurantName));
        System.out.println("\nYou are successfully registered as an affiliate of Fastest Delivery!\nYour affiliate ID is " +affiliateID+ ".\n\n");
        System.out.println(affiliateList.toString());
    }                          //register as affiliate
    
    public void addItem(){                                             
        String foodID;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your affiliate ID: ");
        String affiliateID = scanner.nextLine();
        while(!affiliateList.findSame(affiliateID)){                                 
            System.out.print("ID not found. Enter registered affiliate ID: ");
            affiliateID = scanner.nextLine();
        }
        scanner.reset();
        Affiliate currentAffiliate = affiliateList.retrieveInstance(affiliateID);
        System.out.println("\nHi, you are " + currentAffiliate.getOwnerName() + " of " + currentAffiliate.getRestaurantName());
        do{
        System.out.println("Your current menu: ");
        System.out.print(menuList.listAllMenuOfThatAffiliate(currentAffiliate.getAffiliateID()));
        System.out.print("Please enter food ID that you want to add(Suggested: " +menuList.suggest()+"): ");
        foodID = scanner.nextLine();
        while(!foodID.matches("F\\d{4}") || foodID.trim().isEmpty()){
                System.out.print("Incorrect format!\nPlease enter food id that you want to add: ");
                foodID = scanner.nextLine();
            }
            if (menuList.findSame(foodID)) {           
                System.out.println("\n" + foodID + " already exist. Please enter another number.\n");
            }
        }while (menuList.findSame(foodID));            
        scanner.reset();
        System.out.print("Please enter food name: ");
        String foodName = scanner.nextLine();
        while(!foodName.matches("[A-Za-z ]+") || foodName.trim().isEmpty() || menuList.findSameFood(foodName, currentAffiliate.getAffiliateID())){              //i made changes from here
            if (!foodName.matches("[A-Za-z ]+") || foodName.trim().isEmpty())
                System.out.print("Alphabets only!\nPlease enter food name: ");
            else if (menuList.findSameFood(foodName, currentAffiliate.getAffiliateID()))
                System.out.print("The food name already exist. Please try another name. Please enter food name: ");
            foodName = scanner.nextLine();
        }
        scanner.reset();
        System.out.print("Please enter the price: RM ");
        String priceString = scanner.nextLine();
        while(!isDouble(priceString) || priceString.trim().isEmpty() || Double.parseDouble(priceString) <= 0 ){
            System.out.print("Please enter a valid format!\nPlease enter the price: RM ");
            priceString = scanner.nextLine();
        }
        double price = Double.parseDouble(priceString);
        scanner.reset();
        System.out.print("Enter a description for the item: ");
        String description = scanner.nextLine();
        while(description.trim().isEmpty()){
            System.out.print("Blank is not allowed!\nEnter a description for the item: ");
            description = scanner.nextLine();
        }
        scanner.reset();
        System.out.print("Please enter the calories(kcal): ");
        String caloriesString = scanner.nextLine();
        while(!isInt(caloriesString) || caloriesString.trim().isEmpty() || Integer.parseInt(caloriesString) <= 0 ){
            System.out.print("Please enter a valid format!\nPlease enter the calories(kcal): ");
            caloriesString = scanner.nextLine();
        }
        int calories = Integer.parseInt(caloriesString);
        scanner.reset();
        System.out.print("Enter promotional info for the item: ");
        String promoInfo = scanner.nextLine();
        while(promoInfo.trim().isEmpty()){
            System.out.print("Blank is not allowed!\nEnter promotional info for the item: ");
            promoInfo = scanner.nextLine();
        }
        menuList.add(new Menu(foodID, foodName, price, description, calories, promoInfo, "Available", currentAffiliate.getAffiliateID()));
        System.out.println("\nYour current menu: ");
        System.out.print(menuList.listAllMenuOfThatAffiliate(currentAffiliate.getAffiliateID()));

    }                                       //add item to new menu 
    
    public void removeItem(){                                      
        String foodID;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your affiliate ID: ");
        String affiliateID = scanner.nextLine();
        while(!affiliateList.findSame(affiliateID)){
            System.out.print("ID not found. Enter registered affiliate ID: ");
            affiliateID = scanner.nextLine();
        }
        scanner.reset();
        if (!menuList.isParticularEmpty(affiliateID)) {
            Affiliate currentAffiliate = affiliateList.retrieveInstance(affiliateID);
            System.out.println("\nHi, you are " + currentAffiliate.getOwnerName() + " of " + currentAffiliate.getRestaurantName());
            System.out.println("Your current menu: ");
            System.out.print(menuList.listAllMenuOfThatAffiliate(currentAffiliate.getAffiliateID()));
            System.out.print("Please enter food ID to remove: ");
            foodID = scanner.nextLine();
            while(!foodID.matches("F\\d{4}") || foodID.trim().isEmpty()){
                    System.out.print("Incorrect format!\nPlease enter food ID to remove: ");
                    foodID = scanner.nextLine();
            }
            int count = 0;
            Menu removedItem = null;
            do{
                for (int i = 1 ; i <= menuList.getNumberOfEntries() ; i++){
                    if(foodID.equals(menuList.get(i).getFoodID()) && menuList.get(i).getAffiliateID().equals(currentAffiliate.getAffiliateID())){
                        removedItem = menuList.remove(i);
                        count++;
                    }
                }
                if (count == 1){
                    System.out.println("You have removed:\n\t" + removedItem.toString());
                }
                else{
                    System.out.print("No such item!\tPlease enter food ID to remove: ");
                    scanner = new Scanner(System.in);
                    foodID = scanner.nextLine();
                }
           }while(count == 0);
       }
        else                                                                                                                                           
            System.out.println("You do not have any food yet.\n");
    }                                    //remove item from menu
    
    public void updateItem(){
        String foodID;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your affiliate ID: ");
        String affiliateID = scanner.nextLine();
        while(!affiliateList.findSame(affiliateID)){
            System.out.print("ID not found. Enter registered affiliate ID: ");
            affiliateID = scanner.nextLine();
        }
        scanner.reset();
        if (!menuList.isParticularEmpty(affiliateID)) {
            Affiliate currentAffiliate = affiliateList.retrieveInstance(affiliateID);
            System.out.println("\nHi, you are " + currentAffiliate.getOwnerName() + " of " + currentAffiliate.getRestaurantName());
            System.out.println("Your current menu: ");
            System.out.print(menuList.listAllMenuOfThatAffiliate(currentAffiliate.getAffiliateID()));
            System.out.print("Please enter food ID to update: ");
            foodID = scanner.nextLine();
            while(!foodID.matches("F\\d{4}") || foodID.trim().isEmpty()){
                    System.out.print("Incorrect format!\nPlease enter food ID to update: ");
                    foodID = scanner.nextLine();
            }
            scanner.reset();
            int count = 0;
            do{
                Menu itemToUpdate = null;
                for (int i = 1 ; i <= menuList.getNumberOfEntries() ; i++){
                    if(foodID.equals(menuList.get(i).getFoodID()) && menuList.get(i).getAffiliateID().equals(currentAffiliate.getAffiliateID())){
                        itemToUpdate = menuList.get(i);
                        count++;
                    }
                }
                if (count == 1){
                    System.out.println("\n" + itemToUpdate.toString());
                    System.out.println("Fields as below are available to be updated.\n1. Food Name\n2. Price\n3. Description\n4. Calories\n5. Promotional Info\n6. Status");
                    System.out.print("Your selection: ");
                    scanner = new Scanner(System.in);
                    String selection = scanner.nextLine();
                    while(!selection.matches("[1-7]")){
                        System.out.print("Invalid selection!\nYour selection: ");
                        selection = scanner.nextLine();
                    }
                    int choice = Integer.parseInt(selection);
                    scanner.reset();
                    switch (choice) {
                        case 1:
                            Scanner scanNewFoodName = new Scanner(System.in);
                            System.out.print("Please enter new food name: ");
                            String newFoodName = scanNewFoodName.nextLine();
                            while(!newFoodName.matches("[A-Za-z ]+") || newFoodName.trim().isEmpty()){
                                System.out.print("Alphabets only!\nPlease enter new food name: ");
                                newFoodName = scanNewFoodName.nextLine();
                            }
                            menuList.update(new Menu(itemToUpdate.getFoodID(), newFoodName, itemToUpdate.getPrice(), itemToUpdate.getDescription(), itemToUpdate.getCalories(), itemToUpdate.getPromoInfo(), itemToUpdate.getStatus(), itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                        case 2:
                            Scanner scanNewPrice = new Scanner(System.in);
                            System.out.print("Please enter new price: RM ");
                            String priceString = scanNewPrice.nextLine();
                            while(!isDouble(priceString) || priceString.trim().isEmpty() || Double.parseDouble(priceString) <= 0 ){
                                System.out.print("Please enter a valid format!\nPlease enter new price: RM ");
                                priceString = scanNewPrice.nextLine();
                            }
                            double price = Double.parseDouble(priceString);
                            menuList.update(new Menu(itemToUpdate.getFoodID(), itemToUpdate.getFoodName(), price, itemToUpdate.getDescription(), itemToUpdate.getCalories(), itemToUpdate.getPromoInfo(), itemToUpdate.getStatus(), itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                        case 3:
                            Scanner scanNewDescription = new Scanner(System.in);
                            System.out.print("Enter new description: ");
                            String description = scanNewDescription.nextLine();
                            while(description.trim().isEmpty()){
                                System.out.print("Blank is not allowed!\nEnter new description: ");
                                description = scanNewDescription.nextLine();
                            }
                            menuList.update(new Menu(itemToUpdate.getFoodID(), itemToUpdate.getFoodName(), itemToUpdate.getPrice(), description, itemToUpdate.getCalories(), itemToUpdate.getPromoInfo(), itemToUpdate.getStatus(), itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                        case 4:
                            Scanner scanNewCalories = new Scanner(System.in);
                            System.out.print("Please enter new calories(kcal): ");
                            String caloriesString = scanNewCalories.nextLine();
                            while(!isInt(caloriesString) || caloriesString.trim().isEmpty() || Integer.parseInt(caloriesString) <= 0 ){
                                System.out.print("Please enter a valid format!\nPlease enter new calories(kcal): ");
                                caloriesString = scanNewCalories.nextLine();
                            }
                            int calories = Integer.parseInt(caloriesString);
                            menuList.update(new Menu(itemToUpdate.getFoodID(), itemToUpdate.getFoodName(), itemToUpdate.getPrice(), itemToUpdate.getDescription(), calories, itemToUpdate.getPromoInfo(), itemToUpdate.getStatus(), itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                        case 5:
                            Scanner scanNewPromo = new Scanner(System.in);
                            System.out.print("Enter new description: ");
                            String promoInfo = scanNewPromo.nextLine();
                            while(promoInfo.trim().isEmpty()){
                                System.out.print("Blank is not allowed!\nEnter new description: ");
                                promoInfo = scanNewPromo.nextLine();
                            }
                            menuList.update(new Menu(itemToUpdate.getFoodID(), itemToUpdate.getFoodName(), itemToUpdate.getPrice(), itemToUpdate.getDescription(), itemToUpdate.getCalories(), promoInfo, itemToUpdate.getStatus(), itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                        case 6:
                            Scanner scanNewStatus = new Scanner(System.in);
                            System.out.print("Please enter new status: ");
                            String status = scanNewStatus.nextLine();
                            while(!status.matches("[A-Za-z ]+") || status.trim().isEmpty()){
                                System.out.print("Alphabets only!\nPlease enter new status: ");
                                status = scanNewStatus.nextLine();
                            }
                            menuList.update(new Menu(itemToUpdate.getFoodID(), itemToUpdate.getFoodName(), itemToUpdate.getPrice(), itemToUpdate.getDescription(), itemToUpdate.getCalories(), itemToUpdate.getPromoInfo(), status, itemToUpdate.getAffiliateID()), menuList.getPosition(itemToUpdate));
                            break;
                    }
                    System.out.println("\nMenu updated successfully.\nYour latest menu: \n");
                    System.out.println(menuList.listAllMenuOfThatAffiliate(currentAffiliate.getAffiliateID()));
                }
                else{
                    System.out.print("No such item!\tPlease enter food ID to update: ");
                    scanner = new Scanner(System.in);
                    foodID = scanner.nextLine();
                }
            }while(count != 1);
        }
        else                                                                                                                        
            System.out.println("You do not have any food yet.\n");
    }                                   //update food detail in menu
    
    
public void displayRestaurant(){
        for (int i = 1; i <= affiliateList.getNumberOfEntries(); i++) {
        System.out.println(i+"."+affiliateList.get(i).getRestaurantName());
            } 

    
}
    
    
    public StackInterface displayMenuByRestaurant(int choice){
        System.out.println();
        menuStack.clear();
        for (int i = 1; i <= menuList.getNumberOfEntries(); i++) {
            if (menuList.get(i).getAffiliateID().equals(affiliateList.get(choice).getAffiliateID()) && menuList.get(i).getStatus().equals("Available")) {
                menuStack.push(menuList.get(i));
            }
        }
        System.out.println("\nMenu of " + affiliateList.get(choice).getRestaurantName() + "(Latest to oldest): ");
        int a = 1;
        String menu="\n\t\tMenu\t\t|\tPrice\n===================================================\n";
        while(a <= menuStack.size()){
            menu+=String.format("%2d",a)+". "+String.format("%-34s",menuStack.peek(a).getFoodName())+String.format("RM %.2f", menuStack.peek(a).getPrice())+"\n";

            a++;
        }
        System.out.println(menu);
      
        return menuStack;
    }                     //choose available restaurant to view menu
    
    
    
    
    
    
    
    public void displayPriceInDescending(ListInterface<Menu> menu){
        menuStack.clear();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your affiliate ID: ");
        String affiliateID = scanner.nextLine();
        while(!affiliateList.findSame(affiliateID)){
            System.out.print("ID not found. Enter registered affiliate ID: ");
            affiliateID = scanner.nextLine();
        }
        scanner.reset();
        Affiliate currentAffiliate = affiliateList.retrieveInstance(affiliateID);
        System.out.println("\nHi, you are " + currentAffiliate.getOwnerName() + " of " + currentAffiliate.getRestaurantName());
        System.out.println("Your current menu in descending price order: ");
        
        menuStack.push(menuList.CheapestFood());    
        do{
            double toBePushed = menuList.MostExpensiveValue();  
            Menu objectToBePushed = null;
            for (int i = 1; i <= menu.getNumberOfEntries(); i++) {
                    if ((menu.get(i).getPrice() > menuStack.peekTop().getPrice()) && (menu.get(i).getPrice() <= menuList.MostExpensiveValue())) {
                        if (menu.get(i).getPrice() <= toBePushed) {
                            toBePushed = menu.get(i).getPrice();
                            objectToBePushed = menu.get(i);
                        }
                    }
                    else if ((menu.get(i).getPrice() == menuStack.peekTop().getPrice()) && (!menu.get(i).equals(menuStack.peekTop()))){
                        toBePushed = menu.get(i).getPrice();
                        objectToBePushed = menu.get(i);
                }
            }
            menuStack.push(objectToBePushed);
        }while(menuStack.size() != menuList.getNumberOfEntries());
        
        while(!menuStack.isEmpty()){
            Menu m = menuStack.pop();
            if (m.getAffiliateID().equals(affiliateID)) {
                System.out.println(m);
            }
        }
    }

    public boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }                         //check whether the String is a double value
    
    public boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }                              //check whether the String is a int value
    
}
