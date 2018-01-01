package entity;

public class Customer {
    String customerID;
    String password;
    String customerName;
    String phoneNumber;
    String address;
    String email;


    int orderFrequency;
    
    public Customer(){}

    public Customer(String customerID, String password, String customerName, String phoneNumber, String address, String email, int orderFrequency) {
        this.customerID = customerID;
        this.password = password;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.orderFrequency = orderFrequency;
    }



    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

     public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
    
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getOrderFrequency() {
        return orderFrequency;
    }

    public void setOrderFrequency(int orderFrequency) {
        this.orderFrequency = orderFrequency;
    }

    @Override
    public String toString() {
        return "Customer ID : " + customerID + "\nCustomer Name : " + customerName + " \nPhoneNumber : " + phoneNumber + "\nAddress : " 
                + address + "\nEmail : " + email + "\nOrderFrequency : " + orderFrequency ;
    }
    
    
    
}
