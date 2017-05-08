
//package javaTest;

import java.util.Arrays;

public class ArrayList {

	private Object[] elementData=new Object[2];
	int size;
	//���Ԫ��
	public void add(Object o)
	{
		if(size<elementData.length)
		{
			elementData[size]=o;
		}
		else{
			elementData=grow(elementData);
			elementData[size]=o;
		}
		size++;
		
	}
	//ͨ���������Ԫ��
	public void add(int index,Object o){
		if(size<elementData.length)
		{
			addInner(elementData,index,o);
		}
		else{
			elementData=grow(elementData);
			addInner(elementData,index,o);
		}
		size++;
	}
	//ͨ��������ȡԪ��
	public Object get(int index)
	{
		Object o=null;
		for(int i=0;i<size;i++)
		{
			if(i==index)
			{
				o= elementData[i];
			}
		}
		return o;
	}
	//ͨ������ɾ��Ԫ��
	public Object remove(int index){
		Object o=null;
		if(index==size)
		{
			o=elementData[size];
		}
		else{
			for(int i=0;i<size;i++)
			{
				if(i==index)
				{
				    o=elementData[index];
				    for(int x=index;x<size-1;x++)
					{
						elementData[x]=elementData[x+1];
					}
				}
	               
			}
		}
		size--;
		return o;
	}
	//ɾ�����һ��Ԫ��
	/*public Object removeLast()
	{
		int size=this.size();
		Object o=this.get(size-1);
		size--;
		return o;
	}*/
	//��ȡ��ǰԪ�ص���Ŀ
	public int size()
	{
		return size;
	}
	//�������ݺ���
	public Object[] grow(Object[] elementData)
	{
		return Arrays.copyOf(elementData, elementData.length*2);
	}
	//ͨ���������Ԫ�ص��ڲ�����
	public void addInner(Object[] elementData,int index,Object o)
	{
		for(int i=0;i<size;i++)
		{
			if(i==index)
			{
				Object t=elementData[i];
				elementData[i]=o;
				for(int x=size;x>i;x--)
				{
					if(x==index+1)
					{
						elementData[x]=t;
					}
					else
					{
					  elementData[x]=elementData[x-1];
					}
					
				}
			}
		}
	}
	public Iterator iterator()
	{
		return new ArrayListIterator(this);
	}
	private class ArrayListIterator implements Iterator
   {
		ArrayList l=null;
		int pos=0;
		private ArrayListIterator(ArrayList l)
		{
			this.l=l;
		}
		public boolean hasNext() {
			// TODO Auto-generated method stub
			boolean flag=true;
			pos++;
			if(pos>size)
			{
				flag=false;
			}
			return flag;
		}

		public Object next() {
			// TODO Auto-generated method stub
			
			return elementData[pos-1];
		}
	}

	
	

}

