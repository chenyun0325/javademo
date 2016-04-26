/**
 * 文件名：DynamicproxyTest.java
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
 * 类名称：DynamicproxyTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午05:33:33
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午05:33:33
 * 修改备注：
 * @version 
 * 
 */
public class DynamicproxyTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentInfoService studentInfoService=(StudentInfoService) InstanceFactory.getAOPProxyedObject("dynamicProxy.StudentInfoServiceImp");
        studentInfoService.findInfo("chenyun");
	}

}
