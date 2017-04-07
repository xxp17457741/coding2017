package com.coding.basic.stack;

public class StackUtil {

	/**
	 * 假设栈中的元素是Integer, 从栈顶到栈底是 : 5,4,3,2,1 调用该方法后， 元素次序变为: 1,2,3,4,5
	 * 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 */
	public static Stack reverse(Stack s) {
		Stack s2 = new Stack();
		while(!s.isEmpty()) {
			s2.push(s.pop());
		}
		return s2;
	}

	/**
	 * 删除栈中的某个元素 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty， 可以使用另外一个栈来辅助
	 * 
	 * @param o
	 */
	public static Stack remove(Stack s, Object o) {
		Stack s2 = new Stack();
		while(!s.isEmpty()) {
			if(!o.equals(s.peek())) {
				s2.push(s.pop());
			} else {
				s.pop();
				break;
			}
		}
		while(!s2.isEmpty()) {
			s.push(s2.pop());
		}
		return s;
	}

	/**
	 * 从栈顶取得len个元素, 原来的栈中元素保持不变 注意：只能使用Stack的基本操作，即push,pop,peek,isEmpty，
	 * 可以使用另外一个栈来辅助
	 * 
	 * @param len
	 * @return
	 */
	public static Object[] getTop(Stack s, int len) {
		Object[] obj = new Object[len];
		Stack s2 = new Stack();
		for (int i = 0; i < obj.length; i++) {
			obj[i] = s.peek();
			s2.push(s.pop());
		}
		while (!s2.isEmpty()) {
			s.push(s2.pop());
		}
		s2 = null;
		return obj;
	}

	/**
	 * 字符串s 可能包含这些字符： ( ) [ ] { }, a,b,c... x,y,z 使用堆栈检查字符串s中的括号是不是成对出现的。 例如s =
	 * "([e{d}f])" , 则该字符串中的括号是成对出现， 该方法返回true 如果 s = "([b{x]y})",
	 * 则该字符串中的括号不是成对出现的， 该方法返回false;
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidPairs(String s) {
		Stack left = new Stack();
		Stack right = new Stack();
		char[] arr = new char[s.length()];
		arr = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if ('('==(arr[i]) || '['==(arr[i]) || '{'==(arr[i])) {
				left.push(arr[i]);
			}
			if (')'==(arr[i]) || ']'==(arr[i]) || '}'==(arr[i])) {
				right.push(arr[i]);
			}
		}
		right = StackUtil.reverse(right);
		List<String> list = new ArrayList<String>();
		list.add("()");
		list.add("[]");
		list.add("{}");
		while (!left.isEmpty() && !right.isEmpty()) {
			String temp = left.peek().toString() + right.peek().toString();
			if (list.contains(temp)) {
				left.pop();
				right.pop();
			} else {
				return false;
			}
		}
		if (!left.isEmpty() || !right.isEmpty()) {
			return false;
		}
		return true;
	}

}
