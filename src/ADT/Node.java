package ADT;

public class Node<T> {

  private T data; 
  private Node next; 
  private Node previous;   

    public Node getPrevious() {
        return previous;
    }

    public Node(T data, Node next, Node previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

  public Node(T data) {
    this.data = data;
    next = null;
   
  }

  public Node(T data, Node next) {
    this.data = data;
    this.next = next;
  }

  public void setData(T data) {
    this.data = data;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public Node getNext() {
    return next;
  }

}
