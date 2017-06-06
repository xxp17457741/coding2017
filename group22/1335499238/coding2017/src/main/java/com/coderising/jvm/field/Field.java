package com.coderising.jvm.field;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.ConstantValue;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;


public class Field {
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	
	
	private ConstantPool pool;
	private ConstantValue constValue;
	
	public Field( int accessFlag, int nameIndex, int descriptorIndex,ConstantPool pool) {
		
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
		this.pool = pool;
	}

	
	
	
	public static Field parse(ConstantPool pool,ByteCodeIterator iter){
		Field field = new Field(iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt(), pool);
		int fieldAttributesCount = iter.nextU2ToInt();
		for (int i = 0; i < fieldAttributesCount; i++) {
			int nameIndex = iter.nextU2ToInt();
			String constValueName = pool.getUTF8String(nameIndex);
			if(AttributeInfo.CONST_VALUE.equalsIgnoreCase(constValueName)){
				ConstantValue constantValue = new ConstantValue(nameIndex, iter.nextU4ToInt());
				constantValue.setConstValueIndex(iter.nextU2ToInt());
				field.setConstantValue(constantValue);
			}else{
				throw new RuntimeException("the attribute " + constValueName + " has not been implemented yet.");
			}
		}
		return field;
	}

	@Override
	public String toString() {
		return ((UTF8Info)pool.getConstantInfo(nameIndex)).getValue() + ":" + ((UTF8Info)pool.getConstantInfo(descriptorIndex)).getValue();
	}
	
	public void setConstantValue(ConstantValue constValue) {
		this.constValue = constValue;
	}

}
