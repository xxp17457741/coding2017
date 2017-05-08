package week2.com.coding.basic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ArrayUtil
{
    
    /**
     * ����һ����������a , �Ը������ֵ�����û� ���磺 a = [7, 9 , 30, 3] , �û���Ϊ [3, 30, 9,7] ��� a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
     * 
     * @param origin
     * @return
     */
    public void reverseArray(int[] origin)
    {
        int[] tempArrays = new int[origin.length];
        int j = 0;
        for (int i = origin.length - 1; i > -1; i--)
        {
            tempArrays[j] = origin[i];
            j++;
        }
    }
    
    /**
     * ���������µ�һ�����飺 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��
     * {1,3,4,5,6,6,5,4,7,6,7,5}
     * 
     * @param oldArray
     * @return
     */
    
    public int[] removeZero(int[] oldArray)
    {
        // ArrayList����Ҫ֪�����ȣ��������ڴ˴����
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < oldArray.length; i++)
        {
            if (oldArray[i] != 0)
            {
                list.add(oldArray[i]);
            }
        }
        int[] newArray = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
        {
            newArray[i] = list.get(i);
        }
        return newArray;
        
    }
    
    /**
     * ���������Ѿ�����õ��������飬 a1��a2 , ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ������� ���� a1 = [3, 5, 7,8] a2 = [4, 5, 6,7] �� a3
     * Ϊ[3,4,5,6,7,8] , ע�⣺ �Ѿ��������ظ�
     * 
     * @param array1
     * @param array2
     * @return
     */
    
    public int[] merge(int[] array1, int[] array2)
    {
        //Set���ϲ������ظ�ֵ
        Set<Integer> set = new HashSet<Integer>();
        for (int i : array1)
        {
            set.add(i);
        }
        for (int i : array2)
        {
            set.add(i);
        }
        int[] newArr = new int[set.size()];
        Iterator<Integer> it = set.iterator();
        int i = 0;
        while (it.hasNext())
        {
            newArr[i] = it.next();
            i++;
        }
        // ��newArrð������
        for (int j = 0; j < newArr.length - 1; j++)
        {
            for (int k = 0; k < newArr.length - 1 - j; k++)
            {
                int temp = 0;
                if (newArr[k] > newArr[k + 1])
                {
                    temp = newArr[k];
                    newArr[k + 1] = temp;
                }
            }
        }
        return newArr;
    }
    
    /**
     * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size ע�⣬�������Ԫ��������������Ҫ���� ���� oldArray = [2,3,6] , size =
     * 3,�򷵻ص�������Ϊ [2,3,6,0,0,0]
     * 
     * @param oldArray
     * @param size
     * @return
     */
    public  int[] grow(int[] oldArray, int size)
    {
        int[] newArray = Arrays.copyOf(oldArray, oldArray.length + size);
        return newArray;
    }
    
    /**
     * 쳲���������Ϊ��1��1��2��3��5��8��13��21...... ������һ�����ֵ�� ����С�ڸ�ֵ������ ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13] max = 1, �򷵻ؿ����� []
     * 
     * @param max
     * @return
     */
    public  int[] fibonacci(int max)
    {
        // f(n)=f(n-1)+f(n-2)
        // f(0)=1 f(1)=1 f(2)=f(0)+f(1)=2
        List<Integer> list = new ArrayList<Integer>();
        int[] newArr = null;
        if (max == 1)
            return newArr;
        int num = 0;
        int x = 1, y = 1;
        list.add(1);// f(0)=1;
        list.add(1);// f(1)=1;
        while (true)
        {
            num = x + y;
            x = y;
            y = num;
            if (num >= max)
                break;
            list.add(num);
        }
        newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    /**
     * 쳲��������еĵݹ��㷨
     * 
     * @param i
     * @return
     */
    public static int getFiboo(int i)
    {
        if (i == 1 || i == 2)
            return 1;
        else
            return getFiboo(i - 1) + getFiboo(i - 2);
    }
    
    /**
     * ����С�ڸ������ֵmax�������������� ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
     * 
     * @param max
     * @return
     */
    public int[] getPrimes(int max)
    {
        int[] newArr = null;
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < max; i++)
        {
            boolean flag = isPrime(i);
            if (flag)
                list.add(i);
        }
        newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    public static boolean isPrime(int n)
    {
        if (n == 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;// ż���϶���������
        for (int i = 3; i < n; i += 2)// ȥ��ż�����ж�
        {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    
    /**
     * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3 ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
     * 
     * @param max
     * @return
     */
    public  int[] getPerfectNumbers(int max)
    {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> factorList = null;
        for (int i = 1; i < max; i++)
        {
            // ���е�������������
            if (isPrime(i))
            {
                continue;
            }
            factorList = getFactor(i);
            int count = 0;
            for (int j = 0; j < factorList.size(); j++)
            {
                count += factorList.get(j);
            }
            if (count != i)
                continue;
            list.add(i);
        }
        int[] newArr = new int[list.size()];
        for (int k = 0; k < list.size(); k++)
        {
            newArr[k] = list.get(k);
        }
        return newArr;
    }
    
    /**
     * ��һ��������������
     **/
    public static List<Integer> getFactor(int number)
    {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 1; i < number; i++)
        {
            if (number % i != 0)
                continue;
            list.add(i);
        }
        return list;
    }
    
    /**
     * ��seperator ������ array���������� ����array= [3,8,9], seperator = "-" �򷵻�ֵΪ"3-8-9"
     * 
     * @param array
     * @param s
     * @return
     */
    public  String join(int[] array, String seperator)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++)
        {
            if (i == array.length - 1)
            {
                sb.append(array[i]);
                break;
            }
            sb.append(array[i] + seperator);
        }
        return sb.toString();
    }
    
}