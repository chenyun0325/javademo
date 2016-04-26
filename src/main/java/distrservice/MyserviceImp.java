/**
 * 文件名：MyserviceImp.java
 *
 * 版本信息：
 * 日期：Jul 7, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import javax.jws.WebService;

/**
 * 
 * 项目名称：portal 02
 * 类名称：MyserviceImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 7, 2014 3:19:53 PM
 * 修改人：chenyun
 * 修改时间：Jul 7, 2014 3:19:53 PM
 * 修改备注：
 * @version 
 * 
 */
@WebService(endpointInterface="distrservice.IMyservice",serviceName="MyserviceImpService",targetNamespace="http://localhost/client",name="IMys")
public class MyserviceImp implements IMyservice {

	/* (非Javadoc)
	 * <p>Title: add</p>
	 * <p>Description:</p>
	 * @param a
	 * @param b
	 * @return 
	 * @see distrservice.IMyservice#add(int, int)
	 */
	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}

	/* (非Javadoc)
	 * <p>Title: minus</p>
	 * <p>Description:</p>
	 * @param a
	 * @param b
	 * @return 
	 * @see distrservice.IMyservice#minus(int, int)
	 */
	@Override
	public int minus(int a, int b) {
		// TODO Auto-generated method stub
		return a-b;
	}

}
