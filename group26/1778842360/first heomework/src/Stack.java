
//package javaTest;

public class Stack {
   private ArrayList elementData = new ArrayList();
	
	//��Ԫ��ѹ��ջ
	public void push(Object o){	
		elementData.add(o);
	}
	//��Ԫ�س�ջ
	public Object pop(){
		Object o=elementData.remove(elementData.size-1);
		return o;
	}
	//��ȡջ��Ԫ��
	public Object peek(){
		Object o=elementData.get(elementData.size-1);
		return o;
	}
	//�ж�ջ�Ƿ�Ϊ��
	public boolean isEmpty(){
		boolean flag=true;
		if(elementData.size>0)
		{
			flag=false;
		}
		return flag;
	}
	//��ȡջ�Ĵ�С
	public int size(){
		return elementData.size;
	}
}

