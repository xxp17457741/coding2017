package com.coderising.jvm;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryPermGen {

	public static void main(String[] args) {

		int i = 0;
		List<String> l = new ArrayList<String>();
		while(true){
			l.add(String.valueOf(i++).intern());
		}

	}
}
