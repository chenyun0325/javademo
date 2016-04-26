/**
 * 文件名：Memorytest.java
 *
 * 版本信息：
 * 日期：2014-6-17
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package jvm;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Memorytest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-17 下午01:31:54
 * 修改人：chenyun
 * 修改时间：2014-6-17 下午01:31:54
 * 修改备注：
 * @version 
 * 
 */
public class Memorytest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Runtime.getRuntime().maxMemory()/1024/1024+"M");
		System.out.println(Runtime.getRuntime().freeMemory()/1024/1024+"M");
		System.out.println(Runtime.getRuntime().totalMemory()/1024/1024+"M");
		System.out.println(Runtime.getRuntime().availableProcessors());
		

	}

}
