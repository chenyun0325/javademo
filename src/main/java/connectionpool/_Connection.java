/**
 * 文件名：_Connection.java
 *
 * 版本信息：
 * 日期：2014-6-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 项目名称：portal 02
 * 类名称：_Connection
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-11 下午11:34:45
 * 修改人：chenyun
 * 修改时间：2014-6-11 下午11:34:45
 * 修改备注：
 * @version 
 * 
 */
public class _Connection implements InvocationHandler {

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	private final static String method_name="close";
	private Connection con;//被代理的对象
	private boolean flag;//是否被使用
	private long lastaccess=System.currentTimeMillis();//上次访问时间
	@Override
	//--------------------invoke方法参数是java帮助传递的不用关心内部机制------------------------------------------by chenyun 2014-6-27
	public Object invoke(Object proxy, Method method, Object[] args)
			throws MyException {
		List<Throwable> list=new ArrayList<Throwable>();
		// TODO Auto-generated method stub
		Object object=null;//方法执行的返回值
		//判断是否调用close方法,调用设置flag=false
		String name=method.getName();
		if(method_name.equals(name)){
		  setFlag(false);}
		else{
		try {
			object=method.invoke(con, args);//为何是con而不是proxy?-------------------2014-6-13
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			list.add(e);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			list.add(e);
		} catch (InvocationTargetException e) {
			list.add(e);
		}
		if (list.size()>0) {
			throw new MyException(list);
		}
		}
		lastaccess=System.currentTimeMillis();
		return object;
	}
	/**
	 * 
	 * 创建一个新的实例 _Connection.
	 *<p>Title:</p>
	 *<p>Description:</p>
	 * @param con
	 * @param flag
	 */
   public _Connection(Connection con,boolean flag){
	   this.con=con;
	   this.flag=flag;
   }
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public long getLastaccess() {
		return lastaccess;
	}
	public void setLastaccess(long lastaccess) {
		this.lastaccess = lastaccess;
	}
	//关闭数据库连接
	public boolean close()throws MyException{
		boolean flag=true;
		List<Throwable> list=new ArrayList<Throwable>();
		//处理代码块1的异常处理
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			list.add(e);
			flag=false;
		}
		if (list.size()>0) {
			throw new MyException(list);
		}
		return flag;
	}
	//获取数据库连接--proxy方式,用于截获close方法
	public Connection getconnection()throws MyException{
		//运用proxy返回被代理对象con的接口
		//Connection conn1=(Connection) Proxy.newProxyInstance(con.getClass().getClassLoader(), con.getClass().getInterfaces(), this);
		List<Throwable> list=new ArrayList<Throwable>();
		Connection conn1=null;
		Class[] iterfaces=con.getClass().getInterfaces();
	    for (int i = 0; i < iterfaces.length; i++) {
			System.out.println(iterfaces[i].getCanonicalName());
		}
	    try {
			 conn1=(Connection) Proxy.newProxyInstance(con.getClass().getClassLoader(), new Class[]{Connection.class}, this);
			 

			
		} catch (Exception e) {
			// TODO: handle exception
		  list.add(e);	
		}
		if (list.size()>0) {
			throw new MyException(list);
		}
		return conn1;
	}

}
