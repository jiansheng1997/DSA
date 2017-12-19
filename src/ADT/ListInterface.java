package ADT;

import entity.Customer;

public interface ListInterface<T> {
    
    public boolean isEmpty();  //check whether the list is empty
    
    public int getNumberOfEntries(); //to get size of the list
    
    public boolean add(T object);  //to add a new record into the list
    
    public T get(int position);    //to find the desired position of record in the list without removing it
    
    public T remove(int position);  //to remove a record by giving position
    
    public boolean update(T newRecord, int position);
    
    public int getPosition(T record);
    
    @Override
    public String toString();
    
    
    
    
    public boolean findSame(String id);
    
    public boolean findSameFood(String foodname, String id);                                                
    
    public boolean isParticularEmpty(String id);                                                           
    
    public String suggest();
    
    public T retrieveInstance(String id);
    
    public String listAllMenuOfThatAffiliate(String id);
    
    public T CheapestFood();
    
    public double MostExpensiveValue();
    
    public boolean Login(String id,String pass);

     public void cancelOrder(String OrderID);
     
     public String generateID(T id);
     
    public T getlatestOrder(); 


}
