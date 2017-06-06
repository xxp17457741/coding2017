package com.coderising.jvm.cmd;

import java.util.HashMap;
import java.util.Map;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.StackFrame;


public abstract class ByteCodeCommand {	
	
	String opCode;
	ClassFile clzFile;	
	private int offset;
	
	/**
	 * 将 null 推送至栈顶
	 */
	public static final String aconst_null = "01";
	/**
	 * 创建一个对象，并将其引用值压入栈顶
	 */
	public static final String new_object = "BB";
	/**
	 * 将栈顶 long 型数值存入指定局部变量
	 */
	public static final String lstore = "37";
	/**
	 * 调用超类构造方法，实例初始化方法，私有方法
	 */
	public static final String invokespecial = "B7";
	/**
	 * 调用实例方法
	 */
	public static final String invokevirtual = "B6";
	/**
	 * 获取指定类的实例域，并将其值压入栈顶
	 */
	public static final String getfield = "B4";
	/**
	 * 为指定的类的实例域赋值
	 */
	public static final String putfield = "B5";
	/**
	 * 获取指定类的静态域，并将其值压入栈顶
	 */
	public static final String getstatic = "B2";
	/**
	 * 将 int，float 或 String 型常量值从常量池中推送至栈顶
	 */
	public static final String ldc = "12";
	/**
	 * 复制栈顶数值并将复制值压入栈顶
	 */
	public static final String dup = "59";
	/**
	 * 将单字节的常量值（-128~127）推送至栈顶
	 */
	public static final String bipush = "10";
	/**
	 * 将第一个引用类型局部变量推送至栈顶
	 */
	public static final String aload_0 = "2A";
	/**
	 * 将第二个引用类型局部变量推送至栈顶
	 */
	public static final String aload_1 = "2B";
	/**
	 * 将第三个引用类型局部变量推送至栈顶
	 */
	public static final String aload_2 = "2C";
	/**
	 * 将指定的 int 型局部变量推送至栈顶
	 */
	public static final String iload = "15";
	/**
	 * 将第二个 int 型局部变量推送至栈顶
	 */
	public static final String iload_1 = "1B";
	/**
	 * 将第三个 int 型局部变量推送至栈顶
	 */
	public static final String iload_2 = "1C";
	/**
	 * 将第四个 int 型局部变量推送至栈顶
	 */
	public static final String iload_3 = "1D";
	/**
	 * 将第四个 float 型局部变量推送至栈顶
	 */
	public static final String fload_3 = "25";

	/**
	 * 从当前方法返回 void
	 */
	public static final String voidreturn = "B1";
	/**
	 * 从当前方法返回 int
	 */
	public static final String ireturn = "AC";
	/**
	 * 从当前方法返回 float
	 */
	public static final String freturn = "AE";

	/**
	 * 将栈顶引用型数值存入第二个局部变量
	 */
	public static final String astore_1 = "4C";
	/**
	 * 比较栈顶两 int 型数值大小，当结果大于等于 0 时跳转
	 */
	public static final String if_icmp_ge = "A2";
	/**
	 * 比较栈顶两 int 型数值大小，当结果小于等于 0 时跳转
	 */
	public static final String if_icmple = "A4";
	/**
	 * 无条件跳转
	 */
	public static final String goto_no_condition = "A7";
	/**
	 * 将 int 型 0 推送至栈顶
	 */
	public static final String iconst_0 = "03";
	/**
	 * 将 int 型 1 推送至栈顶
	 */
	public static final String iconst_1 = "04";
	/**
	 * 将栈顶 int 型数值存入第二个局部变量
	 */
	public static final String istore_1 = "3C";
	/**
	 * 将栈顶 int 型数值存入第三个局部变量
	 */
	public static final String istore_2 = "3D";
	/**
	 * 将栈顶两 int 型数值相加并将结果压入栈顶
	 */
	public static final String iadd = "60";
	/**
	 * 将指定 int 型变量增加指定值
	 */
	public static final String iinc = "84";
	
	private static Map<String,String> codeMap = new HashMap<String,String>();
	
	static{
		codeMap.put("01", "aconst_null");
		
		codeMap.put("BB", "new");
		codeMap.put("37", "lstore");
		codeMap.put("B7", "invokespecial");
		codeMap.put("B6", "invokevirtual");
		codeMap.put("B4", "getfield");
		codeMap.put("B5", "putfield");
		codeMap.put("B2", "getstatic");
		
		codeMap.put("2A", "aload_0");
		codeMap.put("2B", "aload_1");
		codeMap.put("2C", "aload_2");
		
		codeMap.put("10", "bipush");
		codeMap.put("15", "iload");
		codeMap.put("1A", "iload_0");
		codeMap.put("1B", "iload_1");
		codeMap.put("1C", "iload_2");
		codeMap.put("1D", "iload_3");
		
		codeMap.put("25", "fload_3");
		
		codeMap.put("1E", "lload_0");
		
		codeMap.put("24", "fload_2");
		codeMap.put("4C", "astore_1");
		
		codeMap.put("A2", "if_icmp_ge");
		codeMap.put("A4", "if_icmple");
		
		codeMap.put("A7", "goto");
		
		codeMap.put("B1", "return");
		codeMap.put("AC", "ireturn");
		codeMap.put("AE", "freturn");
		
		codeMap.put("03", "iconst_0");
		codeMap.put("04", "iconst_1");
		
		codeMap.put("3C", "istore_1");
		codeMap.put("3D", "istore_2");
		
		codeMap.put("59", "dup");
		
		codeMap.put("60", "iadd");
		codeMap.put("84", "iinc");
		
		codeMap.put("12", "ldc");
	}
	
	

	

	protected ByteCodeCommand(ClassFile clzFile, String opCode){
		this.clzFile = clzFile;
		this.opCode = opCode;
	}
	
	protected ClassFile getClassFile() {
		return clzFile;
	}
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	protected ConstantInfo getConstantInfo(int index){
		return this.getClassFile().getConstantPool().getConstantInfo(index);
	}
	
	protected ConstantPool getConstantPool(){
		return this.getClassFile().getConstantPool();
	}
	
	
	
	public String getOpCode() {
		return opCode;
	}

	public abstract int getLength();
	
	
	
	
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.opCode);
		
		return buffer.toString();
	}
	public abstract String toString(ConstantPool pool);
	
	public String getReadableCodeText(){
		String txt = codeMap.get(opCode);
		if(txt == null){
			return opCode;
		}
		return txt;
	}
	
	public abstract void execute(StackFrame frame,ExecutionResult result);
}
