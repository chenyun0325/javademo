/**
 * 文件名：DiagnosisInfoServiceImpRimServer.java
 *
 * 版本信息：
 * 日期：Jul 4, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DiagnosisInfoServiceImpRimServer
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 4, 2014 11:29:05 PM
 * 修改人：chenyun
 * 修改时间：Jul 4, 2014 11:29:05 PM
 * 修改备注：
 * @version 
 * 
 */
public class DiagnosisInfoServiceImpRimServer {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String IDiagnosisInfoName="rmi://localhost:1099/Diagnosis";
		final int port=9527;
		try {
			System.setProperty("java.rmi.server.hostname", "localhost");
			IDiagnosisInfoService diagnosisInfoRimServer=new DiagnosisInfoServiceImp();
			//导出对象
			UnicastRemoteObject.exportObject(diagnosisInfoRimServer, port);
			//获取本地RMI注册表对象
			Registry registry=LocateRegistry.getRegistry();
			/*Registry registry=LocateRegistry.createRegistry(1099);*/
			
			registry.rebind(IDiagnosisInfoName, diagnosisInfoRimServer);
			System.out.println("server is ok");
			
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
