package com.coderising.jvm.attr;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LineNumberTable extends AttributeInfo {
	List<LineNumberItem> items = new ArrayList<LineNumberItem>();
	
	private static class LineNumberItem{
		int startPC;
		int lineNum;
		public int getStartPC() {
			return startPC;
		}
		public void setStartPC(int startPC) {
			this.startPC = startPC;
		}
		public int getLineNum() {
			return lineNum;
		}
		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}
	}
	public void addLineNumberItem(LineNumberItem item){
		this.items.add(item);
	}
	public LineNumberTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);
		
	}
	
	public static LineNumberTable parse(ByteCodeIterator iter){
		LineNumberTable lineNumberTable = new LineNumberTable(iter.nextU2ToInt(),iter.nextU4ToInt());
		int lineNumberTalbeLen = iter.nextU2ToInt();
		if(lineNumberTalbeLen == 0){
			return lineNumberTable;
		}
		LineNumberItem lineNumberItem = null;
		for (int i = 0; i < lineNumberTalbeLen; i++) {
			lineNumberItem = new LineNumberItem();
			lineNumberItem.setStartPC(iter.nextU2ToInt());
			lineNumberItem.setLineNum(iter.nextU2ToInt());
			lineNumberTable.addLineNumberItem(lineNumberItem);
		}
		return lineNumberTable;
	}
	
	

}
