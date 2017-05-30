package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;



public class ClassFileLoader {

private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		for(String path : this.clzPaths){
			String filePath = new StringBuilder().append(path).append("\\").append(className.replace(".", File.separator)).append(".class").toString();
			byte[] loadFile = loadFile(filePath);
			if(loadFile != null){
				return loadFile;
			}
		}
		return null;
	}
	
	private byte[] loadFile(String path){
		File file = new File(path);
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			if(!file.exists()){
				return null;
			}
			byte[] buffer = new byte[1024];
			InputStream is = new FileInputStream(file);
			int length = -1;
			while ((length = is.read(buffer)) != -1) {
				bs.write(buffer, 0, length);
			}
			is.close();
			bs.close();
			return bs.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}
}
