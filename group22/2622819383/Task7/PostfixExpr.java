public class PostfixExpr {
    public static float calculate(String postfixExpr) throws Exception {
        char[] s = postfixExpr.toCharArray();
        Stack<Float> opnd = new Stack<>();
        int idx = 0;
        while (idx < s.length) {
            if (isDigit(s[idx]))
                //++: 跳过空格.
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
        float value = 1;           //小数部分各位权值：10^(-n)
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
            System.out.println("期待运算符：'!'，当前运算符为：" + c);
            throw new Exception("运算符错误");
        }
        
        if (temp == 0)
            ret = 1;
        
        while (--temp > 1) {
            ret *= temp;
        }   
        //System.out.println("" + opnd + c + " = " + ret);
        return ret;
    }
    
    //对需要两个操作数的运算符进行运算并返回结果
    private static float calcu(float opnd1, char optr, float opnd2) throws Exception {
        float ret;
        switch(optr) {
            case '+': ret = opnd1 + opnd2; break;
            case '-': ret = opnd1 - opnd2; break;
            case '*': ret = opnd1 * opnd2; break;
            case '/': ret = opnd1 / opnd2; break;
            case '^': ret = (float)Math.pow(opnd1, opnd2); break;
            default: 
                System.out.println("出现未知运算符：" + optr);
                throw new Exception("不支持的运算符");              
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
































