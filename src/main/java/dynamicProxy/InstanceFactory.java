/**
 * 文件名：InstanceFactory.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package dynamicProxy;

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
public class InstanceFactory {
	/*private static Object getclassinstance(String clsname){
		Object obj = null;
		try {
			Class cls=Class.forName(clsname);
			obj=cls.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}*/
	public static Object getAOPProxyedObject(String clzname){
	Object proxy	=null;//接口返回
	Class cls=null;
	Object obj=null;
	try {
		cls=Class.forName(clzname);
		if (cls!=null) {
		obj=cls.newInstance();//被代理对象
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	//被代理对象与代理对象绑定，静态与实例
	//静态
	/*if (obj!=null) {
		proxy=DynamicProxyStatic.bind(cls.getInterfaces(), obj);//bind第一参数一定要是接口cls.getInterfaces,而不是cls
	}else {
		System.out.println("error");
	}*/
	//实例---新建动态代理实例
	DynamicProxyInstance instance=new DynamicProxyInstance();
	proxy=instance.bind(cls.getInterfaces(), obj);
	return proxy;
	
	}

}
