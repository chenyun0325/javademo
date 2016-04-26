/**
 * 文件名：DiagnosisInfoServiceImpRimClient.java
 *
 * 版本信息：
 * 日期：Jul 4, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DiagnosisInfoServiceImpRimClient
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 4, 2014 11:46:57 PM
 * 修改人：chenyun
 * 修改时间：Jul 4, 2014 11:46:57 PM
 * 修改备注：
 * @version 
 * 
 */
public class DiagnosisInfoServiceImpRimClient {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//获取运行rmiregistry服务的主机上的注册表
			Registry registry=LocateRegistry.getRegistry("localhost");
			//查询并获取远程对象的存根
			IDiagnosisInfoService stub=(IDiagnosisInfoService) registry.lookup("rmi://localhost:1099/Diagnosis");
		    Diagnosis diagnosis=new Diagnosis();
		    stub.addDiagnosis(diagnosis);
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
