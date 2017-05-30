package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		List<Token> paser = new TokenPaser().parse(expr);
		List<Token> infixToPostFix = new ArrayList<>();
		Stack<Token> opStack = new Stack<Token>();
		for (Token token : paser) {
			if(token.isNumber()){
				infixToPostFix.add(token);
			}
			if(token.isOperator()){
				while (!opStack.isEmpty() && !token.hasHigherPriority(opStack.peek())){
					infixToPostFix.add(opStack.pop());
				}
				opStack.push(token);
			}
		}
		while (!opStack.isEmpty()) {
			infixToPostFix.add(opStack.pop());
		}
		return infixToPostFix;
	}
	
	

}
