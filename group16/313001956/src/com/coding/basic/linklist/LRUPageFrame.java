package com.coding.basic.linklist;

/**
 * ��˫������ʵ��LRU�㷨
 * 
 * @author liuxin
 *
 */
public class LRUPageFrame {

	private static class Node {

		Node prev;
		Node next;
		int pageNum;

		Node() {
		}
	}

	private int capacity;

	private Node first;// ����ͷ
	private Node last;// ����β

	public LRUPageFrame(int capacity) {

		this.capacity = capacity;
		first = null;
		last = null;
	}

	/**
	 * ��ȡ�����ж���
	 * 
	 * @param key
	 * @return
	 */
	public void access(int pageNum) {
		Node node = new Node();
		node.prev = null;
		node.pageNum = pageNum;

		if (first == null) {
			node.next = null;
			first = node;
			return;
		}
		if(judgeEqual(node, pageNum)){
			return;
		}

		node.next = first;
		first.prev = node;
		first = node;
		
		if (last == null) {
			judgeFull();
		} else {
			Node temp = last.prev;

			last.prev = null;
			last.next = null;
			last.pageNum = 0;

			temp.next = null;
			last = temp;
		}
	}

	private boolean judgeEqual(Node node, int pageNum) {
		if (first.pageNum == pageNum) {
			return true;
		}
		Node nd = first;
		while (nd != null) {
			if (nd.pageNum == pageNum) {
				if (nd.next != null) {
					nd.prev.next = nd.next;
					nd.next.prev = nd.prev;
					nd.prev = null;
					nd.next = first;
					first = nd;
				} else {
					if (last != null) {
						last = nd.prev;
					} 
					nd.prev.next = null;
					
					nd.prev = null;
					nd.next = first;
					first.prev=nd;
					first = nd;
				}

				return true;
			}
			nd = nd.next;
		}	
		return false;
	}

	// �ж������Ƿ�������˲�����last
	private void judgeFull() {
		int count = 0;
		Node node = first;
		while (node != null) {
			count++;
			if (count == this.capacity) {
				last = node;
				return;
			}
			node = node.next;
		}

		if (count >= this.capacity) {

		}
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		Node node = first;
		while (node != null) {
			buffer.append(node.pageNum);

			node = node.next;
			if (node != null) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

}
