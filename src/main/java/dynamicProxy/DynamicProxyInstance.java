/**
 * 文件名：DynamicProxyInstance.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DynamicProxyInstance
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午05:04:47
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午05:04:47
 * 修改备注：
 * @version 
 * 
 */
public class DynamicProxyInstance implements InvocationHandler {

Object[] obj;//被代理类数组
	
	public DynamicProxyInstance(Object[] obj){
		this.obj=obj;
	}
	
	/**
	 * 创建一个新的实例 DynamicProxyStatic.
	 *<p>Title:</p>
	 *<p>Description:</p>
	 */
	public DynamicProxyInstance() {
		// TODO Auto-generated constructor stub
	}
	public  Object bind(Class[] cls,Object... obj){
		return Proxy.newProxyInstance(cls[0].getClassLoader(), cls, new DynamicProxyStatic(obj));//obj 可以为this 或者new DynamicProxyStatic(obj)
		//return Proxy.newProxyInstance(cls[0].getClassLoader(), cls, new DynamicProxyStatic());//obj 可以为this 或者new DynamicProxyStatic(obj)

	}
	public  <I> I bind( Class<I> cls,Object...obj){
		return (I) bind(new Class[]{cls}, obj);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object result=null;//方法执行的返回值
		//放置被代理类变量
		Object obj1=null,obj2=null,obj3=null;
		Method objm1=null;
		
		try {
		//遍历被代理类
		for (int i = 0; i < obj.length; i++) {
			//获取被代理类所有方法
			Method[] methods=obj[i].getClass().getMethods();
			//根据methods做一些判断------------------------------if
			System.out.println("方法执行前");
			method.invoke(obj[i], args);//执行方法
			System.out.println("方法执行后");
		}
		//-------------------------------------------------------前置操作完毕
	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}


}
