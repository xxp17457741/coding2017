package com.github.xiaozi123.coding2017.secondWork;

import java.util.Map;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;



public class Struts {
	
	

    public static View runAction(String actionName, Map<String,String> parameters) throws DocumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

    	  /*
        
    			0. ��ȡ�����ļ�struts.xml
    	 		
    	 		1. ����actionName�ҵ����Ӧ��class �� ����LoginAction,   ͨ������ʵ��������������
    			��parameters�е����ݣ����ö����setter������ ����parameters�е������� 
    			("name"="test" ,  "password"="1234") ,     	
    			�Ǿ�Ӧ�õ��� setName��setPassword����
    			
    			2. ͨ��������ö����exectue ������ ����÷���ֵ������"success"
    			
    			3. ͨ�������ҵ����������getter���������� getMessage��,  
    			ͨ�����������ã� ��ֵ�������γ�һ��HashMap , ���� {"message":  "��¼�ɹ�"} ,  
    			�ŵ�View�����parameters
    			
    			4. ����struts.xml�е� <result> ����,�Լ�execute�ķ���ֵ��  ȷ����һ��jsp��  
    			�ŵ�View�����jsp�ֶ��С�
    	        
    	        */
    			//��ȡxml�ļ�
    	
//    			String path="src/com/github/xiaozi123.coding2017.secondWork/struct.xml";
    			
    			
    			
    			return initView(actionName, parameters);
    }    
    
    public static View initView(String actionName, Map<String,String> parameters) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    			View view=new View();
    			int i=0;	
    			String[] methodNames=new String[parameters.size()];
    			
    			for (String string : parameters.keySet()) {
					methodNames[i++]="set"
							+string.substring(0, 1).toUpperCase()+string.substring(1);
				}
    			
    			Struts.class.getResourceAsStream("/structs.xml");
    			Element element=getTargetElement(actionName);
    			
    			//ͨ������ʵ����
    			String className=element.attribute(1).getValue();
    			Class clazz=Class.forName(className);
    			
    			Object object=clazz.newInstance();
    			
    			invokeObjectSetter(parameters, methodNames, clazz, object);
    			
    			view.setParameters(createGetterMap(clazz, object));
    			setViewJsp(view, element, clazz, object);
    			return view;
		
	}
    

	private static void setViewJsp(View view, Element element, Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		view.setJsp(getJsp(element, executeToGetResult(clz, obj)));
	}

	
	private static Map createGetterMap(Class clz, Object obj) throws IllegalAccessException, InvocationTargetException {
		Map map = new HashMap();
		Method[] methods = clz.getMethods();
		for (Method item : methods) {
			if (item.getName().contains("get")) {
				String key = item.getName().substring(3).toLowerCase();
				Object value = item.invoke(obj);
				map.put(key, value);
			}
		}
		return map;
	}

	private static String executeToGetResult(Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		// �����ȡ���� ����ִ��
		Method method = clz.getMethod("execute");
		String result = (String) method.invoke(obj);
		return result;
	}

	private static void invokeObjectSetter(Map<String, String> parameters, 
			String[] methodNames, Class clz, Object obj)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (String key : methodNames) {
			Method method = clz.getMethod(key, String.class);
			method.invoke(obj, parameters.get(key));
		}
	}

	private static Element getTargetElement(String actionName) throws DocumentException {
		SAXReader reader = new SAXReader();
		InputStream inputStream =Struts.class.getResourceAsStream("/struts.xml");
		Document document = reader.read(inputStream);
		Element rootNode = document.getRootElement();
		List<Element> elements = rootNode.elements();
		for (Element item : elements) {
			if (actionName.equals(item.attribute(0).getValue())) {
				return item;
			}
		}
		return null;
	}

	private static String getJsp(Element element, String result) {
		List<Element> elements = element.elements();
		for (Element e : elements) {
			if (result.equals(e.attribute(0).getValue())) {
				return e.getTextTrim();
			}
		}
		return null;
	}

}
