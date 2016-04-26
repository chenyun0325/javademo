package concurrent.taskmodel;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InvokTest {

	/**
	 * @param args
	 */
	public static void main(String[] arg) {
		// TODO Auto-generated method stub
	 Object[] args;//方法运行需要的参数
	 ArrayList array=new ArrayList();
	 Map map=new HashMap();
	 map.put("name", "cy");
	 array.add(0, 1);
	 array.add(1, "3");
	 array.add(2, map);
	 args=array.toArray();
	try {
		Object result=InvokeTool.invokeMethod("taskmodel.InvokTest.test", args);
		System.err.println(result);
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 

	}

	public int sum(int i,int j){
		System.err.println(i);
		System.err.println(j);		
		return i+j;
	}
    public void test(int i,String j,Map map){
    	System.err.println(i);
    	System.err.println(j);
    	System.err.println(map.get("name"));
    }
}
