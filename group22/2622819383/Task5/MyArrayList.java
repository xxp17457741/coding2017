import java.util.Iterator;
public class MyArrayList<T> implements Iterable {
    private T[] elems;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    //modCount��¼����MyArrayList���󴴽�����Ԫ�ؽṹ�ı����ÿ��add��remove��������modCount��
    //Ŀ�����ڰ�����������⼯���еı仯��ʹ�ڵ����������ⲿ�Ķ�Ԫ�ؽṹ�仯��ͬ����
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
    //capacity >= 4*size ��shrink()��capacity����
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
    
    //ArrayListIterator����ת��Ϊjava.util.Iterator<T>
    public java.util.Iterator<T> iterator() {
        return new ArrayListIterator();
    }
    
    //���⣺Ϊʲôjava.util.IteratorҪ��<T>��ArrayListIterator���ü�<T>?
    /*�ش���Ϊjava.util.Iterator���ⲿ�ӿڣ����Ѿ�����õĶ����������ڷ�����MyArrayList����ӵġ�
            ��ʹ���Ѿ�����õķ��ͽӿڻ���ʱ��
                ���߲�Ϊ���ͽӿڴ���ʵ�Σ���ֱ��ʹ��java.util.Iterator������java.util.Iterator�в�����Ԫ������ȫ��Object��
                ����һ��ҪΪ���ͽӿڴ���ʵ�Σ��ڱ�����ʵ��Ϊ������MyArrayList<T>��T��
                    ����java.util.Iterator<T>�в�����Ԫ������ȫ��T����֤�˴���MyArrayList��ͨ��Iterator<T>���в�����Ԫ�ص����͡�
            
            �����Ƕ����ArrayListIterator��������ArrayListIterator<T>��ԭ����ArrayListIterator�����ڷ�����MyArrayList<T>�У�
            �������еķ�������Ա�������ڲ���(ArrayListIterator)��������ʹ�÷��Ͳ���T������������
    
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
























