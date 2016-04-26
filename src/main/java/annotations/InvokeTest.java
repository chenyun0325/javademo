package annotations;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;

public class InvokeTest {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		Type[] types=getMethodParamType(InvokeTest.class.newInstance(), "test");
		for (int i = 0; i < types.length; i++) {
			System.err.println(types[i]);
		}
		/*Class[] classes=getMethodParamType1(InvokeTest.class.newInstance(), "test");
		for (int i = 0; i < classes.length; i++) {
			System.err.println(classes[i]);
		}*/

	}
	public static Type[] getMethodParamType(Object classInstance,String methodName){
		Method[] methods=classInstance.getClass().getMethods();
		Method[] methods2=classInstance.getClass().getDeclaredMethods();
		System.err.println(methods.length);
		System.err.println(methods2.length);
		Type[] types=null;
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
			if (methodName.equals(methods[i].getName())) {
			types=methods[i].getGenericParameterTypes();
			break;//终止,没有考虑同名方法
			}			
		}
		
		return types;
	}
	
	public static Class[] getMethodParamType1(Object classInstance,String methodName){
	Class[] paramstypes=null;
	Method[] methods=classInstance.getClass().getMethods();
	for (int i = 0; i < methods.length; i++) {
		if (methodName.equals(methods[i].getName())) {
			paramstypes=methods[i].getParameterTypes();
			break;
		}
	}
		return paramstypes;
	} 
	
	public static String test(String arg1,int arg2,Map arg3){
		return "xxxxxxxxxxx";
	}

}
