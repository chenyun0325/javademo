/**
 * 文件名：MywebserviceClientGenerate.java
 *
 * 版本信息：
 * 日期：Jul 7, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice.client;



/**
 * 
 * 项目名称：portal 02
 * 类名称：MywebserviceClientGenerate
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 7, 2014 4:41:12 PM
 * 修改人：chenyun
 * 修改时间：Jul 7, 2014 4:41:12 PM
 * 修改备注：
 * @version 
 * 
 */
public class MywebserviceClientGenerate {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyserviceImpService service=new MyserviceImpService();
		IMyservice myservice=service.getIMysPort();
		System.out.println(myservice.add(1, 4));

	}

}
