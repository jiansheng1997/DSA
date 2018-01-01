/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import ADT.DLinkedQueue;
import ADT.ListImplementation;
import ADT.ListInterface;
import ADT.QueueInterface;
import ADT.StackImplementation;
import ADT.StackInterface;
import entity.Customer;
import entity.Menu;
import entity.Order;
import entity.OrderList;
import java.util.Scanner;
import static client.FoodDeliveryService.customerList;
import static client.FoodDeliveryService.affiliateList;
import static client.FoodDeliveryService.order;
import static client.FoodDeliveryService.orderList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user1
 */
public class moduleC {

        static QueueInterface<OrderList> OLqueue=new DLinkedQueue<>();
        static QueueInterface<Order> ODqueue=new DLinkedQueue<>();
        
   

            Scanner sc=new Scanner(System.in);
        FoodDeliveryService FCS=new FoodDeliveryService();
         
        
        
        public void menu(){
            
            String choice;
      do{ 
        System.out.println();
        System.out.println("1. Make Order");  
        System.out.println("2. View order queue");     
        System.out.println("3. Retreive Customer's Info"); 
        System.out.println("0. Back to Main Menu"); 
        System.out.print("Enter your choice:");
        choice=sc.nextLine();
      }while(!choice.equals("1")&&!choice.equals("2")&&!choice.equals("3")&&!choice.equals("0"));
      
        if(choice.equals("1")){
            String id=login();
            makeOrder(id);
        }else if(choice.equals("2")){
            viewOrderQueue(); 
        }else if(choice.equals("3")){
         retrieveCustomerInfo();
        }else if(choice.equals("0")){
            FCS.MainMenu();
        }

        }
        
        public moduleC() {
            System.out.println();
            menu();
    }
        
        public String login(){
        String id,pass;
        do{
        System.out.print("\nPlease enter your ID :");
        id=sc.nextLine();
        System.out.print("Please enter your password :");
        pass=sc.nextLine();
        if(!customerList.Login(id, pass)){
        System.out.println("\nWrong id or password.Please try again.");
        }
        }while(!customerList.Login(id, pass));
        return id;
        }
        
        public void makeOrder(String CustID){
          
              reNewFrequency();     

              StackInterface<Menu> menuStack = new StackImplementation<>();
            int firstOrder=1;
            int no,qty,no2,menuNo;
            String choice2="1";
       ModuleA moA=new ModuleA();
        System.out.println();
       
        moA.displayRestaurant();
        System.out.print("\nPlease select a Restaurant >");
        String tempp=sc.nextLine();

         while(!tempp.matches("[1-"+affiliateList.getNumberOfEntries()+"]") || tempp.trim().isEmpty()){
                System.out.print("Please enter an valid number. Enter select a Restaurant: ");
                tempp= sc.nextLine();
         }  
             no = Integer.parseInt(tempp);
  
       Order od=new Order();  
   do{ 
              if(firstOrder==1){
        od.setOrderID(order.generateID(od));
        } 
              
              
     do{
        menuStack=moA.displayMenuByRestaurant(no);
        menuNo=menuStack.size()+1;        

         
         System.out.print("Order >");
             String temp=sc.nextLine();
         while(!temp.matches("[1-"+(menuNo-1)+"]") || temp.trim().isEmpty()){
                System.out.print("Please enter an valid number. Enter Your Order: ");
                temp= sc.nextLine();
         }  
             no2 = Integer.parseInt(temp);
             sc.reset();
        qty=0;            


             System.out.print("Quantity(1 to 10) >");
              String temp2= sc.nextLine();
         while(!temp2.matches("[1-9]|10")){
                System.out.print("Please enter an valid number. Enter Your Quantity: ");
                temp2= sc.nextLine();
         }  
         qty = Integer.parseInt(temp2);

             if(no2>=1&&no2<=menuNo){
             OrderList ol=new OrderList();
             ol.setOrderID(od.getOrderID());
             ol.setFoodID(menuStack.peek(no2).getFoodID());
             ol.setFoodNm(menuStack.peek(no2).getFoodName());
             ol.setQty(qty);
             ol.setSubtotal(menuStack.peek(no2).getPrice()*qty);
             ol.setOrderListID(orderList.generateID(ol));
             OLqueue.add(ol);    
             orderList.add(ol);
           System.out.print("Continue order? (Exit=0)>");
            firstOrder++;
           choice2=sc.nextLine();
             }
         }while(no<1||no>menuNo);     
    
   } while(!(choice2.equals("0")));   
     
    String c; 
      do{
      OLqueue.displayOrderList(od.getOrderID());
       System.out.print("(1-Confirm 2-Cancel) >");
         c=sc.nextLine();
         if(c.equals("1")){
         od.setTotal(OLqueue.calTotal(od.getOrderID()));
         od.setCustID(CustID);
         od.setDate(getCurrentDate());
         od.setTime(getCurrenteTime());
         od.setStatus("Pending");
         Customer cc=customerList.retrieveInstance(CustID);
         int fq=cc.getOrderFrequency();
         if(ODqueue.add(od,fq)&&order.add(od)){        
         System.out.println("Your order is now pending to delivery.");
         System.out.println(od.toString());
         fq=addOrderFrequency(CustID);
         }
         menu();
         }else if(c.equals("2")){
          OLqueue.cancelOrder(od.getOrderID());
         orderList.cancelOrder(od.getOrderID());
          System.out.println("Your order is now cancel.");
         menu();
         }
      }while(!c.equals("1")&&!c.equals("2"));
    
        }
      
       public void viewOrderQueue(){   
           int No=1;
           if(!ODqueue.isEmpty()){
           System.out.println("\nOrder Queue\n==========================");
           }else
                System.out.println("\nThere are no queue.");
           while(!ODqueue.isEmpty()){ 
              Order ORDER =(Order)ODqueue.getFront();
            System.out.println(No+". Order ID : " + ORDER.getOrderID() + "|Order Date : " + ORDER.getDate() + "|Order Time : " + ORDER.getTime()+ " |Status : "
                    + ORDER.getStatus() + "|Total : RM "  + String.format("%.2f", ORDER.getTotal()));
           No++;
           }
            System.out.print("\nBack to Menu?(Menu-1 ,Please any key to exit)");
            String temp=sc.nextLine();
            if(temp.equals("1")){
                menu();
            }
            else
                FCS.MainMenu();
       }
       
        public void retrieveCustomerInfo(){
         String phoneNo;
          Customer cc=new Customer();
          int count=3;
          boolean ck=true;
           String temp="";
do{          
    do{          
          System.out.print("Enter the customer phone number to retrieve the customer information :");
          phoneNo=sc.nextLine();
          cc=customerList.retrieveInstance(phoneNo);
           count--;
           if(cc==null){
          if(count==0&&cc==null){
         System.out.println("\nExceed 3 attempts. The phone number cannot found.");
         ck=false;
            break;
              }               
           System.out.println("\nInvalid phone number.Please try again.\nLeft "+ count +" attempts."); 
           }
        }while(cc==null);
        if(ck==true)
          System.out.println(cc.toString());
            System.out.print("\nBack to Menu?(Menu-0, Please any key to continue.)");
           temp=sc.nextLine();
            if(temp.equals("0")){
                menu();
            }
            }while(!temp.equals("0"));
        }
  
        
     public String getCurrentDate() {
                     Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(cal.getTime());
    } 
     
     public String getCurrenteTime() {
                     Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }  
     
     public int addOrderFrequency(String id){
         int orderFq=0;
         for(int i=1;i<customerList.getNumberOfEntries();i++){
         if(id.equals(customerList.get(i).getCustomerID())){
             orderFq=customerList.get(i).getOrderFrequency()+1;
             customerList.get(i).setOrderFrequency(orderFq);
             break;
            }
         }
         return orderFq;
    }
     
    public void reNewFrequency() {
            try {
                boolean find=false;
                Calendar cal = Calendar.getInstance();
                int month=cal.get(Calendar.MONTH);
                month++;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");       
                Date OdDate=sdf.parse(order.getlatestOrder().getDate());
                if(cal.getTime().after(OdDate)){
                for(int k=1;k<customerList.getNumberOfEntries();k++){
                        if(OdDate.getMonth()+1!=month){
                                    customerList.get(k).setOrderFrequency(0);
                                    ODqueue.resetFrequency();
                                    find=true;
                              
                                }
                    }
                }
              } catch (ParseException ex) {   
                Logger.getLogger(moduleC.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}