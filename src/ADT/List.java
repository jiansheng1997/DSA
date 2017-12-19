/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author Lenovo
 */
public class List<T> implements QuekListInterface {

	private Node firstNode;
	private Node lastNode;
	private int size;

	public List() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	public void add(Object newEntry) {
		size++;
		Node myNode = new Node((T) newEntry);
		Node nodeTemp = firstNode;
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

		if (position > size) {
			position = size;
		}

		for (int i = 0; i < position - 1; i++) {
			myNode.setNext(myNode.getNext());
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
