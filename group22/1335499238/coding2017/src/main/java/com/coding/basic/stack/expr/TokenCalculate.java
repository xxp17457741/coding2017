package com.coding.basic.stack.expr;

public class TokenCalculate {
	public static Float calculate(String operator, Float f1, Float f2) {
		if("+".equals(operator)){
			return f1+f2;
		}else if ("-".equals(operator)) {
			return f1-f2;
		}else if ("*".equals(operator)) {
			return f1*f2;
		}else if ("/".equals(operator)) {
			return f1/f2;
		}else{
			throw new RuntimeException(operator + " is not supported");
		}
	}

}
