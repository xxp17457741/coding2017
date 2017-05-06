//N个人编号从1到N，围坐圆圈。从1号开始传递热土豆。
//经过M次传递后拿着热土豆的人出局，最后剩下的人取胜。
import java.util.LinkedList;
public class Josephus {        
    public static void pass(int M, int N) {
        Queue<Integer> q = new Queue<>();
        for (int i = 1; i <= N; i++)
            q.enqueue(i);
        
        while (0 < q.size()) {
            M = M % N;
            //i的初始值看一下
            for (int i = 0; i < M; i++) {
                q.enqueue(q.dequeue());
            }
            System.out.print(q.dequeue() + " ");
        }
    }
    
    public static void main(String[] args) {
        pass(1, 5);
    }
}

class Queue<T> {
    LinkedList<T> l = new LinkedList<>();
    public T dequeue() {
        return l.removeFirst();
    }
    public int size() { return l.size(); }
    public void enqueue(T x) {
        l.addLast(x);
    }
}