package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> parse = new TokenPaser().parse(expr);
		Stack<Float> numStack = new Stack<>();
		for (Token token : parse) {
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
			if(token.isOperator()){
				Float num2 = numStack.pop();
				Float num1 = numStack.pop();
				Float result = TokenCalculate.calculate(token.toString(), num1, num2);
				numStack.push(result);
			}
		}
		return numStack.pop().floatValue();
	}
	
	
}
