/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author user1
 */
public class OrderList {
    private String orderListID;
    private String foodNm;
    private int qty;
    private String foodID;
    private double subtotal;
    private String orderID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public OrderList(String orderListID, String foodNm, int qty, String foodID, double subtotal, String orderID) {
        this.orderListID = orderListID;
        this.foodNm = foodNm;
        this.qty = qty;
        this.foodID = foodID;
        this.subtotal = subtotal;
        this.orderID = orderID;
    }



    public OrderList() {
      
    }
    

    public String getOrderListID() {
        return orderListID;
    }

    public void setOrderListID(String orderListID) {
        this.orderListID = orderListID;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "OrderList{" + "orderListID=" + orderListID + ", foodNm=" + foodNm + ", qty=" + qty + ", foodID=" + foodID + ", subtotal=" + subtotal + ", orderID=" + orderID + '}';
    }




}
