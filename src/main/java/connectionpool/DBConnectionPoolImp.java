/**
 * 文件名：DBConnectionPoolImp.java
 *
 * 版本信息：
 * 日期：2014-6-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 
 * 项目名称：portal 02
 * 类名称：DBConnectionPoolImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-11 下午10:59:04
 * 修改人：chenyun
 * 修改时间：2014-6-11 下午10:59:04
 * 修改备注：
 * @version 
 * 
 */
public class DBConnectionPoolImp implements DBConnectionPool {

	/* (non-Javadoc)
	 * @see com.portal.oa.services.DBConnectionPool#close()
	 * 
	 */
	ArrayList<_Connection> conns=new ArrayList<_Connection>();//连接池容器
	int concounts;//连接池连接数量
	private ConnectParam param;
	@Override
	//关闭连接池所有连接
	public boolean close() throws MyException {
		// TODO Auto-generated method stub
		boolean flag=true;
		int count=0;
		Iterator<_Connection> iter=conns.iterator();
		while (iter.hasNext()) {
			_Connection connection = (_Connection) iter.next();
		    flag=connection.close();//不执行invoke方法
			count++;
			concounts--;
		}
		System.out.println(count);
		if (!flag) {
			throw new MyException("连接未正常关闭");
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see com.portal.oa.services.DBConnectionPool#getconnection(com.portal.oa.model.ConnectParam)
	 */
	@Override
	public  Connection getconnection() throws MyException {
		// TODO Auto-generated method stub
		List<Throwable> list=new ArrayList<Throwable>();
		if (this.param==null) {
			list.add(new MyException("参数没有初始化"));
		}
		//1.从池中找空闲对象
		Connection con=getfreeconnection(0);
		if (con==null) {
		 //目前连接数与最大线程数比较
		if (getConcounts()>=param.getMaxconn()) {
			con=getfreeconnection(param.getWaitime());
		}else {
			//没有超出新建	
		try {
			Connection con2=DriverManager.getConnection(param.getUrl(), param.getUsername(), param.getPassword());
		
		//--------代理要返回的连接对象
		 _Connection _conn=new _Connection(con2,true);
		//-----往对象池中同步加入_conn
		 synchronized (conns) {
			conns.add(_conn);
			concounts++;//计数增加
		}
		 con=_conn.getconnection();
		} catch (SQLException e) {
			 list.add(e);
			 e.printStackTrace();
			}
			
		}
		if (list.size()>0) {
			throw new MyException(list);
		}
		
		}
		return con;
	}

	/* (non-Javadoc)
	 * @see com.portal.oa.services.DBConnectionPool#getfreeconnection(long)
	 */
	@Override
	public synchronized Connection getfreeconnection(long waitime) throws MyException {//保证同一时间只有一个线程拥有this对象控制权
		// TODO Auto-generated method stub
		List<Throwable> list=new ArrayList<Throwable>();
		Connection conn=null;
		Iterator<_Connection> iter=conns.iterator();
		//遍历池找出flag为false可有连接
		//1.有可用连接返回
		while (iter.hasNext()) {
			_Connection _con = (_Connection) iter.next();
			if (!_con.isFlag()) {
				//返回一个可用连接
				conn=_con.getconnection();
				break;
			}
		}
		if (conn==null&&waitime>0) {
			//等待几毫秒
		 try {
			Thread.sleep(waitime);
			} catch (InterruptedException e) {
		    list.add(e);
		}
		conn=getfreeconnection(0);
		if (conn==null) {
			MyException e=new MyException("没有可用连接", "sql");
			list.add(e);
		}
		if (list.size()>0) {
			throw new MyException(list);
		}
		}
		return conn;
	}

	/* (non-Javadoc)
	 * @see com.portal.oa.services.DBConnectionPool#initconnection(com.portal.oa.model.ConnectParam)
	 */
	@Override
	public boolean initconnection(ConnectParam param) throws MyException {
		// TODO Auto-generated method stub
		List<Throwable> list=new ArrayList<Throwable>();
		this.param=param;//给成员变量，在其他方法共享数据
		Connection con=null;
		boolean ok=true;
			try {
				Class.forName(param.getDriver());
			for (int i = 0; i < param.getMinconn(); i++) {
				con=DriverManager.getConnection(param.getUrl(), param.getUsername(), param.getPassword());
				_Connection con1=new _Connection(con,false);//设置连接为空闲
				synchronized (conns) {//保证同一时间只有一个线程拥有conns控制权
					conns.add(con1);
					concounts++;
				}				
			}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				list.add(e);
				ok=false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				list.add(e);
				System.out.println("mmm");
				e.printStackTrace();
				ok=false;
			}
			System.out.println("xxx");
			if (list.size()>0) {
				throw new MyException(list);
			}
		return ok;
	}

	/* (non-Javadoc)
	 * @see com.portal.oa.services.DBConnectionPool#release()
	 */
	@Override
	public boolean release(Connection con) throws MyException {
		// TODO Auto-generated method stub
		List<Throwable> list=new ArrayList<Throwable>();
		boolean flag=true;
		try {
			con.close();//con是代理要返回的连接对象,执行invoke方法
		} catch (SQLException e) {
			// TODO: handle exception
			list.add(e);
			flag=false;
		}
		
		if (list.size()>0) {
			throw new MyException(list);
		}
		return flag;
	}

	public int getConcounts() {
		return concounts;
	}

	public void setConcounts(int concounts) {
		this.concounts = concounts;
	}
	

}
