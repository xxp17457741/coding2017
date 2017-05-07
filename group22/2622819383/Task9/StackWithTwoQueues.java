public class StackWithTwoQueues {
    //q1用来push() & pop()
    Queue<Integer> q1 = new Queue<>();
    //q2用来辅助
    Queue<Integer> q2 = new Queue<>();
    
    //O(1)
    public void push(int data) {
            q1.enqueue(data);
    }
    
    //O(n)
    public int pop() {
        int size = q1.size();
        while (1 < size--) 
            q2.enqueue(q1.dequeue);
        
        int ret = q1.dequeue();
        
        Queue<Integer> temp = q2;
        q2 = q1;
        q1 = temp;
        
        return ret;
    }
}