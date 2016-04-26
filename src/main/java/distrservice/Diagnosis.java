/**
 * 文件名：Diagnosis.java
 *
 * 版本信息：
 * 日期：Jul 4, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import java.util.Date;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Diagnosis
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 4, 2014 11:04:08 PM
 * 修改人：chenyun
 * 修改时间：Jul 4, 2014 11:04:08 PM
 * 修改备注：
 * @version 
 * 
 */
public class Diagnosis implements Flag {
	private String patient_id;
	private String hosptial_code;
	private String visit_number;
	private String diagnosis_description;
	private Date diagnosis_time;
	private int diagnosis_type;
	private String diagnosis_result;
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patientId) {
		patient_id = patientId;
	}
	public String getHosptial_code() {
		return hosptial_code;
	}
	public void setHosptial_code(String hosptialCode) {
		hosptial_code = hosptialCode;
	}
	public String getVisit_number() {
		return visit_number;
	}
	public void setVisit_number(String visitNumber) {
		visit_number = visitNumber;
	}
	public String getDiagnosis_description() {
		return diagnosis_description;
	}
	public void setDiagnosis_description(String diagnosisDescription) {
		diagnosis_description = diagnosisDescription;
	}
	public Date getDiagnosis_time() {
		return diagnosis_time;
	}
	public void setDiagnosis_time(Date diagnosisTime) {
		diagnosis_time = diagnosisTime;
	}
	public int getDiagnosis_type() {
		return diagnosis_type;
	}
	public void setDiagnosis_type(int diagnosisType) {
		diagnosis_type = diagnosisType;
	}
	public String getDiagnosis_result() {
		return diagnosis_result;
	}
	public void setDiagnosis_result(String diagnosisResult) {
		diagnosis_result = diagnosisResult;
	}
	

}
