/**
 * 文件名：DBConnectionPoolFactory.java
 *
 * 版本信息：
 * 日期：2014-6-12
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * 
 * 项目名称：portal 02
 * 类名称：DBConnectionPoolFactory
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-12 上午11:26:59
 * 修改人：chenyun
 * 修改时间：2014-6-12 上午11:26:59
 * 修改备注：
 * @version 
 * 
 */
public class DBConnectionPoolFactory {
	private static Logger logger= LoggerFactory.getLogger(DBConnectionPoolFactory.class);
	static HashMap<String, DBConnectionPool> pools=null;//hashmap线程不安全有没有问题?hashtable替代
	static{
		pools=new HashMap<String, DBConnectionPool>(2, 0.75F);
	}
	/**
	 * 
	
	  * <p>Title: lookup</p>
	  * <p>Description:------------------------通过key找到DBConnectionPool</p>
	  * @param datasource
	  * @return 
	  * @return DBConnectionPool
	 */
	public static DBConnectionPool lookup(String datasource) throws MyException{
		 List<Throwable> list=new ArrayList<Throwable>();
		 DBConnectionPool dbpool=null;
		 synchronized (pools) {
			 dbpool=pools.get(datasource);	
		}		
		 if (dbpool==null) {
				// TODO: handle exception
				 MyException exception=new MyException(datasource+"的连接池不存在");
				 exception.printStackTrace();
				 list.add(exception);
		}		 
		if (list.size()>0) {
				throw new MyException(list);
		}
		return dbpool;
	 }
	/**
	 * 
	
	  * <p>Title: bind</p>
	  * <p>Description:根据参数初始化一个连接池，--------------系统初始化时候调用</p>
	  * @param param
	  * @return
	  * @throws MyException 
	  * @return DBConnectionPool
	 */
     public static DBConnectionPool bind(ConnectParam param)throws MyException{
    	//获取DBConnectionPool的实现类
    	 List<Throwable> list=new ArrayList<Throwable>();
    	 DBConnectionPool source=null;
    	 try {
    		source=lookup(param.getPoolname());//从池中获取连接
		  } catch (MyException e) {
			// TODO: handle exception
			list.add(e);
		 }    	 
    	 if (source!=null) {//已经有连接池
			MyException e=new MyException(param.getPoolname()+"的连接池已经存在");
			list.add(e);
		   }else 
		   {
		     source=new DBConnectionPoolImp();//新建连接池实现类实例
		     source.initconnection(param);//初始化连接池
		     synchronized (pools) {
			 pools.put(param.getPoolname(), source);//key-value存储	
		 }
		
		}
    	 
    /*	if (list.size()>0) {
		 throw new MyException(list);//不需要throw, 否则后面语句不执行就返回.-------------2014-6-14
		 //第一次bind时候连接池肯定不存在,source=lookup(param.getPoolname());肯定抛出异常-----2014-6-14
		}*/
    /*	for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getMessage());
			System.out.println(list.get(i).getCause());
			list.get(i).printStackTrace();
			System.out.println(i);
			logger.debug(list.get(i).getMessage(), list.get(i).getCause());
		}*/
     for (int i = 0; i < list.size(); i++) {
		MyException e=(MyException) list.get(i);
		List<Throwable> list2=e.getCauses();
		for (int j = 0; j < list2.size(); j++) {
			System.out.println(list2.get(j).getMessage());
			System.out.println("next");
			logger.debug(list2.get(j).getMessage());
		}
	}
    	
       System.out.println("没有返回source就退出");
	   return source;
    }
    public static DBConnectionPool rebind(ConnectParam param)throws MyException{
    	 List<Throwable> list=new ArrayList<Throwable>();
    	 DBConnectionPool dbpool=null;
    	 try {
    		 unbind(param.getPoolname());
    	     dbpool=bind(param);
		} catch (MyException e) {
			// TODO: handle exception
			list.add(e);
		}
    	
    	if (list.size()>0) {
    		throw new MyException(list);//异常抛给上层
        }
    	return dbpool;
    }
    /**
     * 
    
      * <p>Title: unbind</p>
      * <p>Description:----------------------------------系统关闭时候调用</p>
      * @param poolname
      * @throws MyException 
      * @return void
     */
    public static void unbind(String poolname)throws MyException{
    	 List<Throwable> list=new ArrayList<Throwable>();
    try {
    	synchronized (pools) {
    		 DBConnectionPool pool=pools.get(poolname);//获取pool对象	
    		 pool.close();//获取pool对象属性conns,调用close方法关闭每一个连接
    	     pools.remove(poolname);//移除key对应的value  
		}        
	} catch (Exception e) {
		// TODO: handle exception
		list.add(e);
	}
	if (list.size()>0) {
		throw new MyException(list);//异常抛给上层
    }
     	
   }
     
}
