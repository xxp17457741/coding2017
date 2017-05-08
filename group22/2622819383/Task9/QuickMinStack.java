public class QuickMinStack {
    //��������������ջʹ��
    Stack<Integer> stack1 = new Stack<>();
    //������ŵ�ǰ��С��Ԫ��
    Stack<Integer> stack2 = new Stack<>();
    
    public void push(int data) {
        stack1.push(data);
        
        if (data <= stack2.peek())
            stack2.push(data);
    }
    
    public int pop() {
        int ret = stack1.pop();
        
        if (ret == stack2.peek())
            stack2.pop();
        
        return ret;
    }
    
    public int findMin() {
        return stack2.peek();
    }
    
}