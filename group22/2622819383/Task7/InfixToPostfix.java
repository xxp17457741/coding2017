public class InfixToPostfix {
    public static String convert2Postfix(String infixStr) throws Exception {
        infixStr += '\0';
        char[] s = infixStr.toCharArray();
        Stack<Float> opnd = new Stack<>();
        Stack<Character> optr = new Stack<>();
        StringBuilder ret = new StringBuilder();
        optr.push('\0');
        int idx = 0;
        
        while (!optr.isEmpty()) {
            if (isDigit(s[idx])) {
                idx = readNum(s, idx, opnd);
                append(ret, opnd.peek());
            }
            
            else {
                switch (orderBetween(optr.peek(), s[idx])) {
                    case '<':
                        optr.push(s[idx++]);
                        break;
                    case '=':
                        optr.pop(); idx++;
                        break;
                    case '>':
                        ret.append(optr.pop()).append(' ');
                        break;    
                    default:
                        System.out.println("出现错误运算符：" + s[idx]);
                        System.out.println("错误运算符索引：" + idx);
                        throw new Exception("语法错误");                                        
                }
            }
        }
        
        return new String(ret);
    }
    
    private static void append(StringBuilder s, float opnd) {
        if ((float)(int)opnd != opnd)
            s.append(opnd).append(' ');
        else 
            s.append((int)opnd).append(' ');
    }
    
    //支持的八种数学运算符，'\0'表示数学表达式结束
    static char[] operation = {'+',    '-',    '*',    '/',    '^',    '!',    '(',    ')',    '\0'};
    
    /*
    * pri[][]: 
    * 栈顶运算符与当前运算符的优先级比较. ' '表示语法错误;
    * '>'表示栈顶运算符优先级大于当前从数学表达式中读取的运算符，说明可以进行栈顶运算符代表的运算.
    * e.g. "1+2-3"
    *      栈顶运算符为'+'，当前运算符为'-'，查表可知，优先级比较结果为'>'，可以对'1+2'进行计算
    */
    static char[][] pri = {
        /*                |------------当前运算符-----------------|     */
        /*                +    -    *    /    ^    !    (    )    \0    */ 
        /* --   +   */  {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* |    -   */  {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* 栈   *   */  {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* 顶   /   */  {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* 运   ^   */  {'>', '>', '>', '>', '>', '<', '<', '>', '>'},
        /* 算   !   */  {'>', '>', '>', '>', '>', '>', ' ', '>', '>'},
        /* 符   (   */  {'<', '<', '<', '<', '<', '<', '<', '=', ' '},
        /* |    )   */  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        /* --  \0   */  {'<', '<', '<', '<', '<', '<', '<', ' ', '='}
    };
    
    //比较栈顶运算符与当前运算符的优先级.
    public static char orderBetween(char stackTop, char current) {
        //System.out.println("top, cur: " + stackTop + ", " + current);
        int top = findOptr(stackTop);
        int cur = findOptr(current);
        return pri[top][cur];
    }
    
    //找到传入的运算符c在operation[]中的索引位置.
    private static int findOptr(char c) {
        int i = 0;
        while (i < operation.length && c != operation[i])
            ++i;
        return i;
    }

    //读s[]中以s[idx]开始的数字并存入栈opnd中，输出idx.
    //传入idx时s[idx]为数字，输出idx时s[idx]为非数字.
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
    
    private static boolean isDigit(char s) {
        return '0' <= s && s <= '9';
    }
    
    public static void main(String[] args) throws Exception {
        String s = "(0!+1)*2^(3!+4)-(5!-67-(8+9))";
        System.out.println(convert2Postfix(s));
    }
    
}

























