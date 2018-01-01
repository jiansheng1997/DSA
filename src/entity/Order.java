/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Time;

/**
 *
 * @author user1
 */
public class Order {
    private String orderID;
    private String custID;
    private String date;
    private String time;
    private String status;
    private double total;
    

    public Order(String orderID, String custID, String date, String time, String status, double total) {
        this.orderID = orderID;
        this.custID = custID;
        this.date = date;
        this.time = time;
        this.status = status;
        this.total = total;
    }

    public Order() {
      
    }
    
    
    public String getOrderID() {   
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
       
        return "Order Information\n====================\n" + "Order ID : " + orderID + "\nOrder Date : " + date + "\nOrder Time : " + time + " \nStatus : " + status + " \nTotal : RM "  + String.format("%.2f", total);
    }
    
 
}
