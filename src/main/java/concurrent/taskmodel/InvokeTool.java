/**
 * 文件名：InstanceFactory.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package concurrent.taskmodel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 项目名称：portal 02
 * 类名称：InstanceFactory
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午05:07:55
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午05:07:55
 * 修改备注：
 * @version 
 * 
 */
public class InvokeTool {
	
	/**
	 * 
	 * @param clzname//类和方法名称
	 * @param args//方法执行参数
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static Object invokeMethod(String clzname,Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{

	/**
	 * 1.分成类及方法
	 */
	int lastindex=clzname.lastIndexOf(".");
	String methodname=clzname.substring(lastindex+1);
	String classname=clzname.substring(0, lastindex);
	Class cls=null;
	Object obj=null;
	try {
		cls=Class.forName(classname);
		if (cls!=null) {
		obj=cls.newInstance();//被代理对象
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	/**
	 * 2.获取匹配方法
	 */
	Method[] methods=cls.getDeclaredMethods();
	for (int i = 0; i < methods.length; i++) {
		if (methods[i].getName().equals(methodname)) {
		obj=methods[i].invoke(obj, args);
		break;//跳出循环,需要添加方法重载的实现逻辑
		}
	}
	return obj;
	
	}

}
