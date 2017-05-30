package com.coderising.jvm.loader;

import com.coderising.jvm.util.Util;

public class ByteCodeIterator {
	
	byte[] code;
	
	int index;
	
	public ByteCodeIterator(byte[] code){
		this.code = code;
	}
	
	public int nextU1ToInt(){
		return Util.byteToInt(new byte[]{code[index++]});
	}

	public int nextU2ToInt(){
		return Util.byteToInt(new byte[]{code[index++],code[index++]});
	}
	
	public int nextU4ToInt(){
		return Util.byteToInt(new byte[]{code[index++],code[index++],code[index++],code[index++]});
	}
	
	public String nextU4ToHexString(){
		return Util.byteToHexString(new byte[]{code[index++],code[index++],code[index++],code[index++]});
	}
	
	public String nextUxToHexString(int len){
		byte[] temp = new byte[len];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = code[index++];
		}
		return Util.byteToHexString(temp).toLowerCase();
	}
	
	public byte[] getBytes(int len){
		byte[] bytes = new byte[len];
		System.arraycopy(code, index, bytes, 0, len);
		index += len;
		return bytes;
	}
	
	public void back(int n){
		index -= n;
	}
}
