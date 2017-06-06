package com.coderising.jvm.cmd;

import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.constant.ConstantInfo;
import com.coderising.jvm.constant.ConstantPool;
import com.coderising.jvm.constant.StringInfo;
import com.coderising.jvm.engine.ExecutionResult;
import com.coderising.jvm.engine.Heap;
import com.coderising.jvm.engine.JavaObject;
import com.coderising.jvm.engine.StackFrame;

/**
 * 将 int，float 或 String 型常量值从常量池中推送至栈顶
 *
 */
public class LdcCmd extends OneOperandCmd {

	public LdcCmd(ClassFile clzFile,String opCode) {
		super(clzFile,opCode);		
	}
	
	@Override
	public String toString(ConstantPool pool) {
		
		ConstantInfo info = (ConstantInfo)pool.getConstantInfo(this.getOperand());
		
		String value = "TBD";
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			value = strInfo.toString();
		}
		
		return this.getOffset()+":"+this.getOpCode()+" " + this.getReadableCodeText() + " "+  value;
		
	}

	@Override
	public void execute(StackFrame frame, ExecutionResult result) {
		ConstantInfo info = (ConstantInfo)this.clzFile.getConstantPool().getConstantInfo(this.getOperand());
		if(info instanceof StringInfo){
			StringInfo strInfo = (StringInfo)info;
			String value = strInfo.toString();
			JavaObject jo = Heap.getInstance().newString(value);
			frame.getOprandStack().push(jo);
		} else{
			throw new RuntimeException("Only support StringInfo constant");
		}
		
	}
	
}
