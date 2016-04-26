/**
 * 文件名：Constant.java
 *
 * 版本信息：
 * 日期：2014-6-12
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Constant
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-12 下午02:20:00
 * 修改人：chenyun
 * 修改时间：2014-6-12 下午02:20:00
 * 修改备注：
 * @version 
 * 
 */
public class Constant {
	public static final String driver="com.mysql.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/jbpm?useUnicode=true&amp;characterEncoding=utf-8";
	public static final String username="root";
	public static final String password="1";
	public static final int minconn=5;
	public static final int maxconn=50;
	public static final long timeout=600000;
	public static final long waitime=30000;
	public static final String poolname="mysql";
	
	
	

}
