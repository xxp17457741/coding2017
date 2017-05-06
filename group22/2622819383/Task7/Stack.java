public class Stack<T> {
    private MyArrayList<T> elementData = new MyArrayList<>();

    public void push(T o){
        elementData.add(o);
    }

    public T pop(){
        return elementData.remove(size() - 1);
    }

    public T peek(){
        if (size() == 0) return null;
        return elementData.get(size() - 1);
    }
    
    public boolean isEmpty(){
        return size() == 0;
    }
    
    public int size(){
        return elementData.size();
    }
    
    public boolean find(T e) {
        return elementData.find(e);
    }
}
