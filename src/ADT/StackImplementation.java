package ADT;

public class StackImplementation<T> implements StackInterface<T>{

    Node topNode;
    int count;
    
    @Override
    public boolean isEmpty() {
        return (topNode == null) && count == 0;
    }

    @Override
    public void push(T newEntry) {
        Node newNode = new Node(newEntry);
        
        if(!isEmpty()){
            newNode.setNext(topNode);
        }  
        topNode = newNode;
        count++;
    }

    @Override
    public T pop() {
        T tempNode = (T) topNode.getData();
        if (!isEmpty()){
            topNode = topNode.getNext();
        }
        count--;
        return tempNode;
    }

    @Override
    public T peekTop() {
        T result = null;
        if (!isEmpty()){
            result = (T) topNode.getData();
        }
        return result;
    }
    
    @Override
    public int size(){
        return count;
    }
    
    @Override
    public T peek(int position){
        T result = null;
        if (!isEmpty() && (position > 0 && position <= count)) {
            if (position == 1) {
                result = (T) topNode.getData();
            }
            else{
                Node nodeToPeek = topNode;
                for (int i = 1; i < position; i++) {
                    nodeToPeek = nodeToPeek.getNext();
                }
                result = (T) nodeToPeek.getData();
            }
        }
        return result;
    }
    
    @Override
    public void clear(){
        topNode = null;
        count = 0;
    }
}
