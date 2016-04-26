/**
 * 文件名：MyException.java
 *
 * 版本信息：
 * 日期：2014-6-9
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package connectionpool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
 * 项目名称：portal 02
 * 类名称：MyException
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-9 上午11:23:18
 * 修改人：chenyun
 * 修改时间：2014-6-9 上午11:23:18
 * 修改备注：
 * @version 
 * 
 */
public class MyException extends RuntimeException {

	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since Ver 1.1
	 */
	
	private static final long serialVersionUID = 6274147427355417040L;
	/**
	 * 创建一个新的实例 MyException.
	 *
	 */
	
	private String key;//异常代码
	private Object[] values;//其它信息
	private List<Throwable> causes=new ArrayList<Throwable>();//异常列表
	
	public MyException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建一个新的实例 MyException.
	 *
	 * @param message
	 */
	public MyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建一个新的实例 MyException.
	 *
	 * @param cause
	 */
	public MyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 创建一个新的实例 MyException.
	 *
	 * @param message
	 * @param cause
	 */
	public MyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public MyException(String message,String key){
		super(message);
		this.key=key;
	}
	public MyException(String message,String key,Object value) {
		super(message);
		this.key=key;
		this.values=new Object[]{value};
	}
	public MyException(String message,String key,Object[] value){
		super(message);
		this.key=key;
		this.values=value;
	}
	public MyException(Collection<? extends Throwable> _causes){
		causes.addAll(_causes);
	}

	public String getKey() {
		return key;
	}

	public Object[] getValues() {
		return values;
	}

	public List<Throwable> getCauses() {
		return causes;
	}

   

}
