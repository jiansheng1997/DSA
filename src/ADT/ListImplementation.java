package ADT;
import entity.Affiliate;
import entity.Customer;
import entity.Menu;
import entity.Order;
import entity.OrderList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListImplementation<T> implements ListInterface<T>{

    Node firstNode;
    int totalEntries;
    
    @Override
    public boolean isEmpty() {
        return (firstNode == null) && (totalEntries == 0);
    }                                    //Check whether the list is empty. Return true if empty, else return false.

    @Override
    public int getNumberOfEntries() {
        return totalEntries;
    }                            //Get total number of records in the list. Return an integer.

    @Override
    public boolean add(T object) {
        Node newNode = new Node(object);
        
        if (!isEmpty()) {
            Node tempNode = firstNode;
            while(tempNode.getNext() != null)
                tempNode = tempNode.getNext();
            tempNode.setNext(newNode);
        }
        else{
            firstNode = newNode;
        }
     totalEntries++;
        return true;
    }                                //Add new record to the list, total number of records incremented by 1. Return true if successfully added.
    
    @Override
    public T get(int position){
        T result = null;
        if (!isEmpty() && (position > 0 && position <= totalEntries)) {
            if (position == 1) {
                result = (T) firstNode.getData();
            }
        else{
            Node tempNode = firstNode;
            for (int i = 1; i < position; i++) {
                tempNode = tempNode.getNext();
            }
            result = (T) tempNode.getData();
        }
    }
        return result;
    }                                   //Retrieve a record from the list by position. Return a record from the list if the position is valid, else return null.
             
    @Override
    public T remove(int position){
        T result = null;
        
        if ((position > 0) && (position <= totalEntries)) {
            if (position == 1) {
                result = (T) firstNode.getData();
                firstNode = firstNode.getNext();
            }
            else{
                Node tempNode = firstNode;
                for (int i = 1; i < position - 1 ; i++) {
                    tempNode = tempNode.getNext();
                }
                result = (T) tempNode.getNext().getData();
                tempNode.setNext(tempNode.getNext().getNext());
            }
            totalEntries--;
        }
        return result; 
    }                                //Remove a record from the list by position. Total number of records decremented by 1. Return the record to be removed, else return null.
     
    @Override
    public boolean update(T newRecord, int position){
        boolean updateSuccess =  true;

        if (position > 0){
            Node tempNode = firstNode;
            for (int i = 1; i < position ; i++)
                tempNode = tempNode.getNext();		
            tempNode.setData(newRecord);
        }
        else
            updateSuccess = false;
        
        return updateSuccess;
    }             //Replace current record with a new record. Return true if successfully replaced, else return false.
    
    @Override
    public int getPosition(T record){       
        int result = 0;
        Node tempNode = firstNode;
        for (int i = 1; i <= totalEntries; i++) {
            if (tempNode.getData().equals(record)) {   
                return i;
            }
            tempNode = tempNode.getNext();
        }
        return result;
    }                            //Get position of specific record. Return an integer if found, else return 0.
    
    @Override
    public String toString() {
    String outputStr = "";
    Node tempNode = firstNode;
    while (tempNode != null) {
      outputStr += tempNode.getData() + "\n";
      tempNode = tempNode.getNext();
    }
    return outputStr;
  }                                    //Print all records of the list. Return String.
    
    
    
    
    @Override
    public boolean findSame(String id){
        boolean found = false;
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Affiliate) {                           
            if (id.matches("A\\d{4}")) {
                for (int i = 1; i <= totalEntries; i++) {
                    Affiliate aff = (Affiliate)tempNode.getData();
                    if (aff.getAffiliateID().equals(id)) {
                        return true;
                    }
                    tempNode = tempNode.getNext();
                }
            }
            else{
                for (int i = 1; i <= totalEntries; i++) {
                    Affiliate aff = (Affiliate)tempNode.getData();
                    if (aff.getRestaurantName().equals(id)) {
                        return true;
                    }
                    tempNode = tempNode.getNext();
                }
            } 
        }
        
        else if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getFoodID().equals(id)) {
                    return true;
                }
                tempNode = tempNode.getNext();
            }
        } else if (!isEmpty() && tempNode.getData() instanceof Customer) {
            for (int i = 1; i <= totalEntries; i++) {
                Customer cust = (Customer)tempNode.getData();
                if (cust.getCustomerID().equals(id)) {
                    
                    return true;
                }
                tempNode = tempNode.getNext();
        }

        }   //Check if that foodID or AffiliateID exists in the list. Return true if it exists, else return false.
                return found;
    }
    
    public boolean Login(String id,String pass){
        boolean found = false;
        Node tempNode = firstNode;
if (!isEmpty() && tempNode.getData() instanceof Customer) {
            for (int i = 1; i <= totalEntries; i++) {
                Customer cust = (Customer)tempNode.getData();
                if (cust.getCustomerID().equals(id)) {
                    if (cust.getPassword().equals(pass))
                         found= true;
                }
                tempNode = tempNode.getNext();
        }

        } 
        return found;
    }
    
    @Override
    public T retrieveInstance(String info){
        T result = null;
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Affiliate) {
            for (int i = 1; i <= totalEntries; i++) {
                Affiliate aff = (Affiliate)tempNode.getData();
                if (aff.getAffiliateID().equals(info)) {
                    result = (T) aff;
                }
                tempNode = tempNode.getNext();
            }
        }
        else if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getFoodID().equals(info)) {
                    result = (T) menu;
                }
                tempNode = tempNode.getNext();
            }
        }else if (!isEmpty() && tempNode.getData() instanceof Customer) {
    while(tempNode!=null){
    Customer cus =(Customer)tempNode.getData();       //retrieve cus info based on phone number  
        if(cus.getPhoneNumber().equals(info))
            result=(T)cus;   
        else if(info.equals(cus.getCustomerID()))   //retrieve cus info based on cusID 
            result=(T)cus;
            tempNode=tempNode.getNext();
    }
        }
        return result;
    }//Retrieve a record from the list. Return a record if it matches the id entered, else return false.
    
    @Override
    public String listAllMenuOfThatAffiliate(String id){
        String output = "";
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getAffiliateID().equals(id)) {
                    output += menu.toString() + "\n";
                }
                tempNode = tempNode.getNext();
            }
            if (output.isEmpty()) {
                return "\t\tNone\n\n";
            }
            else{
                output += "\n";
            }
        }
        return output;
    }  //Print all records that match with the id entered. Return formatted string if matches, else return "None".
    
    @Override                                                                                                       
    public boolean findSameFood(String foodname, String id){
        boolean sameName = false;
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getFoodName().equals(foodname) && menu.getAffiliateID().equals(id)) {
                    return true;
                }
                tempNode = tempNode.getNext();
            }
        }
        return sameName;
    }                                                                                                               
    
    @Override                                                                                                       
    public boolean isParticularEmpty(String id){
        boolean isEmpty =  true;
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getAffiliateID().equals(id)) {
                    return false;
                }
                tempNode = tempNode.getNext();
            }
        }
        return isEmpty;
    }                                                                                                               
    
    @Override                                                                                                       
    public String suggest(){
        String randNum;
        String result = "";
        do{
            Random rand = new Random();
            randNum = String.format("F%04d", rand.nextInt(10000));
            if (!findSame(randNum)) {
                result = randNum;
            }
        }while(findSame(randNum));
        return result;
    }
    
    @Override
    public T CheapestFood(){
        T result = null;
        Node tempNode = firstNode;
        if(!isEmpty() && tempNode.getData() instanceof Menu){
            Menu first = (Menu)tempNode.getData();
            double smallest = first.getPrice();
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu)tempNode.getData();
                if (menu.getPrice() < smallest) {
                    result = (T) menu;
                }
                tempNode = tempNode.getNext();
            }
        }
        return result;
    }                                     //Find the cheapest food from the menu and return the record. Return the record with cheapest price.
    
    @Override
    public double MostExpensiveValue(){
        double biggest = 0;
        Node tempNode = firstNode;
        if (!isEmpty() && tempNode.getData() instanceof Menu) {
            for (int i = 1; i <= totalEntries; i++) {
                Menu menu = (Menu) tempNode.getData();
                if (menu.getPrice() > biggest) {
                    biggest = menu.getPrice();
                }
                tempNode = tempNode.getNext();
            }
        }
        return biggest;
    }                         //Find the most expensive price from the menu. Return a double value.
  
 
  public void cancelOrder(String OrderID){
      Node tempNode;
      if(firstNode!=null){
          tempNode=firstNode;
      while(tempNode!=null){
          OrderList OL=(OrderList) tempNode.getData();
          if(OL.getOrderID().equals(OrderID)){
              if(tempNode.getNext()!=null){
              firstNode=firstNode.getNext();
              
              }else{
              firstNode=null;
              }
           }
          tempNode=tempNode.getNext();
      }
  }
  }
     
     public String generateID(T id){   
        String no="";
        int num;
        Node tempNode=firstNode;  
            while(tempNode.getNext() != null)
                tempNode = tempNode.getNext();
        
  
   if (id instanceof OrderList) { 
      OrderList ol=new OrderList();      
   if(firstNode!=null){
        ol=(OrderList)tempNode.getData();
        for(int i=2;i<=5;i++){
            no+=ol.getOrderListID().charAt(i); 
                }
        num=Integer.parseInt(no)+1;   
        no="OL"+num;  

        return no;
    }else
         no="OL1000";
        }
   
   else if (id instanceof Order) { 
      Order od=new Order();
      
   if(firstNode!=null){
        od=(Order)tempNode.getData();
        for(int i=2;i<=5;i++){
            no+=od.getOrderID().charAt(i); 
                }
        num=Integer.parseInt(no)+1;   
        no="OD"+num;  
        return no;
    }else
         no="OD1000";
   }
   
   return no;
    }
  
    public T getlatestOrder(){
        T result = null;
        Node tempNode = firstNode;
        if(!isEmpty() && tempNode.getData() instanceof Order){
            try {
                Order firstOd = (Order)tempNode.getData();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date latestOdDate = sdf.parse(firstOd.getDate());
                
            for (int i = 1; i <= totalEntries; i++) {
                    Order od = (Order)tempNode.getData();
                    Date OdDate = sdf.parse(od.getDate());
                    if (latestOdDate.before(OdDate)) {
                        result = (T) od;
                    }
                    tempNode = tempNode.getNext();
                }
            } catch (ParseException ex) {
                Logger.getLogger(ListImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }   
  
  
}
