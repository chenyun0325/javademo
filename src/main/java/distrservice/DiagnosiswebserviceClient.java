/**
 * 文件名：MywebserviceClient.java
 *
 * 版本信息：
 * 日期：Jul 7, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * 
 * 项目名称：portal 02
 * 类名称：MywebserviceClient
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 7, 2014 4:02:59 PM
 * 修改人：chenyun
 * 修改时间：Jul 7, 2014 4:02:59 PM
 * 修改备注：
 * @version 
 * 
 */
public class DiagnosiswebserviceClient {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//服务wsdl 地址
			URL url=new URL("http://localhost:8081/IMyservice?wsdl");
			//服务的namespace
			QName sQName=new QName("http://localhost/client", "MyserviceImpService");
			//创建服务
			Service service=Service.create(url, sQName);
			IMyservice ms=service.getPort(IMyservice.class);
			System.out.println(ms.add(1, 4));
			System.out.println(ms.minus(1, 4));
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
