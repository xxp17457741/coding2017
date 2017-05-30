package com.coderising.jvm;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemory {

	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		long i = 100;
		while(true){
			list.add(new String(i+""));
			i++;
		}
	}
}
