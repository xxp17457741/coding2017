package com.coderising.jvm.loader;

import com.coderising.jvm.clz.AccessFlag;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.clz.ClassIndex;
import com.coderising.jvm.constant.ClassInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.FieldRefInfo;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.constant.NameAndTypeInfo;
import com.coderising.jvm.constant.NullConstantInfo;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.constant.UTF8Info;
import com.coderising.jvm.field.Field;
import com.coderising.jvm.method.Method;

public class ClassFileParser {

	public ClassFile parse(byte[] codes) {
		ByteCodeIterator iter = new ByteCodeIterator(codes);
		ClassFile classFile = new ClassFile();
		if(!"cafebabe".equals(iter.nextUxToHexString(4))){
			return null;
		}
		classFile.setMinorVersion(iter.nextU2ToInt());
		classFile.setMajorVersion(iter.nextU2ToInt());
		classFile.setConstPool(parseConstantPool(iter));
		classFile.setAccessFlag(parseAccessFlag(iter));
		classFile.setClassIndex(parseClassInfex(iter));
		parseInterfaces(iter);
		parseField(iter, classFile, classFile.getConstantPool());
		parseMethod(iter, classFile);
		return classFile;
	}

	private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
		return new AccessFlag(iter.nextU2ToInt());
	}

	private ClassIndex parseClassInfex(ByteCodeIterator iter) {
		ClassIndex classIndex = new ClassIndex();
		classIndex.setThisClassIndex(iter.nextU2ToInt());
		classIndex.setSuperClassIndex(iter.nextU2ToInt());
		return classIndex;

	}

	private ConstantPool parseConstantPool(ByteCodeIterator iter) {
		int constPoolCount = iter.nextU2ToInt();
		ConstantPool constantPool = new ConstantPool();
		constantPool.addConstantInfo(new NullConstantInfo());
		for (int i = 1; i < constPoolCount; i++) {
			int tag = iter.nextU1ToInt();
			switch (tag) {
			case 7:
				ClassInfo classInfo = new ClassInfo(constantPool);
				classInfo.setUtf8Index(iter.nextU2ToInt());
				constantPool.addConstantInfo(classInfo);
				break;
			case 1:
				UTF8Info utf8Info = new UTF8Info(constantPool);
				int len = iter.nextU2ToInt();
				utf8Info.setLength(len);
				utf8Info.setValue(new String(iter.getBytes(len)));
				constantPool.addConstantInfo(utf8Info);
				break;
			case 8:
				StringInfo stringInfo = new StringInfo(constantPool);
				stringInfo.setIndex(iter.nextU2ToInt());
				constantPool.addConstantInfo(stringInfo);
				break;
			case 9:
				FieldRefInfo fieldRefInfo = new FieldRefInfo(constantPool);
				fieldRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				fieldRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				constantPool.addConstantInfo(fieldRefInfo);
				break;
			case 10:
				MethodRefInfo methodRefInfo = new MethodRefInfo(constantPool);
				methodRefInfo.setClassInfoIndex(iter.nextU2ToInt());
				methodRefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
				constantPool.addConstantInfo(methodRefInfo);
				break;
			case 12:
				NameAndTypeInfo nameAndTypeInfo = new NameAndTypeInfo(constantPool);
				nameAndTypeInfo.setIndex1(iter.nextU2ToInt());
				nameAndTypeInfo.setIndex2(iter.nextU2ToInt());
				constantPool.addConstantInfo(nameAndTypeInfo);
				break;
			default:
				throw new RuntimeException("常量池还未实现" + tag + " 类型");
			}
		}
		return constantPool;
	}
	private void parseInterfaces(ByteCodeIterator iter) {
		int interfaceCount = iter.nextU2ToInt();

		if(interfaceCount > 0){
			System.out.println("interfaceCount:" + interfaceCount);
		}

		// TODO : 如果实现了interface, 这里需要解析
	}
	
	private void parseField(ByteCodeIterator iter, ClassFile classFile, ConstantPool constantPool){
		int fieldCount = iter.nextU2ToInt();
		if(fieldCount == 0){
			return;
		}
		for (int i = 0; i < fieldCount; i++) {
			Field field = Field.parse(constantPool, iter);
			classFile.addField(field);
		}
	}
	
	private void parseMethod(ByteCodeIterator iter, ClassFile classFile){
		int methodCount = iter.nextU2ToInt();
		if(methodCount == 0){
			return;
		}
		for (int i = 0; i < methodCount; i++) {
			Method method = Method.parse(classFile, iter);
			classFile.addMethod(method);
		}
	}
}
