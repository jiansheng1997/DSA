/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import entity.DeliveryMan;
import entity.Order;

/**
 *
 * @author Lenovo
 */
public class DeliverymanList<T> implements DeliverymanListInterface<T> {

	private static final int INDEX_NUMBER = 1;
	private static final int INDEX_VALUE = 0;
	private Node firstNode;
	private Node lastNode;
	private int size;

	public DeliverymanList() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	public void add(T newEntry) {
		size++;
		Node myNode = new Node(newEntry);
		if (isEmpty()) {
			firstNode = myNode;
			lastNode = firstNode;
		} else {
			if (firstNode.getNext() == null) {
				firstNode.setNext(myNode);
			}
			lastNode.setNext(myNode);
			lastNode = myNode;

		}
	}
        

	public void set(int position, Object newEntry) {
		Node myNode = firstNode;
                
		for (int i = 0; i < position; i++) {
                    myNode =myNode.getNext();		

		}
                    myNode.setData(newEntry);
	}

	public T remove(int position) {
		Node myNode = firstNode;
		for (int i = 0; i < position - 1; i++) {
			myNode = myNode.getNext();
		}
		if (myNode.getNext() == null) {
			myNode.getPrevious().setNext(null);
		} else {
			myNode.getPrevious().setNext(myNode.getNext());
		}
		size--;
		return (T) myNode.getData();
	}
       
	public T get(int position) {

		Node myNode = firstNode;

		for (int i = 0; i <= position - 1; i++) {
			myNode = myNode.getNext();
		}

		return (T) myNode.getData();
	}

	public int[] getDesc(int[] index, int[] value) {
		int indexHolder = 0;
		int valueHolder = 0;
		int[] temp = index;
		int[] temp2 = value;
		
		
		for (int i = 0; i < size - 1; i++) {
			for (int ii = 0; ii < size - 1; ii++) {
				
				
				if (value[ii] < value[ii+1]) {
					indexHolder = index[ii];
					valueHolder = value[ii];
					
					index[ii] = index[ii+1];
					value[ii] = value[ii+1];
					
					index[ii+1] = indexHolder;
					value[ii+1] = valueHolder;
 				}
				
			}
		}
		
		return index;
	}
	
	public int[][] getDesc(DeliverymanList<DeliveryMan> list) {
		int[][] desc = new int[list.size()][2];
		int[] temp = new int[2];

		for (int i = 0; i < list.size(); i++) {
			desc[i][0] = list.get(i).getNumberOfDelivery();
			desc[i][1] = i;
		}

		for (int o = 0; o < list.size() - 1; o++) {
			if (desc[o][0] < desc[o + 1][0]) {
				temp[0] = desc[o][0];
				temp[1] = desc[o][1];

				desc[o][0] = desc[o + 1][0];
				desc[o][1] = desc[o + 1][1];

				desc[o + 1][0] = temp[0];
				desc[o + 1][1] = temp[1];
			}

		}
		return desc;
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	public void clear() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	public int size() {
		return size;
	}
        
        public T removeByPosition(int position){
           T result = null;
        
        if ((position > 0) && (position <= size)) {
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
            size--;
        }
        return result;
       }
        
        public T MostExperience(){
    T result = null;
    Node tempNode = firstNode;
    if(!isEmpty()&&tempNode.getData() instanceof DeliveryMan)
    {
        DeliveryMan high = (DeliveryMan)tempNode.getData();
        int highest = 0;
        for(int i =1;i<=size;i++)
        {
        DeliveryMan de = (DeliveryMan)tempNode.getData();
        
            if(de.getExperience()>highest){
                highest=de.getExperience();
                result = (T) de;
            }
            
        
        tempNode = tempNode.getNext();
       }
    }
    return result;
      }
        
        public int calculate(){
       int result =0;
       Node tempNode = firstNode;
       if(!isEmpty()&&tempNode.getData() instanceof DeliveryMan){
           
           for ( int i =0; i<size;i++){
               DeliveryMan num = (DeliveryMan)tempNode.getData();
               if (num.getTaskStatus().equals("Available")){
                   result++;
               }
               tempNode = tempNode.getNext();
           }
       }
       return result;
   }
        
     public int getPosition(T record)
        {       
        int result = 0;
        Node tempNode = firstNode;
        for (int i = 1; i <= size; i++) 
        {
            if (tempNode.getData().equals(record)) 
            {   
                return i;
            }
            tempNode = tempNode.getNext();
        }
        return result;
        }   
        
     public T retrieveInstance(String id){
            Node tempNode = firstNode;
            T result =null;
             if (!isEmpty() && tempNode.getData() instanceof DeliveryMan){
            for (int i = 1; i <= size; i++) {
                DeliveryMan del = (DeliveryMan)tempNode.getData();
                if (del.getId().equals(id)) {
                    result = (T) del;
                }
                tempNode = tempNode.getNext();
            }
        }
             else if (!isEmpty() && tempNode.getData() instanceof Order){
            for (int i = 1; i <= size; i++) {
                Order order = (Order)tempNode.getData();
                if (order.getOrderID().equals(id)) {
                    result = (T) order;
                }
                tempNode = tempNode.getNext();
            }
        }
             
             return result;
        }
     
     public void displaydeliverymanList() {
        int No = 1;
        if (!isEmpty()) {
            Node tempNode = firstNode;
            while (tempNode != null) {
                DeliveryMan deliveryman = (DeliveryMan) tempNode.getData();
                System.out.println(No + ". DeliverymanID : " + deliveryman.getId() + "|Name : " + deliveryman.getName() +  " |Status : "
                        + deliveryman.getTaskStatus());
                No++;
                tempNode = tempNode.getNext();
            
            }
        }
    }
    
     
}
