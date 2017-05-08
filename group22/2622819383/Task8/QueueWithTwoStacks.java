//������stackʵ��queue
class QueueWithTwoStacks<T> {
    //stack1�������Ԫ��
    private Stack<T> stack1 = new Stack<>();
    //stack2����ɾ��Ԫ��
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
        //stack1�� & stack2�ǿ�
        if (stack1.isEmpty())
            return stack2.pop();
        //stack1�ǿ� & stack2��
        else if (stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
            return stack2.pop();
        } 
        //stack1�ǿ� & stack2�ǿ�
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