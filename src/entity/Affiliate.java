package entity;

import ADT.ListImplementation;
import ADT.ListInterface;

public class Affiliate {
    String affiliateID;
    String ownerName;
    String contactNo;
    String icNumber;
    String email;
    String restaurantName;
    
    public Affiliate(){}
    
    public Affiliate(String affiliateID, String ownerName, String contactNo, String icNumber, String email, String restaurantName){
        this.affiliateID = affiliateID;
        this.ownerName = ownerName;
        this.contactNo = contactNo;
        this.icNumber = icNumber;
        this.email = email;
        this.restaurantName = restaurantName;
    }

    public String getAffiliateID() {
        return affiliateID;
    }

    public void setAffiliateID(String affiliateID) {
        this.affiliateID = affiliateID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return affiliateID + ", " + ownerName + ", " + contactNo + ", " + icNumber + ", " + email + ", " + restaurantName;
    }

     public void hardcode(){
        ListInterface<Affiliate> affiliateList = new ListImplementation<>(); 
        Affiliate a1 = new Affiliate("A0001", "Ng Yih Sam", "0165502038", "970110565289", "yihsam1997@gmail.com", "HoChak Restaurant");
        Affiliate a2 = new Affiliate("A0002", "Quek Wei Jian", "0123456789", "970214146603", "quek@hotmail.com", "HOHO Seafood");
        Affiliate a3 = new Affiliate("A0003", "Phuah Wai Yan", "0115659879", "970125105645", "pwy@gmail.com", "KenKen Western");
        Affiliate a4 = new Affiliate("A0004", "Seong Jian Sheng", "0122256999", "971102551203", "sjs111@hotmail.com", "Tokok Vegetarian");
        affiliateList.add(a1);
        affiliateList.add(a2);
        affiliateList.add(a3);
        affiliateList.add(a4);
 } 
    
}
