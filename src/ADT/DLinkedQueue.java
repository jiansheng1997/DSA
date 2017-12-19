/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;


import entity.Customer;
import entity.Order;
import entity.OrderList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user1
 */
public class DLinkedQueue<T> implements QueueInterface<T>{

  private Node<T> firstNode; 
  private Node<T> lastNode;  
  private int lastOdFrequency;
  

  public DLinkedQueue() {
    firstNode = null;
    lastNode = null;
  } 

  
    public boolean add(T newEntry) {
      Node newOd = new Node(newEntry);
    boolean ck=false;
    Node tempNode=lastNode; 
        if(newEntry instanceof OrderList){
       
       if (isEmpty()) {
          firstNode = newOd;
          lastNode=newOd;
          ck=true;
         } else {
     if(!foundSame(newEntry)){
      tempNode.setNext(newOd);
      newOd.setPrevious(tempNode);
      lastNode=newOd;
          }
       ck=true;
      }
    
    }
        return ck;
  }
  
  
    public boolean add(T newEntry,int frequency) {
    Node newOd = new Node(newEntry);
    boolean ck=false;
    Node tempNode=lastNode; 
    if (newEntry instanceof Order){
         if (isEmpty()) {
           newOd.setPrevious(null);
          firstNode = newOd;
          firstNode.setNext(null);
          lastNode=newOd;
          lastOdFrequency=frequency;
          ck=true;
         } else {
        Order od=(Order) tempNode.getData();
      if ((((Order) newEntry).getDate()).equals(od.getDate())){
          if(frequency>lastOdFrequency && frequency>=5) {
              if(firstNode.getNext()!=null){
              newOd.setPrevious(lastNode.getPrevious());
              newOd.setNext(lastNode);
              lastNode.setPrevious(newOd);
              ck=true;
              }else{
              firstNode=newOd;
              firstNode.setNext(lastNode);
              lastNode.setPrevious(firstNode);
              ck=true;
              }  
          }else{
          
          SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
           Date Newtime;
           Date Oldtime; 
          try {
              Newtime=sdf.parse(((Order) newEntry).getTime());
              Oldtime=sdf.parse(od.getTime());
          if(Newtime.after(Oldtime)){
            if(firstNode.getNext()!=null){
                lastNode.setNext(newOd);
                newOd.setPrevious(lastNode);
                ck=true; 
              }else{
                 firstNode.setNext(newOd);
                 newOd.setPrevious(firstNode);
                  ck=true;     
              }
                lastNode=newOd;  
          }else if(Newtime.before(Oldtime)){
              if(firstNode.getNext()!=null){
              newOd.setPrevious(lastNode.getPrevious());
              newOd.setNext(lastNode);
              lastNode.setPrevious(newOd);
              ck=true;
              }else{
              firstNode=newOd;
              firstNode.setNext(lastNode);
              lastNode.setPrevious(firstNode);
              ck=true;
              }  
             
          }
                    
          } catch (ParseException ex) {
              Logger.getLogger(DLinkedQueue.class.getName()).log(Level.SEVERE, null, ex);
             
              } 
          }
      }else{
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           Date NewDate;
           Date OldDate;
     
          try {
              NewDate=sdf.parse(((Order) newEntry).getDate());
              OldDate=sdf.parse(od.getDate());
          if(NewDate.after(OldDate)){
                tempNode.setNext(newOd);
                newOd.setPrevious(tempNode);
                lastNode=newOd;
                ck=true; 
          }else if(NewDate.before(OldDate)){
              if(tempNode.getPrevious()!=null){
              newOd.setPrevious(tempNode.getPrevious());
              tempNode.getPrevious().setNext(newOd);
              }
              newOd.setNext(tempNode);
              tempNode.setPrevious(newOd);
              firstNode=newOd;
              ck=true; 
          }
          } catch (ParseException ex) {
              ck=false;
              Logger.getLogger(DLinkedQueue.class.getName()).log(Level.SEVERE, null, ex);
          }         
      }
      
    }
         
    }
    return ck;
  }
    
   
    
 
    public T getOrderRecord(String id){
        Node tempNode = firstNode;
       T order=null;
        if (!isEmpty() && tempNode.getData() instanceof Order) {
            while(tempNode!=null){
                Order od=(Order)tempNode.getData();
                if(id.equals(od.getCustID())){
                    order=(T)od;
                }  
                tempNode = tempNode.getNext();    
            }
        }else if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while(tempNode!=null){
                OrderList odL=(OrderList)tempNode.getData();
                if(id.equals(odL.getOrderID())){
                    order=(T)odL;
                }  
                tempNode = tempNode.getNext();  
        }
        
        }
   return order;
    }
    
    public int calFoodQuantity(String id){
        Node tempNode = firstNode;
        int qty=0;
        if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while(tempNode!=null){
                OrderList odL=(OrderList)tempNode.getData();
                if(id.equals(odL.getOrderID())){
                    qty+=odL.getQty();
                }  
                tempNode = tempNode.getNext();    
            }  
        }
        return qty;
    }
    
    public void resetFrequency(){
        lastOdFrequency=0;
    }
    
    
    public boolean foundSame(T od){
        Node tempNode = lastNode;
        boolean sameFood=false;
        if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while(tempNode!=null){
            OrderList ol=(OrderList)tempNode.getData();
            if(ol.getOrderID().equals(((OrderList)od).getOrderID())&&ol.getFoodID().equals(((OrderList)od).getFoodID())){
                double price=ol.getSubtotal()/ol.getQty();
                ol.setQty(ol.getQty()+((OrderList)od).getQty());
                ol.setSubtotal(price*ol.getQty()); 
                tempNode.setData(ol);
              
                return true;
       
            }
                tempNode = tempNode.getNext();
            }
        }
        return sameFood;
    } 
    

  public T getFront() {
    T front = null;

    if (!isEmpty()) {
      front = firstNode.getData();
    }

    return front;
  } 


  
  public T get() {
    T front = null;
    
    if (!isEmpty()) {
      front = firstNode.getData();
      firstNode = firstNode.getNext();
      
      if (firstNode == null) {
        lastNode = null;
      }
    } 
    return front;
  } 
  

  public boolean isEmpty() {
    return (firstNode == null) && (lastNode == null);
  } 
  
  
  public void cancelOrder(String OrderID){
      Node tempNode;
      if(firstNode!=null){
      tempNode=lastNode;
      }
      else 
          tempNode=firstNode;
      
      while(tempNode!=null){
          OrderList OL=(OrderList) tempNode.getData();
          if(OL.getOrderID().equals(OrderID)){
              if(tempNode.getNext()!=null){
              firstNode=firstNode.getNext();
              
              }else{
              firstNode=null;
              lastNode=null;
              }
           }
          tempNode=tempNode.getNext();
      }
  }

  public void clear() {
    firstNode = null;
    lastNode = null;
  } 


 
   
    
  public void displayOrderList(String id){
            int i=1;

        Node tempNode=firstNode;
        OrderList odList=new OrderList();
            String ol="===================================================\nNo    Order List\t     　  |\tPrice\n===================================================\n";
        while(tempNode!=null){
        odList=(OrderList)tempNode.getData();
            if(odList.getOrderID().equals(id)){
            ol+=(i)+")  "+String.format("%-28s", odList.getQty()+" "+odList.getFoodNm())
                    +" | "+String.format("RM %.2f", odList.getSubtotal())+"\n";
            i++;
            
         } 
            tempNode=tempNode.getNext();
        }
        ol+="***************************************************\n\tTotal :\t\t\t | "+ String.format("RM %.2f",calTotal(odList.getOrderID()))+"\n***************************************************";       
        System.out.println(ol);
  }

  public Double calTotal(String orderID){
      double total=0;
        Node tempNode=firstNode;
        OrderList odList=new OrderList(); 
        while(tempNode!=null){
        odList=(OrderList)tempNode.getData();
        if(odList.getOrderID().equals(orderID)){
            total+=odList.getSubtotal();
         } 
        tempNode=tempNode.getNext();
        }
      
      return total;
  }
  
} 
