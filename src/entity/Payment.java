
package entity;


public class Payment {
    String paymentID;
    String DeliveryID;
    String status;

    public Payment(){}
    
    public Payment(String paymentID,String DeliveryID,String status){
        this.paymentID = paymentID;
        this.DeliveryID=DeliveryID;
        this.status = status;
    }
    
    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getDeliveryID() {
        return DeliveryID;
    }

    public void setDeliveryID(String DeliveryID) {
        this.DeliveryID = DeliveryID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String toString(){
        return "PaymentID:" + paymentID +" DeliveryID: "+DeliveryID+" status:"+status;
    }
    
}
