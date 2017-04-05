

/**
 * 用双向链表实现LRU算法
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
    private Node first;// 链表头，哨兵节点，便于插入删除
    private Node last; // 链表尾，哨兵节点，便于插入删除


    public LRUPageFrame(int capacity) {
        this.capacity = capacity;
        first = new Node(-1, null, null);
        last = new Node(-1, first, null);
        first.next = last;
        size = 0;
    }

    /**
    * 获取缓存中对象
    * 
    * @param key
    * @return
    */
    /*Node(pageNum) = p.
     *如果p在缓存中存在，将p提至链表头addFirst(remove(p));
     *如果p在缓存中不存在，if(size >= capacity) removeLast(); 在链表头创建p.    
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