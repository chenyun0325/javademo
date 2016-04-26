/**
 * 文件名：BusinessImp.java
 *
 * 版本信息：
 * 日期：2014-6-25
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.rmi.RemoteException;

/**
 * 
 * 项目名称：portal 02
 * 类名称：BusinessImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-25 下午09:47:10
 * 修改人：chenyun
 * 修改时间：2014-6-25 下午09:47:10
 * 修改备注：
 * @version 
 * 
 */
public class BusinessImp implements Business {

	/* (非Javadoc)
	 * <p>Title: echo</p>
	 * <p>Description:</p>
	 * @param message
	 * @return 
	 * @see distrservice.Business#echo(java.lang.String)
	 */
	@Override
	public String echo(String message) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("message from client:"+message);
		return message;
	}

}
