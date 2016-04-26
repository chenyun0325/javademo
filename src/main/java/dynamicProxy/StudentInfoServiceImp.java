/**
 * 文件名：StudentInfoServiceImp.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package dynamicProxy;

/**
 * 
 * 项目名称：portal 02
 * 类名称：StudentInfoServiceImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午04:23:24
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午04:23:24
 * 修改备注：
 * @version 
 * 
 */
public class StudentInfoServiceImp implements StudentInfoService {

	/* (非Javadoc)
	 * <p>Title: findInfo</p>
	 * <p>Description:</p>
	 * @param studentname 
	 * @see dynamicProxy.StudentInfoService#findInfo(java.lang.String)
	 */
	@Override
	public void findInfo(String studentname) {
		// TODO Auto-generated method stub
		System.out.println("name:"+studentname);

	}

}
