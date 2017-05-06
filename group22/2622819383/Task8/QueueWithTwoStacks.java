//用两个stack实现queue
class QueueWithTwoStacks<T> {
    //stack1用于添加元素
    private Stack<T> stack1 = new Stack<>();
    //stack2用于删除元素
    private Stack<T> stack2 = new Stack<>();
    
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
    public void addToTail(T e) {
        stack1.push(e);
    }
    
    public T getFromHead() {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException();
        //stack1空 & stack2非空
        if (stack1.isEmpty())
            return stack2.pop();
        //stack1非空 & stack2空
        else if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
            return stack2.pop();
        } 
        //stack1非空 & stack2非空
        else 
            return stack2.pop();
    }
    
    public int size() {
        return stack2.size() + stack1.size();
    }
    
    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.getFromHead();
        for (int i = 0; i < 4; i++)
            q.addToTail(i);
        System.out.println(q.getFromHead());
        System.out.println(q.getFromHead());
        for (int i = 4; i < 10; i++)
            q.addToTail(i);        
        while (q.size() > 0)
            System.out.println(q.getFromHead());
        
    }
    
}