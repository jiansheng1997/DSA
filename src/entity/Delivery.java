
package entity;



public class Delivery {
    String DeliveryID;
    String DeliverymanID;
    String paymentID;
    String orderID;
    String Reachtime;

    public Delivery(){}
    
    public Delivery(String DeliveryID,String DeliverymanID,String paymentID,String orderID,String Reachtime){
        this.DeliveryID= DeliveryID;
        this.DeliverymanID= DeliverymanID;
        this.paymentID= paymentID;
        this.orderID= orderID;
        this.Reachtime= Reachtime;
    }
    public String getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(String DeliveryID) {
        this.DeliveryID = DeliveryID;
    }

    public String getDeliverymanID() {
        return DeliverymanID;
    }

    public void setDeliverymanID(String DeliverymanID) {
        this.DeliverymanID = DeliverymanID;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getReachtime() {
        return Reachtime;
    }

    public void setReachtime(String Reachtime) {
        this.Reachtime = Reachtime;
    }

    @Override
    public String toString() {
        return  "DeliveryID=" + DeliveryID + ", DeliverymanID=" + DeliverymanID + ", paymentID=" + paymentID + ", orderID=" + orderID + ", Reachtime=" + Reachtime;
    }
    
}
