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
                        System.out.println("���ִ����������" + s[idx]);
                        System.out.println("���������������" + idx);
                        throw new Exception("�﷨����");                                        
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
    
    //֧�ֵİ�����ѧ�������'\0'��ʾ��ѧ���ʽ����
    static char[] operation = {'+',    '-',    '*',    '/',    '^',    '!',    '(',    ')',    '\0'};
    
    /*
    * pri[][]: 
    * ջ��������뵱ǰ����������ȼ��Ƚ�. ' '��ʾ�﷨����;
    * '>'��ʾջ����������ȼ����ڵ�ǰ����ѧ���ʽ�ж�ȡ���������˵�����Խ���ջ����������������.
    * e.g. "1+2-3"
    *      ջ�������Ϊ'+'����ǰ�����Ϊ'-'������֪�����ȼ��ȽϽ��Ϊ'>'�����Զ�'1+2'���м���
    */
    static char[][] pri = {
        /*                |------------��ǰ�����-----------------|     */
        /*                +    -    *    /    ^    !    (    )    \0    */ 
        /* --   +   */  {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* |    -   */  {'>', '>', '<', '<', '<', '<', '<', '>', '>'},
        /* ջ   *   */  {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* ��   /   */  {'>', '>', '>', '>', '<', '<', '<', '>', '>'},
        /* ��   ^   */  {'>', '>', '>', '>', '>', '<', '<', '>', '>'},
        /* ��   !   */  {'>', '>', '>', '>', '>', '>', ' ', '>', '>'},
        /* ��   (   */  {'<', '<', '<', '<', '<', '<', '<', '=', ' '},
        /* |    )   */  {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
        /* --  \0   */  {'<', '<', '<', '<', '<', '<', '<', ' ', '='}
    };
    
    //�Ƚ�ջ��������뵱ǰ����������ȼ�.
    public static char orderBetween(char stackTop, char current) {
        //System.out.println("top, cur: " + stackTop + ", " + current);
        int top = findOptr(stackTop);
        int cur = findOptr(current);
        return pri[top][cur];
    }
    
    //�ҵ�����������c��operation[]�е�����λ��.
    private static int findOptr(char c) {
        int i = 0;
        while (i < operation.length && c != operation[i])
            ++i;
        return i;
    }

    //��s[]����s[idx]��ʼ�����ֲ�����ջopnd�У����idx.
    //����idxʱs[idx]Ϊ���֣����idxʱs[idx]Ϊ������.
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
    
    private static boolean isDigit(char s) {
        return '0' <= s && s <= '9';
    }
    
    public static void main(String[] args) throws Exception {
        String s = "(0!+1)*2^(3!+4)-(5!-67-(8+9))";
        System.out.println(convert2Postfix(s));
    }
    
}

























