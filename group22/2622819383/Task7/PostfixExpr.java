public class PostfixExpr {
    public static float calculate(String postfixExpr) throws Exception {
        char[] s = postfixExpr.toCharArray();
        Stack<Float> opnd = new Stack<>();
        int idx = 0;
        while (idx < s.length) {
            if (isDigit(s[idx]))
                //++: �����ո�.
                idx = 1 + readNum(s, idx, opnd);
                
            else {
                if (s[idx] == '!')
                    opnd.push(calcu('!', opnd.pop()));
                else {
                    float pOpnd2 = opnd.pop(), pOpnd1 = opnd.pop();
                    opnd.push(calcu(pOpnd1, s[idx], pOpnd2));
                }
                idx += 2;
            }
        }
        
        return opnd.pop();
    }
    
    private static int readNum(char[] s, int idx, Stack<Float> opnd) {
        float current = 0;
        float value = 1;           //С�����ָ�λȨֵ��10^(-n)
        boolean isDecimal = false;
        opnd.push((float)(s[idx++] - '0'));
        
        while (idx < s.length) {            
            if (s[idx] == '.') {
                isDecimal = true;
                ++idx;                   
            }
            else if (isDigit(s[idx])) {
                current = s[idx] - '0';
                if (!isDecimal) {
                    opnd.push(opnd.pop() * 10 + current);
                    ++idx;
                }
                else {
                    value /= 10;
                    opnd.push(opnd.pop() + current * value);
                    ++idx;
                }
            }
            else
                break;           
        }
        //System.out.println(opnd.peek());
        return idx;
    }
    
    private static float calcu(char c, float opnd) throws Exception {
        
        float ret = opnd;
        float temp = opnd;
        
        if (c != '!') {
            System.out.println("�ڴ��������'!'����ǰ�����Ϊ��" + c);
            throw new Exception("���������");
        }
        
        if (temp == 0)
            ret = 1;
        
        while (--temp > 1) {
            ret *= temp;
        }   
        //System.out.println("" + opnd + c + " = " + ret);
        return ret;
    }
    
    //����Ҫ������������������������㲢���ؽ��
    private static float calcu(float opnd1, char optr, float opnd2) throws Exception {
        float ret;
        switch(optr) {
            case '+': ret = opnd1 + opnd2; break;
            case '-': ret = opnd1 - opnd2; break;
            case '*': ret = opnd1 * opnd2; break;
            case '/': ret = opnd1 / opnd2; break;
            case '^': ret = (float)Math.pow(opnd1, opnd2); break;
            default: 
                System.out.println("����δ֪�������" + optr);
                throw new Exception("��֧�ֵ������");              
        } 
        //System.out.println("" + opnd1 + optr + opnd2 + " = " + ret); 
        return ret;
    }
    
    private static boolean isDigit(char s) {
        return '0' <= s && s <= '9';
    }
    
    public static void main(String[] args) throws Exception {
        String s = "0 ! 1 + 2 3 ! 4 + ^ * 5 ! 67 - 8 9 + - -";
        System.out.println(calculate(s));
    }
}
































