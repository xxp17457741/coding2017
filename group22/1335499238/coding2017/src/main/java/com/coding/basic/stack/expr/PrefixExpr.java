package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> parse = new TokenPaser().parse(expr);
		Stack<Float> numStack = new Stack<>();
		Stack<Token> exprStack = new Stack<>();
		for (Token token : parse) {
			exprStack.push(token);
		}
		while (!exprStack.isEmpty()) {
			Token token = exprStack.pop();
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
			if(token.isOperator()){
				Float num1 = numStack.pop();
				Float num2 = numStack.pop();
				Float calculate = TokenCalculate.calculate(token.toString(), num1, num2);
				numStack.push(calculate);
			}
		}
		return numStack.pop().floatValue();
	}
	
	
}
