/**
 * 文件名：RmiClient.java
 *
 * 版本信息：
 * 日期：2014-6-25
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * 项目名称：portal 02
 * 类名称：RmiClient
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-25 下午10:03:37
 * 修改人：chenyun
 * 修改时间：2014-6-25 下午10:03:37
 * 修改备注：
 * @version 
 * 
 */
public class RmiClient {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Business business=(Business) Naming.lookup("demo");
			business.echo("xxxxxxxxxxxxxx");
			Registry registry=LocateRegistry.getRegistry("localhost");
			Business business2=(Business) registry.lookup("demo");
			business2.echo("kkkkkkkkkkkkk");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
