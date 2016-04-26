/**
 * 文件名：RecordLock.java
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
 * 类名称：RecordLock
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-15 下午02:11:51
 * 修改人：chenyun
 * 修改时间：2014-6-15 下午02:11:51
 * 修改备注：
 * @version 
 * 
 */
public class RecordLock {
	private Object obj;//针对哪条记录上锁
	private boolean IsLock =false;//资源是否被使用标志

	public void lock() throws InterruptedException{
		synchronized (this) {
			while(IsLock){
				wait();	//资源被锁住，调用资源的线程等待		
				}
			IsLock=true;
			//标志资源被锁住
		}
      }
	public void unlock(){
		synchronized (this) {
			IsLock=false; //标志资源解锁
			while(!IsLock){
				notifyAll();//资源释放,通知所有等待资源的线程
			}
		}
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public boolean isIsLock() {
		return IsLock;
	}
	public void setIsLock(boolean isLock) {
		IsLock = isLock;
	}
	
	}

