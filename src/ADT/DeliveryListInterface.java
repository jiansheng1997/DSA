package ADT;

 
public interface DeliveryListInterface<T> {
   
     public boolean isEmpty();  //check whether the list is empty
    
    public int getNumberOfEntries(); //to get size of the list
    
    public boolean add(T object);  //to add a new record into the list
    
    public T get(int position);    //to find the desired position of record in the list without removing it
    
    public T remove(int position);  //to remove a record by giving position
    
    public boolean update(T newRecord, int position);
    
    public int getPosition(T record);
    
    @Override
    public String toString();
    
    public T getOrderTime(String orderID);
    
    public T retrieveInstance(String id);
    
    public void displayDeliveryList();
    
    public void displayDeliveryReport();
}
