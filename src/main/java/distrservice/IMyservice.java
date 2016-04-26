/**
 * 文件名：IMyservice.java
 *
 * 版本信息：
 * 日期：Jul 7, 2014
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package distrservice;

import javax.jws.WebService;

/**
 * 
 * 项目名称：portal 02
 * 类名称：IMyservice
 * 类描述：
 * 创建人：chenyun
 * 创建时间：Jul 7, 2014 3:18:11 PM
 * 修改人：chenyun
 * 修改时间：Jul 7, 2014 3:18:11 PM
 * 修改备注：
 * @version 
 * 
 */
@WebService
public interface IMyservice {
	
 int add(int a, int b);
 int minus(int a, int b);
}
