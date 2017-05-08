

/**
 * ��˫������ʵ��LRU�㷨
 * 
 *
 */
public class LRUPageFrame {

    private static class Node {
        int pageNum;
        Node prev;
        Node next;
        
        Node(int num, Node p, Node n) {
            pageNum = num; prev = p; next = n;
        }   
    }
    
    private int size;
    private int capacity;
    private Node first;// ����ͷ���ڱ��ڵ㣬���ڲ���ɾ��
    private Node last; // ����β���ڱ��ڵ㣬���ڲ���ɾ��


    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
        first = new Node(-1, null, null);
        last = new Node(-1, first, null);
        first.next = last;
        size = 0;
    }

    /**
    * ��ȡ�����ж���
    * 
    * @param key
    * @return
    */
    /*Node(pageNum) = p.
     *���p�ڻ����д��ڣ���p��������ͷaddFirst(remove(p));
     *���p�ڻ����в����ڣ�if(size >= capacity) removeLast(); ������ͷ����p.    
    */
    public void access(int pageNum) {
        Node p = find(pageNum);
        if (p != last)
            addFirst(remove(p));
        else {            
            if (size >= capacity)
                removeLast();
            addFirst(pageNum);
        }

    }
    private Node find(int num) {
        Node p = first;
        while ((p = p.next) != last && p.pageNum != num);
        return p;
    }
    
    private void addFirst(int num) {
        Node p = new Node(num, first, first.next);
        size++;
    }
    
    private int remove(Node p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        int removed = p.pageNum;
        size--;
        return removed;       
    }
    
    private void removeLast() {
        last.prev.prev.next = last;
        last.prev = last.prev.prev;
        size--;
    }



    public String toString(){
        StringBuilder buffer = new StringBuilder();
        Node node = first;
        
        while(node != null){            
            buffer.append(node.pageNum);			
            node = node.next;
            
            if(node != null) buffer.append(",");        
        }        
        return buffer.toString();
    }
}