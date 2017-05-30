package com.coding.basic.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	private int size;
	
	public CircleQueue(){
		elementData = new Object[DEFAULT_SIZE];
	}
	
	public CircleQueue(int capacity){
		elementData = new Object[capacity];
	}
	
	public boolean isEmpty() {
		return size == 0;
    }

	public boolean isFull(){
		
		return elementData.length == size;
	}
    public int size() {
        return size;
    }

    public void enQueue(E data) {
    	if(isFull()){
    		throw new RuntimeException("The queue is full");
    	}
    	rear = (rear) % elementData.length;
    	elementData[rear++] = data;
    	size++;
    }

    public E deQueue() {
    	if(isEmpty()){
    		throw new RuntimeException("The queue is empty");
    	}
		E e = (E)elementData[front];
    	elementData[front] = null;
    	front = (front + 1) % elementData.length;
    	size--;
        return e;
    }
}
