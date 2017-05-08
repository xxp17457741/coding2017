
public class StackUtil {


    /**
    * ����ջ�е�Ԫ����Integer, ��ջ����ջ���� : 5,4,3,2,1 ���ø÷����� Ԫ�ش����Ϊ: 1,2,3,4,5
    * ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
    */
    public static void reverse(Stack s) {        
        Stack temp = new Stack();
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }
        
        s = temp;
    }

    /**
    * ɾ��ջ�е�ĳ��Ԫ�� ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
    * 
    * @param o
    */
    public static void remove(Stack s,Object o) {
        Stack temp = new Stack();
        while (s.peek() != o) {
            temp.push(s.pop());
        }
        s.pop();
        
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
    }

    /**
    * ��ջ��ȡ��len��Ԫ��, ԭ����ջ��Ԫ�ر��ֲ���
    * ע�⣺ֻ��ʹ��Stack�Ļ�����������push,pop,peek,isEmpty�� ����ʹ������һ��ջ������
    * @param len
    * @return
    */
    public static Object[] getTop(Stack s,int len) {
        Stack temp = new Stack();
        Object[] ret = new Object[len];
        int index = 0;
        
        while (0 < len--) {
            temp.push(ret[index] = s.pop());
            index++;
        }
        while (!temp.isEmpty())
            s.push(temp.pop());
        return ret;
    }
    /**
    * �ַ���s ���ܰ�����Щ�ַ���  ( ) [ ] { }, a,b,c... x,yz
    * ʹ�ö�ջ����ַ���s�е������ǲ��ǳɶԳ��ֵġ�
    * ����s = "([e{d}f])" , ����ַ����е������ǳɶԳ��֣� �÷�������true
    * ��� s = "([b{x]y})", ����ַ����е����Ų��ǳɶԳ��ֵģ� �÷�������false;
    * @param s
    * @return
    */
    public static boolean isValidPairs(String s){
        char[] arrS = s.toCharArray();
        Stack temp = new Stack();
        for (i = 0; i < arrS.length; i++) {
            switch(s[i]) {
                case '(': case '[': case '{': temp.push(s[i]); break;
                case ')':
                    //��Ҫ�����ж�temp�Ƿ�Ϊ�հ���
                    if (temp.isEmpty() || temp.pop() != '(') return false;
                    break;
                case ']':
                    if (temp.isEmpty() || temp.pop() != '[') return false;
                    break;
                case '}':
                    if (temp.isEmpty() || temp.pop() != '{') return false;
                    break;
                default: break;
            }
        }
        
        
        return temp.isEmpty();
    }


}
