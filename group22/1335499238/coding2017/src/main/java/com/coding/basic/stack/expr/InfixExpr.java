package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;


public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	
	public float evaluate() {		
		
		Stack<Float> stackOfNum  = new Stack<>();
		Stack<Token> stackOfOper = new Stack<Token>();
		TokenPaser tokenPaser = new TokenPaser();
		List<Token> paser = tokenPaser.parse(expr);
		for (Token token : paser) {
			if(token.isNumber()){
				stackOfNum.push(new Float(token.getIntValue()));
			}
			if(token.isOperator()){
				while(!stackOfOper.isEmpty() && !token.hasHigherPriority(stackOfOper.peek())){
					Token prevOperator = (Token)stackOfOper.pop();
					Float f2 = stackOfNum.pop();
					Float f1 = stackOfNum.pop();
					Float result = TokenCalculate.calculate(prevOperator.toString(), f1,f2);
					stackOfNum.push(result);						
				}
				stackOfOper.push(token);
			}
		}
		while(!stackOfOper.isEmpty()){
			Token token = stackOfOper.pop();
			Float f2 = stackOfNum.pop();
			Float f1 = stackOfNum.pop();
			stackOfNum.push(TokenCalculate.calculate(token.toString(), f1,f2));
		}
		return stackOfNum.peek().floatValue();
	}
}
