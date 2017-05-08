package com.github.xiaozi123.coding2017.secondWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] reverseArray=new int[origin.length];
		for (int i = 0; i < reverseArray.length/2; i++) {
			reverseArray[i]=origin[origin.length-i-1];
		}
		
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		int[] newArray=new int[oldArray.length];
		
		for (int i = 0,j=0; i < oldArray.length; i++,j++) {
			if (oldArray[i]==0) {
				i++;
			}
			newArray[j]=oldArray[i];
		}
		return newArray;
		
		
	}
	
	/**
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		ArrayList list1 = new ArrayList(Arrays.asList(array1));
		ArrayList list2 = new ArrayList(Arrays.asList(array2));
		list1.removeAll(list2);
		Integer[] integers=(Integer[]) list1.toArray(); 
		int[] intArray = new int[integers.length];
		for(int i=0; i < integers.length; i ++)
		{
		    intArray[i] = integers[i].intValue();
		}
		return intArray;
	}
	/**
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		if (oldArray==null||size>=0) {
			throw new IndexOutOfBoundsException("����.");
			
		}
		int[] resultArray=new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, resultArray, 0,oldArray.length);
		return resultArray;
	}
	
	/**
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int[] array=new int[max];
		for (int i = 0; i < max; i++) {
			array[i]=getFibo(i);//��i��
			if (array[i]>=max) {
				break;
			}
		}
		return array;
	}
	
	// ��ȡ��i��
	private static int getFibo(int i) {
		  if (i == 1 || i == 2)
		  return 1;
		  else
		  return getFibo(i - 1) + getFibo(i - 2);}
		 
		
	
	/**
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] array=new int[max];
		for (int i = 0,j=0; i < max; i++) {
			if (isPrime(i)) {
				array[j]=i;
				j++;
			}
			
		}
		return array;
	}
	public static boolean isPrime(int a) {
		boolean flag = true;

		if (a < 2) {// ������С��2
			return false;
		} else {

			for (int i = 2; i <= Math.sqrt(a); i++) {

				if (a % i == 0) {// ���ܱ���������˵����������������false

					flag = false;
					break;// ����ѭ��
				}
			}
		}
		return flag;
	}
	
	/**
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] array=new int[max];
		for (int i = 0,j=0; i < max; i++) {
			if (isPrime(i)) {
				array[j]=i;
				j++;
			}
			
		}
		return array;
	}
	
	public static boolean isPerfectNumber(int i) {
		int s=0;
	    for(int j=1;j<i;j++){
	        if(i % j==0)
	           s=s+j;
	        if(s==i)
	        	return true;
	        }
	           
		return false;
	}

	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		if (array.length>0&&array!=null) {
			StringBuffer stringBuffer=new StringBuffer();
			stringBuffer.append(array[0]);
		for (int i = 1; i < array.length; i++) {
			stringBuffer.append(seperator).append(array[i]);
		}
		return stringBuffer.toString();
		}
		return null;
	}
	

}
