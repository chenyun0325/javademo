/**
 * 文件名：Business.java
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
 * 类名称：Business
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-25 下午09:38:21
 * 修改人：chenyun
 * 修改时间：2014-6-25 下午09:38:21
 * 修改备注：
 * @version 
 * 
 */
public interface Business extends Flag {
	public String echo(String message) throws RemoteException;

}
