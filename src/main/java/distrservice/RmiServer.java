/**
 * 文件名：RmiServer.java
 *
 * 版本信息：
 * 日期：2014-6-25
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * 项目名称：portal 02
 * 类名称：RmiServer
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-25 下午09:53:56
 * 修改人：chenyun
 * 修改时间：2014-6-25 下午09:53:56
 * 修改备注：
 * @version 
 * 
 */
public class RmiServer {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port=9527;
		String name="demo";
		Business businterface=new BusinessImp();
		//对象绑定到端口
		try {
			UnicastRemoteObject.exportObject(businterface, port);
			//注册对象
			Registry registry=LocateRegistry.createRegistry(1099);
			registry.rebind(name, businterface);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
