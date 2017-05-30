package com.coding.basic.tree;

import java.io.File;

public class FileList {
	public void list(File f) {
		if(!f.exists() || !f.isDirectory()){
			throw new RuntimeException("directory does not exist or the file is not a directory ");
		}
		printFile(f, 1);
	}

	private void printFile(File f, int hierarchy){
		File[] listFiles = f.listFiles();
		for (File file : listFiles) {
			if (file.isDirectory()) {
				printFile(file, hierarchy+1);
				System.out.println(hierarchy + " - directoryName   " + file.getName());
			}else{
				System.out.println( hierarchy + " - fileName   " + file.getName());
			}
		}
	}
	public static void main(String[] args) {
		FileList fileList = new FileList();
		fileList.list(new File("D:\\Git"));
	}
}
