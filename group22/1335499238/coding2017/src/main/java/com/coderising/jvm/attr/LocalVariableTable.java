package com.coderising.jvm.attr;


import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.constant.ConstantPool;

import com.coderising.jvm.loader.ByteCodeIterator;

public class LocalVariableTable extends AttributeInfo{

	List<LocalVariableItem> items = new ArrayList<LocalVariableItem>();
	
	public LocalVariableTable(int attrNameIndex, int attrLen) {
		super(attrNameIndex, attrLen);		
	}
	
	public static LocalVariableTable parse(ByteCodeIterator iter){
		LocalVariableTable localVariableTable = new LocalVariableTable(iter.nextU2ToInt(), iter.nextU4ToInt());
		int localVarInfoLen = iter.nextU2ToInt();
		for (int i = 0; i < localVarInfoLen; i++) {
			LocalVariableItem localVariableItem = new LocalVariableItem();
			localVariableItem.setStartPC(iter.nextU2ToInt());
			localVariableItem.setLength(iter.nextU2ToInt());
			localVariableItem.setNameIndex(iter.nextU2ToInt());
			localVariableItem.setDescIndex(iter.nextU2ToInt());
			localVariableItem.setIndex(iter.nextU2ToInt());
			localVariableTable.addLocalVariableItem(localVariableItem);
		}
		
		return null;
	}
	private void addLocalVariableItem(LocalVariableItem item) {
		this.items.add(item);		
	}
	
	
}
