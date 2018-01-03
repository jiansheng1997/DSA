/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import entity.DeliveryMan;

/**
 *
 * @author Lenovo
 */
public class List<T> implements QuekListInterface<T> {

	private static final int INDEX_NUMBER = 1;
	private static final int INDEX_VALUE = 0;
	private Node firstNode;
	private Node lastNode;
	private int size;

	public List() {
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
                
		for (int i = 1; i < position; i++) {
                    myNode =myNode.getNext();		
                    myNode.setData(newEntry);
		}

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
	
	public int[][] getDesc(List<DeliveryMan> list) {
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
}
