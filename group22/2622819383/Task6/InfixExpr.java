//��׺���ʽ��ֵ
//����ο��ԡ����ݽṹ��
public class InfixExpr {
    
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
    public static int readNum(char[] s, int idx, Stack<Float> opnd) {
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
    
    public static boolean isDigit(char s) {
        return '0' <= s && s <= '9';
    }
           
    //�Ա��ʽstr��ֵ
    public static float evaluate(String str) throws Exception {
        str += '\0';                                //��ǰ�������ʽ�����ı�־
        char[] s = str.toCharArray();
        Stack<Float> opnd = new Stack<>();          //opnd: �洢������
        Stack<Character> optr = new Stack<>();      //optr: �洢�����
        optr.push('\0');                            //�ڱ��ڵ�
        int i = 0;                                  //s������
        while (!optr.isEmpty()) {
            if (isDigit(s[i])) {
                //readNum(char[], int, Stack<Float>)����ֵΪchar[]��int���һ���������ַ�������.                
                i = readNum(s, i, opnd); 
            }
            else
                //�Ƚϵ�ǰ�������ջ�������.                
                switch (orderBetween(optr.peek(), s[i])) {
                    //��ջ����������ȼ�С�ڵ�ǰ���������ǰ��������ջ����ȡ��һ���ַ�.
                    case '<':
                        optr.push(s[i++]);
                        break;
                    //��ǰ�����Ϊ')'��β���ڱ�'\0'������ջ�����������ȡ��һ���ַ�����ʱջ���������ӦΪ'('��'\0'.
                    case '=':
                        optr.pop(); ++i;
                        break;
                    //'>': ջ����������Խ�������.
                    //NOTICE�� case '>'ʱû��++i����Ϊs[i]��һֱ��ջ������������������ȼ��Ƚ�.
                    //���ջ������� '>' s[i]��˵�����Խ������㣺����ջ�����������Ҫ�Ĳ������������㣬Ȼ���ٽ�s[i]��ջ��������Ƚ�...
                    //ֱ��ջ������� '<=' s[i]ʱ���Ż��ǵ�ǰ�����s[i]��ջoptr���ж���һ���ַ�(++i).
                    case '>':
                        char op = optr.pop(); 
                        if ('!' == op) {
                            float pOpnd = opnd.pop();
                            opnd.push(calcu(op, pOpnd));
                        } else {
                            float pOpnd2 = opnd.pop(), pOpnd1 = opnd.pop();
                            opnd.push(calcu(pOpnd1, op, pOpnd2));
                        }
                        break;
                    default:
                        
                }
        }
        return opnd.pop();
    }
    
    //��ֻ��Ҫһ���������������'!'�������㲢���ؽ��
    public static float calcu(char c, float opnd) throws Exception {
        
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
        System.out.println("" + opnd + c + " = " + ret);
        return ret;
    }
    
    //����Ҫ������������������������㲢���ؽ��
    public static float calcu(float opnd1, char optr, float opnd2) throws Exception {
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
        System.out.println("" + opnd1 + optr + opnd2 + " = " + ret); 
        return ret;
    }
    
    public static void main(String[] args) throws Exception {
        String s = "(0!+1)*2^(3!+4)-(5!-67-(8+9))";
        System.out.println(evaluate(s));
    }
}
