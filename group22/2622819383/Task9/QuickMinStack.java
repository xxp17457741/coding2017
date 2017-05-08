public class QuickMinStack {
    //用来当做正常的栈使用
    Stack<Integer> stack1 = new Stack<>();
    //用来存放当前最小的元素
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