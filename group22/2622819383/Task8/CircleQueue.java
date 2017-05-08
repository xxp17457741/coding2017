class CircleQueue<T> {
    private int size;
    private int capacity;
    private int headPos;
    private int tailPos;
    private T[] theItems;
    
    public CircleQueue(int maxSize) {
        theItems = (T[])new Object[capacity = maxSize];
        size = 0;
        headPos = tailPos = 0;
    }
    
    public CircleQueue() {
        this(100);
    }
    
    private boolean isFull() { return size == capacity; }
    public boolean isEmpty() { return size == 0; }
    
    public void enqueue(T e) {
        if (!isFull()) {
            theItems[++tailPos % capacity] = e;
            size++;
        }
        else
            throw new IndexOutOfBoundsException();
    }
    
    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        T ret = null;
        
        ret = theItems[headPos];
        headPos = ++headPos % capacity;
        size--;
        
        return ret;
    }
}