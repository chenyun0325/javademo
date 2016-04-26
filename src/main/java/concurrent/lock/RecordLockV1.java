/**
 * 文件名：RecordLockV1.java
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
 * 类名称：RecordLockV1
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-15 下午02:42:28
 * 修改人：chenyun
 * 修改时间：2014-6-15 下午02:42:28
 * 修改备注：
 * @version 
 * 
 */
public class RecordLockV1 {
	private Object obj;//针对哪条记录上锁
	private boolean isLock=false;
	public synchronized void lock(){
		isLock=true;
	}
	public synchronized void unlock(){
		isLock=false;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isLock() {
		return isLock;
	}
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}

}
