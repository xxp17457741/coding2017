
//package javaTest;

public class ArrayTest {

	/**
	 * @param args
	 * ģ��ArrayListʵ�ֶ�Ԫ�ص���ɾ����
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     ArrayList a=new ArrayList();
     a.add("hello");
     a.add("java");
     a.add("world");
     a.add("hello");
    // a.add(2,"hello");
     Iterator it=a.iterator();
      while(it.hasNext())
      {
    	  System.out.print(it.next()+" ");
     }
      System.out.println(a.size());
	}

}

