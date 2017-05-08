import java.util.Iterator;
public class MyArrayList<T> implements Iterable {
    private T[] elems;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    //modCount记录对自MyArrayList对象创建以来元素结构的变更，每次add或remove都将更新modCount。
    //目的在于帮助迭代器检测集合中的变化，使在迭代器与其外部的对元素结构变化的同步。
    private int modCount;
    
    public MyArrayList() {
        clear();
    }
    
    public void clear() {
        size = 0;
        elems = (T[])new Object[capacity = DEFAULT_CAPACITY];
        modCount++;
    }
    
    public int size() { return size; }
    
    public void expand() {
        if (size < capacity) return;
        T[] old = elems;
        elems = (T[])new Object[capacity *= 2];
        for (int i = 0; i < size; i++)
            elems[i] = old[i];
    }
    public boolean find(T e) {
        
        int times = size - 1;
        while (times != -1) {
            if (e.equals(elems[times]))
                return true;
            --times;
        }
        return false;
        
        
    }
    //capacity >= 4*size 则shrink()，capacity减半
    public void shrink() {
        if (capacity < DEFAULT_CAPACITY * 2) return;
        if (capacity < size * 4) return;
        T[] old = elems;
        elems = (T[])new Object[capacity /= 2];
        for (int i = 0; i < size; i++)
            elems[i] = old[i];                
    }
    
    
    public boolean add(T e) {
        add(size, e);
        return true;
    }
    
    public void add(int index, T e) {
        if (index < 0 || size < index) throw new ArrayIndexOutOfBoundsException();
        expand();
        for (int i = size; i > index; i--)
            elems[i] = elems[i - 1];
        elems[index] = e;
        size++;
        modCount++;
    }
    
    public T get(int index) {
        if (index < 0 || size <= index) throw new ArrayIndexOutOfBoundsException();
        return elems[index];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public T remove(int index) {
        if (index < 0 || size <= index) throw new ArrayIndexOutOfBoundsException();
        T removed = elems[index];
        for (int i = index; i < size - 1; i++)
            elems[i] = elems[i + 1];
        
        size--;
        shrink();
        modCount++;
        return removed;
    }
        
    public T set(int index, T e) {
        if (index < 0 || size <= index) throw new ArrayIndexOutOfBoundsException();
        T old = elems[index];
        elems[index] = e;
        return old;
    } 
    
    //ArrayListIterator向上转型为java.util.Iterator<T>
    public java.util.Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    
    //问题：为什么java.util.Iterator要加<T>而ArrayListIterator不用加<T>?
    /*回答：因为java.util.Iterator是外部接口，是已经定义好的而不是我们在泛型类MyArrayList中添加的。
            在使用已经定义好的泛型接口或类时，
                或者不为泛型接口传入实参，即直接使用java.util.Iterator，这样java.util.Iterator中操作的元素类型全是Object。
                或者一定要为泛型接口传入实参，在本例中实参为泛型类MyArrayList<T>的T，
                    这样java.util.Iterator<T>中操作的元素类型全是T，保证了存入MyArrayList并通过Iterator<T>进行操作的元素的类型。
            
            而我们定义的ArrayListIterator无须声明ArrayListIterator<T>的原因是ArrayListIterator定义在泛型类MyArrayList<T>中，
            泛型类中的方法、成员变量、内部类(ArrayListIterator)可以任意使用泛型参数T而无须声明。
    
    */
    
    private class ArrayListIterator implements java.util.Iterator<T> {
        private int current = 0;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext() { return current < size; }
        public T next() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!hasNext()) throw new java.util.NoSuchElementException();
            
            okToRemove = true;
            return elems[current++];
        }
        public void remove() {
            if (modCount != expectedModCount)
                throw new java.util.ConcurrentModificationException();
            if (!okToRemove)
                throw new IllegalStateException();
            
            MyArrayList.this.remove(--current);
            expectedModCount++;
            okToRemove = false;
        }
    }
    
    //boolean remove(Object o) 
}
























