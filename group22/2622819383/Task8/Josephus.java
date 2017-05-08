//N���˱�Ŵ�1��N��Χ��ԲȦ����1�ſ�ʼ������������
//����M�δ��ݺ��������������˳��֣����ʣ�µ���ȡʤ��
import java.util.LinkedList;
public class Josephus {        
    public static void pass(int M, int N) {
        Queue<Integer> q = new Queue<>();
        for (int i = 1; i <= N; i++)
            q.enqueue(i);
        
        while (0 < q.size()) {
            M = M % N;
            //i�ĳ�ʼֵ��һ��
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