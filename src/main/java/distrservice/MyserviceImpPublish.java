/**
 * 文件名：MyserviceImpPublish.java
 *
 * 版本信息：
 * 日期：Jul 7, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import javax.xml.ws.Endpoint;



/**
 * 
 * 项目名称：portal 02
 * 类名称：MyserviceImpPublish
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 7, 2014 3:30:55 PM
 * 修改人：chenyun
 * 修改时间：Jul 7, 2014 3:30:55 PM
 * 修改备注：
 * @version 
 * 
 */
public class MyserviceImpPublish {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String address="http://localhost:8081/IMyservice";
		String address2="http://localhost:8081/IDiagnosisInfoService";
		//Endpoint.publish(address, new MyserviceImp());
		Endpoint.publish(address2, new DiagnosisInfoServiceImp());

	}

}
