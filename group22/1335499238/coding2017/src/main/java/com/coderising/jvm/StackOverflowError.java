package com.coderising.jvm;

public class StackOverflowError {
	public static void main(String[] args) {
		recursionStackOverFlow();
	}
	
	private static void recursionStackOverFlow(){
		while (true) {
			recursionStackOverFlow();
		}
	}
}
