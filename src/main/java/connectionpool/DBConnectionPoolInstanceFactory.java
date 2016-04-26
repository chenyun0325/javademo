/**
 * 文件名：DBConnectionPoolInstanceFactory.java
 *
 * 版本信息：
 * 日期：2014-6-12
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.sql.Driver;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * 项目名称：portal 02
 * 类名称：DBConnectionPoolInstanceFactory
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-12 下午03:14:04
 * 修改人：chenyun
 * 修改时间：2014-6-12 下午03:14:04
 * 修改备注：
 * @version 
 * 
 */
public class DBConnectionPoolInstanceFactory {
 static DBConnectionPoolFactory instance;//连接池管理类实例
 static int clients;//连接池个数
 private Vector<Driver> drivers= new Vector<Driver>();//数据库驱动集合
 public static Hashtable<String, DBConnectionPool> pools=new Hashtable<String, DBConnectionPool>();//key-value

}
