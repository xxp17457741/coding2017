package com.coderising.jvm.loader;

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
	
	public byte[] readBinaryCode(String className) {
		String filePath = new StringBuilder().append(getClassPath()).append("\\").append(className.replace(".", "\\")).append(".class").toString();
		File file = new File(filePath);
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			if(!file.exists()){
				throw new FileNotFoundException();
			}
			byte[] buffer = new byte[1024];
			InputStream is = new FileInputStream(file);
			int length = -1;
			while ((length = is.read(buffer)) != -1) {
				bs.write(buffer, 0, length);
			}
			is.close();
			bs.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bs.toByteArray();	
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		StringBuilder stringBuilder = new StringBuilder();
		int size = clzPaths.size();
		for (int i = 0; i < size; i++) {
			stringBuilder.append(clzPaths.get(i));
			if(size - 1 > i){
				stringBuilder.append(";");
			}
		}
		return stringBuilder.toString();
	}
}
