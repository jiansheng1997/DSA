package ADT;

public interface StackInterface<T> {
    
    public boolean isEmpty();
    
    public void push(T newEntry);
    
    public T pop();
    
    public T peekTop();
    
    public int size();
    
    public T peek(int position);
    
    public void clear();
    
}
