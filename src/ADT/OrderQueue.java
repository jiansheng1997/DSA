/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

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
public class OrderQueue<T> implements OrderQueueInterface<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int lastOdFrequency;
    private int size;

    public OrderQueue() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public boolean add(T newOrder) {
        Node newOd = new Node(newOrder);
        boolean ck = false;
        Node tempNode = lastNode;

        if (isEmpty()) {
            firstNode = newOd;
            lastNode = newOd;
            ck = true;
            size++;
        } else {
            if (!addOn(newOrder)) {
                tempNode.setNext(newOd);
                newOd.setPrevious(tempNode);
                lastNode = newOd;
            }
            ck = true;
            size++;
        }

        return ck;
    }

    public boolean add(T newOrder, int frequency) {
        Node newOd = new Node(newOrder);
        boolean ck = false;
        Node tempNode = lastNode;
        if (newOrder instanceof Order) {
            if (isEmpty()) {
                newOd.setPrevious(null);
                firstNode = newOd;
                firstNode.setNext(null);
                lastNode = newOd;
                ck = true;
                size++;
            } else {
                Order od = (Order) firstNode.getData();
                if ((((Order) newOrder).getDate()).equals(od.getDate())) {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        Date Newtime;
                        Date Oldtime;
                        od = (Order) tempNode.getData();
                        try {
                            Newtime = sdf.parse(((Order) newOrder).getTime());
                            Oldtime = sdf.parse(od.getTime());
                            
                            if (Newtime.after(Oldtime)) {
                                if (firstNode.getNext() != null) {
                                    tempNode.setNext(newOd);
                                    newOd.setPrevious(tempNode);
                                    ck = true;
                                    size++;
                                } else {
                                    firstNode.setNext(newOd);
                                    newOd.setPrevious(firstNode);
                                    ck = true;
                                    size++;
                                }
                                lastNode = newOd;
                            } else if (Newtime.before(Oldtime)) {
                               Node afterNode=null;
                                while (tempNode != null && Newtime.before(Oldtime)) {
                                 afterNode=tempNode;
                                    tempNode =tempNode.getPrevious();
;   
                                    if (tempNode != null) {
                                        od = (Order) tempNode.getData();
                                        Oldtime = sdf.parse(od.getTime());
                                     }
                                }
         
                                   if(tempNode!=null){
                                        (afterNode.getPrevious()).setNext(newOd);         
                                        newOd.setNext(afterNode);
                                        newOd.setPrevious(afterNode.getPrevious());
                                        afterNode.setPrevious(newOd);
                                        ck = true;
                                        size++;
                           
                                   }else{
                                       firstNode.setPrevious(newOd);
                                        newOd.setNext(firstNode);
                                        newOd.setPrevious(null);
                                        firstNode = newOd;
                                        ck = true;
                                        size++;
                                   }
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(OrderQueue.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date NewDate;
                    Date OldDate;

                    try {
                        NewDate = sdf.parse(((Order) newOrder).getDate());
                        OldDate = sdf.parse(od.getDate());
                        if (NewDate.after(OldDate)) {
                            tempNode.setNext(newOd);
                            newOd.setPrevious(tempNode);
                            lastNode = newOd;
                            ck = true;
                            size++;
                        } else if (NewDate.before(OldDate)) {
                               Node afterNode=null;
                                while (tempNode != null && NewDate.before(OldDate)) {
                                 afterNode=tempNode;
                                    tempNode =tempNode.getPrevious();
;   
                                    if (tempNode != null) {
                                        od = (Order) tempNode.getData();
                                        OldDate = sdf.parse(od.getDate());
                                     }
                                }
         
                                   if(tempNode!=null){
                                        (afterNode.getPrevious()).setNext(newOd);         
                                        newOd.setNext(afterNode);
                                        newOd.setPrevious(afterNode.getPrevious());
                                        afterNode.setPrevious(newOd);
                                        ck = true;
                                        size++;
                           
                                   }else{
                                       firstNode.setPrevious(newOd);
                                        newOd.setNext(firstNode);
                                        newOd.setPrevious(null);
                                        firstNode = newOd;
                                        ck = true;
                                        size++;
                                   }
                        }
                    } catch (ParseException ex) {
                        ck = false;
                        Logger.getLogger(OrderQueue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (frequency > lastOdFrequency && frequency >= 5) {
                    if (!newOd.equals(firstNode)) {
                        if (firstNode.getNext() != null) {
                            if (!newOd.equals(lastNode)) {
                                newOd.getPrevious().setNext(newOd.getNext());
                                newOd.setNext(newOd.getPrevious());
                                newOd.setPrevious( newOd.getPrevious().getPrevious());                                
                                newOd.getPrevious().setPrevious(newOd);
                            }else{
                            newOd.setNext(lastNode);
                            lastNode.getPrevious().setNext(newOd);
                            newOd.setPrevious(lastNode.getPrevious());
                            lastNode.setPrevious(newOd);  
                            }
                        } else {
                            newOd.setNext(firstNode);
                            firstNode.setPrevious(newOd);
                            firstNode = newOd;

                        }
                    }
                }
            }

        }
        if (ck) {
            lastOdFrequency = frequency;
        }

        return ck;
    }



    public int getSize() {
        return size;
    }

    public T getOrderRecord(String id) {
        Node tempNode = firstNode;
        T order = null;
        if (!isEmpty() && tempNode.getData() instanceof Order) {
            while (tempNode != null) {
                Order od = (Order) tempNode.getData();
                if (id.equals(od.getCustID())) {
                    order = (T) od;
                }
                tempNode = tempNode.getNext();
            }
        } else if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while (tempNode != null) {
                OrderList odL = (OrderList) tempNode.getData();
                if (id.equals(odL.getOrderID())) {
                    order = (T) odL;
                }
                tempNode = tempNode.getNext();
            }
        }
        return order;
    }

    public int calFoodQuantity(String id) {
        Node tempNode = firstNode;
        int qty = 0;
        if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while (tempNode != null) {
                OrderList odL = (OrderList) tempNode.getData();
                if (id.equals(odL.getOrderID())) {
                    qty += odL.getQty();
                }
                tempNode = tempNode.getNext();
            }
        }
        return qty;
    }

    public void resetFrequency() {
        lastOdFrequency = 0;
    }

    private boolean addOn(T od) {
        Node tempNode = lastNode;
        boolean sameFood = false;
        if (!isEmpty() && tempNode.getData() instanceof OrderList) {
            while (tempNode != null) {
                OrderList ol = (OrderList) tempNode.getData();
                if (ol.getOrderID().equals(((OrderList) od).getOrderID()) && ol.getFoodID().equals(((OrderList) od).getFoodID())) {
                    double price = ol.getSubtotal() / ol.getQty();
                    ol.setQty(ol.getQty() + ((OrderList) od).getQty());
                    ol.setSubtotal(price * ol.getQty());
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

    public void cancelOrder(String OrderID) {
        Node tempNode;
        if (firstNode != null) {
            tempNode = lastNode;
        } else {
            tempNode = firstNode;
        }

        while (tempNode != null) {
            OrderList OL = (OrderList) tempNode.getData();
            if (OL.getOrderID().equals(OrderID)) {
                if (tempNode.getNext() != null) {
                    firstNode = firstNode.getNext();

                } else {
                    firstNode = null;
                    lastNode = null;
                }
            }
            tempNode = tempNode.getNext();
        }
    }

    public void displayOrderList(String id) {
        int i = 1;

        Node tempNode = firstNode;
        OrderList odList = new OrderList();
        String ol = "===================================================\nNo    Order List\t     　  |\tPrice\n===================================================\n";
        while (tempNode != null) {
            odList = (OrderList) tempNode.getData();
            if (odList.getOrderID().equals(id)) {
                ol += (i) + ")  " + String.format("%-28s", odList.getQty() + " " + odList.getFoodNm())
                        + " | " + String.format("RM %.2f", odList.getSubtotal()) + "\n";
                i++;

            }
            tempNode = tempNode.getNext();
        }
        ol += "***************************************************\n\tTotal :\t\t\t | " + String.format("RM %.2f", calTotal(odList.getOrderID())) + "\n***************************************************";
        System.out.println(ol);
    }

    public Double calTotal(String orderID) {
        double total = 0;
        Node tempNode = firstNode;
        OrderList odList = new OrderList();
        while (tempNode != null) {
            odList = (OrderList) tempNode.getData();
            if (odList.getOrderID().equals(orderID)) {
                total += odList.getSubtotal();
            }
            tempNode = tempNode.getNext();
        }

        return total;
    }

    public void displayCurrentQueue() {
        int No = 1;
        if (!isEmpty()) {
            Node tempNode = firstNode;
            while (tempNode != null) {
                Order ORDER = (Order) tempNode.getData();
                System.out.println(No + ". Order ID : " + ORDER.getOrderID() + "|Order Date : " + ORDER.getDate() + "|Order Time : " + ORDER.getTime() + " |Status : "
                        + ORDER.getStatus() + "|Total : RM " + String.format("%.2f", ORDER.getTotal()));
                No++;
                tempNode = tempNode.getNext();
            }
            System.out.println();
            tempNode = lastNode;
            while (tempNode != null) {
                Order ORDER = (Order) tempNode.getData();
                System.out.println(No + ". Order ID : " + ORDER.getOrderID() + "|Order Date : " + ORDER.getDate() + "|Order Time : " + ORDER.getTime() + " |Status : "
                        + ORDER.getStatus() + "|Total : RM " + String.format("%.2f", ORDER.getTotal()));
                No++;
                tempNode = tempNode.getPrevious();
            }
        }
    }
}
