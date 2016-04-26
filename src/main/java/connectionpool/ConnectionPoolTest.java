/**
 * 文件名：ConnectionPoolTest.java
 *
 * 版本信息：
 * 日期：2014-6-12
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：portal 02
 * 类名称：ConnectionPoolTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-12 下午01:51:25
 * 修改人：chenyun
 * 修改时间：2014-6-12 下午01:51:25
 * 修改备注：
 * @version 
 * 
 */
public class ConnectionPoolTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args)throws MyException {
		List<Throwable> list=new ArrayList<Throwable>();
		
		// TODO Auto-generated method stub
		//1.初始化池信息---------系统启用时一次 application级别
		ConnectParam parm=new ConnectParam();
		parm.setDriver(Constant.driver);
		parm.setMaxconn(Constant.maxconn);
		parm.setMinconn(Constant.minconn);
		parm.setPassword(Constant.password);
		parm.setPoolname(Constant.poolname);
		parm.setUrl(Constant.url);
		parm.setUsername(Constant.username);
		parm.setWaitime(Constant.waitime);
		try{
		DBConnectionPoolFactory.bind(parm);
		//2.连接池找到一个DBConnectionPoolImp
		System.out.println("x1");
		DBConnectionPool pool=DBConnectionPoolFactory.lookup(parm.getPoolname());
		//-----------------------------------------------------------------------------------
		//3.使用连接开始
		//3.1从池中取到一个连接
		Connection con=pool.getconnection();
		Statement stm=con.createStatement();
		ResultSet rs=stm.executeQuery("select * from loan_money");
		rs.next();
		System.out.println(rs.getInt(3));
		System.out.println("xxxxxxxxxxxxxxx");
		//todo sth
		//3.2释放连接------不是真正关闭而是设置flag
		// pool.release(con);//与con.close();效果一样
 		 con.close();
		}catch (MyException e) {
			// TODO: handle exception
			System.out.println("x2");
			list.add(e);
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			list.add(e);
			e.printStackTrace();
		}
		
		
		 //3使用结束
		//------------------------------------------------------------------------------------
		//4.销毁指定名poolname的连接池---------系统关闭时一次 application级别
		 DBConnectionPoolFactory.unbind(parm.getPoolname());
		
		if (list.size()>0) {
			throw new MyException(list);
		}
		

	}

}
