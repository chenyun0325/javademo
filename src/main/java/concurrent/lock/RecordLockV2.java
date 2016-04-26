/**
 * 文件名：RecordLockV2.java
 *
 * 版本信息：
 * 日期：2014-6-15
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package concurrent.lock;

/**
 * 
 * 项目名称：portal 02
 * 类名称：RecordLockV2
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-15 下午02:53:41
 * 修改人：chenyun
 * 修改时间：2014-6-15 下午02:53:41
 * 修改备注：
 * @version 
 * 
 */
public class RecordLockV2 {
	private Object obj;//上锁对象
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public synchronized void lock() {	
		//没有使线程挂起机制
	}
    public synchronized void unlock(){
   	
    }
}
