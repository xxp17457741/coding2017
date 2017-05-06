public class StackUtil {
    public static void reverse(Stack<Integer> s) {
        if (s == null || s.isEmpty()) return;
        
        Integer top = s.pop();
        reverse(s);
        addToBottom(s, top);
    }
    
    private static void addToBottom(Stack<Integer> s, int num) {
        if (s.size() == 0)
            s.push(num);
        
        else {
            int top = s.pop();
            addToBottom(s, num);
            s.push(top);
        }
    }
    
    public static void remove(Stack s, Object o) {
        if (s.isEmpty()) return;
        
        Stack temp = new Stack();
        
        while (!s.isEmpty()) {
            Object val = s.pop();
            if (!value.equals(o))
                temp.push(value);
        }
        
        while (!temp.isEmpty())
            s.push(temp.pop());
            
    }
    
    public static Object[] getTop(Stack s, int len) {
        if (s == null || s.isEmpty() || s.size() < len || len <= 0)
            return null;
        
        Stack temp = new Stack();
        int idx = 0;
        Object ret = new Object[len];
        while (!s.isEmpty()) {
            Object val = s.pop();
            temp.push(value);
            result[i++] = val;
            if (i == len)
                break;
        }
        while (temp.isEmpty())
            s.push(temp.pop());
        
        return ret;
    }
    
    public static boolean isValidPairs(String s) {
        char[] arrS = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        while (idx++ < arrS.length)
            switch (arrS[idx]) {
                case '(':
                case '[':
                case '{':
                    stack.push(arrS[idx]); break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
                    
                default:
                    break;
            }
            
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < 6; i++)
            s.push(i);
        reverse(s);
        while (!s.isEmpty())
            System.out.println(s.pop());
    }
}
