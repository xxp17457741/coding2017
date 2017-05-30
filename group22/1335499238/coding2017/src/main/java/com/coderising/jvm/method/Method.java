package com.coderising.jvm.method;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.attr.AttributeInfo;
import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.loader.ByteCodeIterator;



public class Method {
	
	private int accessFlag;
	private int nameIndex;
	private int descriptorIndex;
	
	private CodeAttr codeAttr;
	
	private ClassFile clzFile;
	
	
	public ClassFile getClzFile() {
		return clzFile;
	}

	public int getNameIndex() {
		return nameIndex;
	}
	public int getDescriptorIndex() {
		return descriptorIndex;
	}
	
	public CodeAttr getCodeAttr() {
		return codeAttr;
	}

	public void setCodeAttr(CodeAttr code) {
		this.codeAttr = code;
	}

	public Method(ClassFile clzFile,int accessFlag, int nameIndex, int descriptorIndex) {
		this.clzFile = clzFile;
		this.accessFlag = accessFlag;
		this.nameIndex = nameIndex;
		this.descriptorIndex = descriptorIndex;
	}

	public static Method parse(ClassFile clzFile, ByteCodeIterator iter){
		
		Method method = new Method(clzFile, iter.nextU2ToInt(), iter.nextU2ToInt(), iter.nextU2ToInt());
		int methodAttributesCount = iter.nextU2ToInt();
		for (int i = 0; i < methodAttributesCount; i++) {
			int attributeNameIndex = iter.nextU2ToInt();
			String attributeName = clzFile.getConstantPool().getUTF8String(attributeNameIndex);
			iter.back(2);
			if(AttributeInfo.CODE.equalsIgnoreCase(attributeName)){
				CodeAttr parse = CodeAttr.parse(clzFile, iter);
				method.setCodeAttr(parse);
			}else{
				throw new RuntimeException("has method attribute_info unrealized, is" + attributeName);
			}
		}
		return method;
	}
	
	public ByteCodeCommand[] getCmds() {		
		return this.getCodeAttr().getCmds();
	}
	
	public List<String> getParameterList(){
		// e.g. (Ljava/util/List;Ljava/lang/String;II)V
		String paramAndType = getParamAndReturnType();
		int first = paramAndType.indexOf("(");
		int last = paramAndType.lastIndexOf(")");
		// e.g. Ljava/util/List;Ljava/lang/String;II
		String param = paramAndType.substring(first+1, last);
		List<String> paramList = new ArrayList<String>();
		if((null == param) || "".equals(param)){
			return paramList;
		}
		while (!param.equals("")) {
			int pos = 0;
			// 这是一个对象类型
			if(param.charAt(pos) == 'L'){
				int end = param.indexOf(";");
				if(end == -1){
					throw new RuntimeException("can't find the ; for a object type");
				}
				paramList.add(param.substring(pos+1,end));
				pos = end + 1; 				
			} else if(param.charAt(pos) == 'I'){
				// int
				paramList.add("I");
				pos++;
			} else if(param.charAt(pos) == 'F'){
				// float
				paramList.add("F");
				pos++;
			} else{
				throw new RuntimeException("the param has unsupported type:" + param);
			}
			param = param.substring(pos);
		}
		return paramList;
	}
	
	private String getParamAndReturnType(){
		UTF8Info  nameAndTypeInfo = (UTF8Info)this.getClzFile().getConstantPool().getConstantInfo(this.getDescriptorIndex());
		return nameAndTypeInfo.getValue();
	}
}
