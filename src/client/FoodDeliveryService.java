/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import entity.DeliveryMan;
import ADT.DeliverymanList;
import ADT.ListImplementation;
import ADT.ListInterface;
import entity.Affiliate;
import entity.Customer;
import entity.Menu;
import entity.Order;
import entity.OrderList;
import java.util.Scanner;
import ADT.DeliverymanListInterface;

/**
 *
 * @author user1
 */
public class FoodDeliveryService {

    /**
     * @param args the command line arguments
     */
    static ListInterface<Affiliate> affiliateList = new ListImplementation<>();
    static ListInterface<Menu> menuList = new ListImplementation<>();
    static ListInterface<Customer> customerList = new ListImplementation<>();
    static DeliverymanListInterface<DeliveryMan> list = new DeliverymanList<>();
    static ListInterface<Order> order = new ListImplementation<>();
    static ListInterface<OrderList> orderList = new ListImplementation<>();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        FoodDeliveryService FCS = new FoodDeliveryService();
        FCS.hardcode();
        FCS.MainMenu();

    }

    
    public void hardcode(){

        Affiliate a1 = new Affiliate("A0001", "Ng Yih Sam", "0165502038", "970110565289", "yihsam1997@gmail.com", "HoChak Restaurant");
        Affiliate a2 = new Affiliate("A0002", "Quek Wei Jian", "0123456789", "970214146603", "quek@hotmail.com", "HOHO Seafood");
        Affiliate a3 = new Affiliate("A0003", "Phuah Wai Yan", "0115659879", "970125105645", "pwy@gmail.com", "KenKen Western");
        Affiliate a4 = new Affiliate("A0004", "Seong Jian Sheng", "0122256999", "971102551203", "sjs111@hotmail.com", "Tokok Vegetarian");
        affiliateList.add(a1);
        affiliateList.add(a2);
        affiliateList.add(a3);
        affiliateList.add(a4);
        
        Menu sam1 = new Menu("F0001", "Pikachu Meat", 5.90, "Delicious meat ever", 120, "December Promo", "Available", "A0001");
        Menu sam2 = new Menu("F0002", "Hotdog", 2.80, "Tasty hotdog of the day", 245, "-", "Available", "A0001");
        Menu sam3 = new Menu("F0003", "Durian Burger", 9.50, "Fresh and delicious", 660, "October Promo", "Available", "A0001");
        Menu quek1 = new Menu("F0004", "Fried Prawn", 35, "Fresh prawn of the day", 249, "-", "Available", "A0002");
        Menu quek2 = new Menu("F0005", "Cheese Crab", 99.9, "Meaty crab with cheese", 450, "December Promo", "Available", "A0002");
        Menu quek3 = new Menu("F0006", "Sweet and sour tilapia", 28, "Catch from river", 264, "November Promo", "Available", "A0002");
        Menu vayne1 = new Menu("F0007", "Fish n' Chip", 18.9, "Fish with fries", 529, "October Promo", "Available", "A0003");
        Menu vayne2 = new Menu("F0008", "Lamb Chop", 17.9, "Yummy lamb meat", 440, "October Promo", "Available", "A0003");
        Menu vayne3 = new Menu("F0009", "Wasabi Chicken Chop", 12.8, "Unexpected delicious chicken chop", 670, "-", "Available", "A0003");
        Menu seong1 = new Menu("F0010", "Vege Mee", 6, "Vegetarian's favourite", 480, "November promo", "Available", "A0004");
        Menu seong2 = new Menu("F0011", "Vege Bak Kut Teh", 12.3, "Herbal dish", 380, "December Promo", "Available", "A0004");
        Menu seong3 = new Menu("F0012", "Lui Cha", 9.9, "Rice with lots of vege", 400, "October Promo", "Available", "A0004");
        
        menuList.add(sam1);   menuList.add(sam2);   menuList.add(sam3);
        menuList.add(quek1);  menuList.add(quek2);  menuList.add(quek3);
        menuList.add(vayne1); menuList.add(vayne2); menuList.add(vayne3);
        menuList.add(seong1); menuList.add(seong2); menuList.add(seong3);
   
        Customer c1 = new Customer("C0001","abc123", "Michelle Lee", "0127654479", "No.34, Jalan 2, Taman Segamat, Selangor", "michelleLee@gmail.com", 5);
        Customer c2 = new Customer("C0002","4896", "Soon Jian Kai", "0129709876", "No.55, Jalan 1/2, Taman Wilayah, Selangor", "soonjk@gmail.com", 2);
        Customer c3 = new Customer("C0003", "233","Ashlyn Chong", "0193456789", "No.11, Jalan 1, Blok H, Taman Ceasar, Selangor", "ashlyn@gmail.com", 1);
        Customer c4 = new Customer("C0004","idk", "Michael Chia", "0122234479", "No.99, Bangunan Tan Siew Sin, Setapak, Selangor", "michaelChia@gmail.com", 1);
        Customer c5 = new Customer("C0005","gg.com", "Lee Jia Xin", "0111369434", "No.6, Jalan 3/5, Taman Yuri, Selangor", "JiaXinLee@gmail.com", 0);
        
        customerList.add(c1);
        customerList.add(c2);
        customerList.add(c3);
        customerList.add(c4);
        customerList.add(c5);
  
        Order od1 =new Order("OD1000","C0001","8/11/2017","18:22:01","Delivered",12);
        Order od2 =new Order("OD1001","C0002","12/11/2017","10:43:12","Delivered",35);
        Order od3 =new Order("OD1002","C0003","28/11/2017","22:11:35","Delivered",19.8);    
        Order od4 =new Order("OD1003","C0001","3/12/2017","09:46:51","Delivered",12.8); 
        Order od5 =new Order("OD1004","C0001","3/12/2017","05:35:54","Delivered",199.8);         
        Order od6 =new Order("OD1005","C0001","3/12/2017","01:56:43","Delivered",46.9);
        Order od7 =new Order("OD1006","C0001","8/12/2017","09:34:43","Delivered",28.5);
        Order od8 =new Order("OD1007","C0003","10/12/2017","23:56:11","Delivered",17.9);
        Order od9 =new Order("OD1008","C0004","15/12/2017","16:21:22","Delivered",36.9);
        Order od10 =new Order("OD1009","C0001","17/12/2017","05:46:56","Delivered",12.8);

        order.add(od1); order.add(od2); order.add(od3);
        order.add(od4); order.add(od5); order.add(od6);
        order.add(od7); order.add(od8); order.add(od9); order.add(od10);

        OrderList odl1 = new OrderList("OL1000", "Vege Mee", 2, "F0010", 12, "OD1000");
        OrderList odl2 = new OrderList("OL1001", "Fried Prawn", 1, "F0004", 35, "OD1001");
        OrderList odl3 = new OrderList("OL1005", "Lui Cha", 2, "F0012", 19.8, "OD1002");
        OrderList odl4 = new OrderList("OL1002", "Wasabi Chicken Chop", 1, "F0009", 12.8, "OD103");
        OrderList odl5 = new OrderList("OL1002", "Cheese Crab", 2, "F0005", 199.8, "OD1004");
        OrderList odl6 = new OrderList("OL1003", "Sweet and sour tilapia", 1, "F0006", 28, "OD1005");
        OrderList odl7 = new OrderList("OL1004", "Fish n' Chip", 1, "F0007", 18.9, "OD1005");
        OrderList odl8 = new OrderList("OL1007", "Lamb Chop", 1, "F0008", 17.9, "OD1007");
        OrderList odl9 = new OrderList("OL1006", "Durian Burger", 3, "F0003", 28.5, "OD1006");
        OrderList odl20 = new OrderList("OL1008", "Vege Bak Kut Teh", 3, "F0012", 36.9, "OD1008");
        OrderList odl21 = new OrderList("OL1009", "Wasabi Chicken Chop", 1, "F0009", 12.8, "OD109");
    
        orderList.add(odl1);  orderList.add(odl2); orderList.add(odl3);
        orderList.add(odl4);  orderList.add(odl5); orderList.add(odl6); 
        orderList.add(odl7);  orderList.add(odl8); orderList.add(odl9);
        orderList.add(odl20); orderList.add(odl21);
        
        list.add(new DeliveryMan("DM"+1, "Quek Wei Jian   ", "Working", 20, 5, 3000.55, "012-3456789", "Quek Address", "Clock Out", 1, 10));
	list.add(new DeliveryMan("DM"+2, "Phuah Wai Yan   ", "Working", 18, 3, 4500.99, "012-9876543", "Phuah Address", "Clock In", 2, 20));
	list.add(new DeliveryMan("DM"+3, "Ng Yih Sam      ", "Retire", 25, 15, 7600.30, "012-9786543", "Sam Address", "Clock Out", 3, 30));
	list.add(new DeliveryMan("DM"+4, "Seong Jian Sheng", "Working", 22, 7, 2000.55, "012-3456789", "Seong Address", "Clock In", 4, 40));
    }
    
    public void MainMenu() {
        int no;
        String choice;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\n1. Maintainance Menus");
        System.out.println("2. Maintain Deliveryman Info");
        System.out.println("3. Place ad hoc order ");
        System.out.println("4. Workload assignment and clock in & out");
        System.out.println("0. Exit");
        System.out.print("Enter your choice:");
        choice = sc.next();
        while (!choice.matches("[0-4]") || choice.trim().isEmpty()) {
            System.out.print("Please enter an valid number. Enter Your choice: ");
            choice = sc.next();
        }
        no = Integer.parseInt(choice);

        do {
            switch (no) {
                case 1:
                    System.out.println();
                    ModuleA a = new ModuleA();
                    a.menu();
                    break;
                case 2:
                    System.out.println();
                    moduleB b = new moduleB();
                    break;
                case 3:
                    moduleC c = new moduleC();
                    break;
                case 4:
                    break;
                case 0:
                    System.out.print("Bye.Have a nice day.");
                    break;
                default:
                    System.out.print("Invalid choice.Please Try again:");
            }
        } while (no < 0 || no > 4);

    }
}
