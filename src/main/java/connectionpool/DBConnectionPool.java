/**
 * 文件名：DBConnectionPool.java
 *
 * 版本信息：
 * 日期：2014-6-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.sql.Connection;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DBConnectionPool
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-11 下午10:43:33
 * 修改人：chenyun
 * 修改时间：2014-6-11 下午10:43:33
 * 修改备注：
 * @version 
 * 
 */
public interface DBConnectionPool {
	//1.从池中获取空闲连接
	 Connection  getfreeconnection(long waitime) throws MyException;
	//2.初始化连接池
	boolean initconnection(ConnectParam param) throws MyException;
	//3.关闭数据库连接
	boolean close() throws MyException;
	//4.释放数据库连接
	boolean release(Connection con) throws MyException;
	//5.获取连接
	Connection getconnection() throws MyException;

}
