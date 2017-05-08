public class TwoStackInOneArray {
    
    private Object[] data = new Object[10];
    private int top1 = 0;
    private int top2 = data.length - 1;
    private int size1;
    private int size2;
    
    public void push1(Object o) {
        ++top1;
        if (isFull())
            expand();
        data[top1] = o;
        ++size1;
    }
    
    public void push2(Object o) {
        --top2;
        if (isFull())
            expand();
        data[top2] = o;
        ++size2;
    }
    
    public Object pop1() {
        Object ret = data[top1];
        data[top1--] = null;
        size1--;
        
        return ret;
    }
    public Object pop2() {
        Object ret = data[top2];
        data[top2++] = null;
        size2--;
    }
    
    public Object peek1() {
        return data[top1];
    }
    public Object peek1() {
        return data[top2];
    }
    
    private void expand() {
        Object[] old = data;
        data = new Object[old.length * 2];
        
        for (int i = top1; 0 <= i; i--)
            data[i] = old[i];
                
        int idx = data.length - 1;
        for (int j = old.length - 1; top2 <= j; j--, idx--)
            data[idx] = old[j];
        
        top2 = ++idx;           
    }
    
    private boolean isFull() {
        return top1 == top2;
    }
}