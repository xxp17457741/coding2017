package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {
	
	//存放入数据
	private Queue<Integer> queue1;
	
	//中转数据
    private Queue<Integer> queue2;

    public StackWithTwoQueues(){
    	queue1 = new Queue<>();
    	queue2 = new Queue<>();
    }
    
    public void push(int data) {       
    	queue1.enQueue(data);
    }

    public int pop() {
    	if(queue1.isEmpty() && queue2.isEmpty()){
    		throw new RuntimeException("stack is empty");
    	}
    	if(!queue1.isEmpty()){
    		while (queue1.size() != 1) {
				queue2.enQueue(queue1.deQueue());
			}
    	}
    	Integer deQueue = queue1.deQueue();
    	while (!queue2.isEmpty()) {
			queue1.enQueue(queue2.deQueue());
		}
       return deQueue.intValue();
    }

    
}
