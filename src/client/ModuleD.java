
package client;
import java.util.Scanner;
import ADT.DeliverymanList;
import ADT.DeliverymanListInterface;
import ADT.DeliveryListInterface;
import ADT.DeliveryListImplementation;
import ADT.OrderQueue;
import ADT.OrderQueueInterface;
import entity.Delivery;
import entity.Order;
import entity.Customer;
import entity.Payment;
import entity.DeliveryMan;
import static client.moduleC.ODqueue;
import static client.moduleC.OLqueue;
import static client.FoodDeliveryService.customerList;
import static client.FoodDeliveryService.menuList;
import static client.FoodDeliveryService.order;
import static client.FoodDeliveryService.list;
import static client.FoodDeliveryService.TemporaryList;
import static client.FoodDeliveryService.SortedList;
import static client.FoodDeliveryService.order;
import static client.FoodDeliveryService.payment;
import static client.FoodDeliveryService.delivery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModuleD {
    
    
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Scanner scanner = new Scanner(System.in);
    public void menu(){

        String backMain;
         FoodDeliveryService FCS = new FoodDeliveryService();
        
        do{
            System.out.println("Please select the following: ");
            System.out.println("1. Track Reach Time");
            System.out.println("2. Clock in or out");
            System.out.println("3. Assign deliveryman");
            System.out.println("4. Update complete delivery status");
            System.out.println("5. Generate Daily Deliver Report");
            System.out.println("0. Back to Main Menu\n");            
            System.out.print("Your choice: ");
            String selection = scanner.nextLine();
            while(!selection.matches("[0-5]") || selection.trim().isEmpty()){
                System.out.print("Your choice: ");
                selection = scanner.nextLine();
            }
            int choice = Integer.parseInt(selection);
            switch (choice) {
                case 0: 
                    FCS.MainMenu();
                    break;
                case 1:
                    TrackReachTime();
                    break;
                case 2:
                 clockin();
                    break;
                case 3:
                    assign();
                    break;
                case 4:
                    updatedeliverystatus();
                    break;
                case 5:
                    displayReport();
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
        
    } 
    
    public void TrackReachTime() {
        String address ="";
        String name ="";
        String customerid ="";
        int count =1;
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your OrderID to check remaining time of your order to arrive: ");
        String id = scanner.next();
        
        while(!id.matches("OD\\d{4}")||id.trim().isEmpty())
        {
         System.out.println("Invalid OrderID, Please try again");
         System.out.print("Enter your OrderID to check remaining time of your order to arrive: ");
         id = scanner.next();
        }
        
        for(int i =1;i<order.getNumberOfEntries()+1;i++){
            
            if(id.equals(order.get(i).getOrderID())){
                Customer  customer = (Customer)customerList.retrieveInstance(order.get(i).getCustID());
                name = customer.getCustomerName();
                address = customer.getAddress();
                customerid =customer.getCustomerID();
                
            }
            else {
                count++;
            }
         
        }
            
        if(count==order.getNumberOfEntries()+1){
            System.out.println("This OrderrID does not exist");
            
        }
       else{
        int orderquantity = OLqueue.calFoodQuantity(id);
        int processtime = orderquantity * 5;

        
        System.out.println("Name               : "+name);
        System.out.println("Address            : "+address);
        System.out.println("CustomerID         : "+customerid);
        System.out.println("OrderID            : "+id);
        System.out.println("Order Time         : "+order.getOrderTime(id));
        System.out.println("order will reach in: "+calculateReachTime(id));
        System.out.println("Time remaining     : "+processtime+" minutes");
        }
     }
    
    public void assign(){
        
            if(!TemporaryList.isEmpty()&&TemporaryList.size()>3){
                
        for ( int i =0;i<list.size();i++){
         
           DeliveryMan de = (DeliveryMan)TemporaryList.removeByPosition(TemporaryList.getPosition(TemporaryList.MostExperience()));
           SortedList.add(de);
         }
            }else if (!TemporaryList.isEmpty()&&TemporaryList.size()==1){
                DeliveryMan de = (DeliveryMan)TemporaryList.removeByPosition(TemporaryList.getPosition(TemporaryList.MostExperience()));
                SortedList.add(de);
            }
        
        System.out.println("Deliveryman that are available (Sort in experience Highest-lowest)");
        System.out.println("DeliverymanID\tDeliveryman Name\t\tDeliveryman Status\tDeliveryman Experience(month)");
         System.out.println(SortedList.calculate());
         for(int a =0;a<SortedList.size();a++){
             DeliveryMan sorted =(DeliveryMan)SortedList.get(a);
             if(sorted.getTaskStatus().equals("Available")){
         System.out.println(sorted.getId()+"\t\t"+sorted.getName()+"\t\t"+sorted.getTaskStatus()+"\t\t"+sorted.getExperience()+" month");
             }
         }
         System.out.println("\nOrder that are still pending to be deliver");
         ODqueue.displayCurrentQueue();
        System.out.print("\nEnter DeliverymanID to assign which deliveryman to deliver order: ");
        String id = scanner.next();
        while(!id.matches("DM\\d{1}")||id.trim().isEmpty()){
          System.out.println("Invalid deliverymanID, Please enter again");
          System.out.print("Enter DeliverymanID to assign which deliveryman to deliver order: ");
          id = scanner.next();
        }
        Order od = (Order)ODqueue.getFront();
        String orderid = od.getOrderID();
        Delivery de = new Delivery();
        if(delivery.isEmpty()){
            de.setDeliveryID("DE001");
        }else{
            int countDelivery = delivery.getNumberOfEntries()+1;
            de.setDeliveryID("DE00"+countDelivery);
        }
        de.setDeliverymanID(id);
        DeliveryMan setstatus = SortedList.retrieveInstance(id);
        setstatus.setTaskStatus("Delivery");
        
        Payment pa = new Payment();
        
        if (payment.isEmpty()){
            de.setPaymentID("P001");
            pa.setPaymentID("P001");
        }else{
            int countPayment = payment.getNumberOfEntries()+1;
            de.setPaymentID("P00"+countPayment);
            pa.setPaymentID("P00"+countPayment);
        }
        de.setOrderID(orderid);
        de.setReachtime(calculateReachTime(orderid));
        pa.setDeliveryID(de.getDeliveryID());
        pa.setStatus("pending");
        payment.add(pa);
        delivery.add(de);
        Order remove = (Order)ODqueue.get();
        remove.setStatus("Delivery");
        order.add(remove);
        DeliveryMan display = (DeliveryMan)SortedList.retrieveInstance(id);
        System.out.println(display.getName()+ " are assigned to deliver "+orderid+" order.");
        System.out.println("\nDelivery List");
        delivery.displayDeliveryList();
        System.out.println("\nPayment List");
        payment.displayPaymentList();
        
        
        
    }
    public void updatedeliverystatus(){
        System.out.print("Enter DeliveryID that u just deliver to update status: ");
        String deliveryid = scanner.next();
        while(!deliveryid.matches("DE\\d{3}")||deliveryid.trim().isEmpty()){
          System.out.println("Invalid deliveryID, Please enter again");
          System.out.print("Enter DeliveryID that u just deliver to update status: ");
          deliveryid = scanner.next();
        }
        Delivery del = (Delivery)delivery.retrieveInstance(deliveryid);
        String orderid = del.getOrderID();
        String deliverymanid = del.getDeliverymanID();
        
        Order updatestatus = (Order)order.retrieveInstance(orderid);
        updatestatus.setStatus("Delivered");
        DeliveryMan setstatus = SortedList.retrieveInstance(deliverymanid);
        setstatus.setTaskStatus("Available");
        Payment pay = (Payment)payment.retrieveInstance(deliveryid);
        pay.setStatus("Paid");
        System.out.println("\n"+updatestatus.toString());
        System.out.println("\nPayment List");
        payment.displayPaymentList();
        System.out.println("\n"+setstatus.toString());
        
    }
   
    public void clockin(){
        if(!TemporaryList.isEmpty()&&TemporaryList.size()>3){
                
        for ( int i =0;i<list.size();i++){
         
           DeliveryMan de = (DeliveryMan)TemporaryList.removeByPosition(TemporaryList.getPosition(TemporaryList.MostExperience()));
           SortedList.add(de);
         }
            }else if (!TemporaryList.isEmpty()&&TemporaryList.size()==1){
                DeliveryMan de = (DeliveryMan)TemporaryList.removeByPosition(TemporaryList.getPosition(TemporaryList.MostExperience()));
                SortedList.add(de);
            }
        
		System.out.print("Please enter your deliverymanID to clock in or out: ");
		String deliverymanid = scanner.next();
                while(!deliverymanid.matches("DM\\d{1}")||deliverymanid.trim().isEmpty()){
                System.out.println("Invalid deliverymanID, Please enter again");
                System.out.print("Please enter your deliverymanID to clock in or out: ");
                deliverymanid = scanner.next();
                }
		DeliveryMan deliveryMan = SortedList.retrieveInstance(deliverymanid);
                
		if (deliveryMan.getTaskStatus().equals("Unavailable")) {
			System.out.println("Name: "+deliveryMan.getName());
                        System.out.println("Status: "+deliveryMan.getTaskStatus());
                        
			System.out.print("Are you sure to clock in ?(y=yes,n=no) :");
                        String choice = scanner.next();
                        
                       
                        if (choice.equals("y")||choice.equals("Y")){
                            deliveryMan.setTaskStatus("Available");
                            System.out.println("Welcome Back, You have successfull to clock in.\n");
                            SortedList.displaydeliverymanList();
                        }else if (choice.equals("n")||choice.equals("N")){
                            System.out.println("You have cancel to clock in");
                        }
		} else {
                    System.out.println("Name: "+deliveryMan.getName());
                    System.out.println("Status: "+deliveryMan.getTaskStatus());
                    System.out.print("Are you sure to clock out ?(y=yes,n=no) :");
                        String choice = scanner.next();
                        
                        if (choice.equals("y")||choice.equals("Y")){
                            deliveryMan.setTaskStatus("Unavailable");
                            System.out.println("Goodbye, You have successfull to clock out.\n");
                            SortedList.displaydeliverymanList();
                        }else if(choice.equals("n")||choice.equals("N")){
                            System.out.println("You have cancel to clock out");
                        }
		}
    }
    public String calculateReachTime(String id){
        try {
            int orderquantity = OLqueue.calFoodQuantity(id);
            int processtime = orderquantity * 5;
            
            String time = order.getOrderTime(id);

            Date ordertime = sdf.parse(time);
            
            cal.setTime(ordertime);
            cal.add(Calendar.MINUTE, processtime);
            
        } catch (ParseException ex) {
            Logger.getLogger(ModuleD.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return sdf.format(cal.getTime());

        }  
    public void displayReport(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\t\t\t\t\t\t\tDaily Delivery Report\t\t\t\tDate:"+sdf1.format(calendar.getTime()));
        System.out.println("==========================================================================================================================");
        delivery.displayDeliveryReport();
    }
}
