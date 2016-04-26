/**
 * 文件名：AnnotationsTest.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 
 * 项目名称：portal 02
 * 类名称：AnnotationsTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午01:17:43
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午01:17:43
 * 修改备注：
 * @version 
 * 
 */
public class AnnotationsTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException 
	
	
	 */
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		// TODO Auto-generated method stub
    AnnotationsTest aTest=new AnnotationsTest();
  
    //获取AnnotationsTest的class实例
    //Class<AnnotationsTest> c=AnnotationsTest.class;
    Class<AnnotationsTest> c=(Class<AnnotationsTest>) aTest.getClass();
    Method[] methods=c.getMethods();
    System.out.println(methods.length);
    for (int i = 0; i < methods.length; i++) {
		Method method=methods[i];
		
		System.out.println(method.getName());
	//判断方法是否含有@Delegate注释
		if (method.isAnnotationPresent(Delegate.class)) {
	//获取该方法的@Delegate注释实例
		Delegate dl=method.getAnnotation(Delegate.class);
	//执行注释方法
		method.invoke(aTest, new Object[]{});
	//获取@Delegate注释值
		System.out.println(dl.interfacename());
	//加载接口类实例---------------------------------------------------关键
	//	Class class1=Class.forName(dl.interfacename());
	//后续处理----------------------------------判断
	//	class1.isAssignableFrom(c);
		method.getTypeParameters();//参数
		method.getReturnType();//返回值
	//--------------------------------------end
		System.out.println(dl.methodname());
		System.out.println(dl.policy());
		}
	}
	}
	@Delegate(interfacename="com.fourinone.ParkLocal",methodname="excute",policy=Policy.implement)
	public void excute(){
	System.out.println("annotation method excute");
	}
	@Delegate(interfacename="com.fourinone.ParkLocal",methodname="excute1",policy=Policy.implement)
	public void excute1(){
	System.out.println("annotation method excute1");
	}
	public void noexcute(){
	System.out.println("not annotation method");
	}

}
