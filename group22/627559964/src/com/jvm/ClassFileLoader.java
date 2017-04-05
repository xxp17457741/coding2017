package com.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className){
		File file = null;
		for (String string : clzPaths) {
			file = new File(string + className.replace(".", "/") + ".class");
			//TODO 已找到就退出,是不是有问题？
			if (file.exists()){
				break;
			}
		}
		if (file == null) {
			new FileNotFoundException("classpath中未找到该文件");
		}
		byte[] bt = new byte[(int) file.length()];
		byte[] buf = new byte[1024];
		
		try {
			InputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int n;
			while ((n=in.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			bt = out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bt;
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		StringBuilder paths = new StringBuilder();
		for (int i = 0; i < clzPaths.size(); i++) {
			paths.append(clzPaths.get(i));
			if(i != clzPaths.size() - 1) {
				paths.append(";");
			}
		}
		return paths.toString();
	}

}
