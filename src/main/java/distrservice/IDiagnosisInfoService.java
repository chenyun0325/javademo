/**
 * 文件名：IDiagnosisInfoService.java
 *
 * 版本信息：
 * 日期：Jul 4, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.rmi.RemoteException;
import java.util.Map;

import javax.jws.WebService;

/**
 * 
 * 项目名称：portal 02
 * 类名称：IDiagnosisInfoService
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 4, 2014 11:09:21 PM
 * 修改人：chenyun
 * 修改时间：Jul 4, 2014 11:09:21 PM
 * 修改备注：
 * @version 
 * 
 */
@WebService
public interface IDiagnosisInfoService extends Flag {
	//public Map<String, Diagnosis> getAllDiagnosis()throws RemoteException;
	public Diagnosis getDiagnosis(String patient_id, String hosptial_code, String visit_number) throws RemoteException;
	public boolean addDiagnosis(Diagnosis diagnosis) throws RemoteException;

}
