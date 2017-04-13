package com.coding.basic.linklist;

/**
 * 用双向链表实现LRU算法
 * @author liuxin
 *
 */
public class LRUPageFrame {
	
	private static class Node {
		
		Node prev;
		Node next;
		int pageNum;

		Node(Node prev, Node next,int pageNum) {
			this.prev = prev;
			this.next = next;
			this.pageNum = pageNum;
		}
	}

	private int capacity;
	
	private Node first;// 链表头
	private Node last;// 链表尾
	
	private int size;

	
	public LRUPageFrame(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * 获取缓存中对象
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		if(first != null && first.pageNum == pageNum){
			return;
		}
		if(first != null){
			Node node = checkHaveAndRemove(pageNum);
			if(node == null && size + 1 > capacity){
				removeLast();
			}
		}
		addFrist(pageNum);
	}
	
	private Node checkHaveAndRemove(int pageNum){
		Node node = first;
		while(node != null){
			if(node.pageNum == pageNum){
				return remove(node);
			}
			node = node.next;
		}
		return null;
	}
	
	private Node remove(Node node){
		Node prev = node.prev;
		Node next = node.next;
		if(prev != null){
			prev.next = next;
		}
		if(next != null){
			next.prev = prev;
		}
		if (last == node){
			last = node.prev;  
			last.next = null;
		}
		if (first == node){
			first = node.next;  
			first.prev = null;
		}
		node.prev = null;
		node.next = null;
		--size;
		return node;
	}
	
	private Node removeLast(){
		Node node = last;
		Node prev = last.prev;
		prev.next = null;
		last.prev = null;
		last = prev;
		--size;
		return node;
	}
	
	private void addFrist(int pageNum){
		if(first == null){
			last = first = new Node(null, null, pageNum);
			++size;
			return;
		}
		Node node = first;
		first = new Node(null, node, pageNum);
		node.prev = first;
		if(last == null){
			last = node;
			node.next = null;
		}
		++size;
	}
	
	public String toString(){
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while(node != null){
			buffer.append(node.pageNum);			
			
			node = node.next;
			if(node != null){
				buffer.append(",");
			}
		}
		return buffer.toString();
	}
}
