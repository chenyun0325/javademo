/**
 * 文件名：DiagnosisInfoServiceImp.java
 *
 * 版本信息：
 * 日期：Jul 4, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.rmi.RemoteException;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DiagnosisInfoServiceImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 4, 2014 11:21:00 PM
 * 修改人：chenyun
 * 修改时间：Jul 4, 2014 11:21:00 PM
 * 修改备注：
 * @version 
 * 
 */
@WebService(endpointInterface="distrservice.IDiagnosisInfoService")
@SOAPBinding(style=SOAPBinding.Style.RPC)//不一定要添加
public class DiagnosisInfoServiceImp implements IDiagnosisInfoService {

	/* (非Javadoc)
	 * <p>Title: addDiagnosis</p>
	 * <p>Description:</p>
	 * @param diagnosis
	 * @return
	 * @throws RemoteException 
	 * @see distrservice.IDiagnosisInfoService#addDiagnosis(distrservice.Diagnosis)
	 */
	@Override
	public boolean addDiagnosis(Diagnosis diagnosis) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("add diagnosis ok");
		System.err.println(diagnosis.getDiagnosis_description());
		System.err.println(diagnosis.getDiagnosis_type());
		System.err.println(diagnosis.getDiagnosis_time());
		return true;
	}

	/* (非Javadoc)
	 * <p>Title: getAllDiagnosis</p>
	 * <p>Description:</p>
	 * @return
	 * @throws RemoteException 
	 * @see distrservice.IDiagnosisInfoService#getAllDiagnosis()
	 */
	/*@Override
	public Map<String, Diagnosis> getAllDiagnosis() throws RemoteException {
		// TODO Auto-generated method stub
		Map<String, Diagnosis> map=new HashMap<String, Diagnosis>();
		return map;
	}*/

	/* (非Javadoc)
	 * <p>Title: getDiagnosis</p>
	 * <p>Description:</p>
	 * @param patientId
	 * @param hosptialCode
	 * @param visitNumber
	 * @return
	 * @throws RemoteException 
	 * @see distrservice.IDiagnosisInfoService#getDiagnosis(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Diagnosis getDiagnosis(String patientId, String hosptialCode,
			String visitNumber) throws RemoteException {
		// TODO Auto-generated method stub
		Diagnosis diagnosis=new Diagnosis();
		diagnosis.setPatient_id("xx");
		return diagnosis;
	}

}
