package ADT;

import entity.Order;
import entity.Customer;
import entity.Delivery;
import entity.Payment;


public class DeliveryListImplementation<T> implements DeliveryListInterface<T>{
Node firstNode;
Node lastNode;
int totalEntries;
    @Override
    public boolean isEmpty() {
          return (firstNode == null) && (totalEntries == 0);
    }

    @Override
    public int getNumberOfEntries() {
        return totalEntries;
    }

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
    }
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
    }
    

    @Override
    public T remove(int position) {
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
    }

    @Override
    public boolean update(T newRecord, int position) {
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
    }

    @Override
    public int getPosition(T record) {
        int result = 0;
        Node tempNode = firstNode;
        for (int i = 1; i <= totalEntries; i++) {
            if (tempNode.getData().equals(record)) {   
                return i;
            }
            tempNode = tempNode.getNext();
        }
        return result;
    }
    
    public T getOrderTime(String orderID){
        T result = null;
        Node tempNode = firstNode;
        if(!isEmpty() && tempNode.getData() instanceof Order){
            Order order = (Order)tempNode.getData();
            if(order.getOrderID().equals(orderID)){
                result=(T)order.getTime();
            }
            tempNode = tempNode.getNext();
        }
        return result;
    }
    
    public T retrieveInstance(String id){
        T result = null;
        Node tempNode = firstNode;
        
             if (!isEmpty() && tempNode.getData() instanceof Delivery){
            for (int i = 1; i <= totalEntries; i++) {
                Delivery delivery = (Delivery)tempNode.getData();
                if (delivery.getDeliveryID().equals(id)) {
                    result = (T) delivery;
                }
                tempNode = tempNode.getNext();
            }
        }
            
             return result;
    }
    public void displayDeliveryList() {
        int No = 1;
        if (!isEmpty()) {
            Node tempNode = firstNode;
            while (tempNode != null) {
                Delivery delivery = (Delivery) tempNode.getData();
                System.out.println(No + ". Delivery ID : " + delivery.getDeliveryID() + "|DeliverymanID : " + delivery.getDeliverymanID() + "|PaymentID : " + delivery.getPaymentID() + " |OrderID : "
                        + delivery.getOrderID() + "|Reach Time :" + delivery.getReachtime());
                No++;
                tempNode = tempNode.getNext();
            
            }
        }
    }
    
    public void displayDeliveryReport() {
        int No = 1;
        if (!isEmpty()) {
            Node tempNode = firstNode;
               System.out.println("No\t\tDelivery ID\t\tDeliveryman ID\t\tPayment ID\t\tOrder ID\t\tReach Time");
            while (tempNode != null) {
                Delivery delivery = (Delivery) tempNode.getData();
                System.out.println(No+"\t\t"+delivery.getDeliveryID()+"\t\t\t"+delivery.getDeliverymanID()+"\t\t\t"+delivery.getPaymentID()+"\t\t\t"+delivery.getOrderID()+"\t\t\t"+delivery.getReachtime());
                No++;
                tempNode = tempNode.getNext();
            }   
    }else{
            System.out.println("\n\t\t\t\t\t\t\tThere are no record");
        }
    }
}
