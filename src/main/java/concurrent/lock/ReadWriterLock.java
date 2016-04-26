/**
 * 文件名：ReadWriterLock.java
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
 * 类名称：ReadWriterLock
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-15 下午03:26:09
 * 修改人：chenyun
 * 修改时间：2014-6-15 下午03:26:09
 * 修改备注：
 * @version 
 * 
 */
public class ReadWriterLock {
	/**
	 * 读写锁机制
	 * 1.读的时候能读
	 * 2.读的时候不能写
	 * 3.写的时候不能读
	 * 4.写的时候不能写
	 * 
	 */
	private int readingReaders=0;//正在读取的线程
	private int writingWriters=0;//正在写的线程0/1
	private int waitingWriters=0;//等待写的线程
	private boolean preferwriter=true;//写优先，preferwriter&waitingWriters两个变量完成线程读写切换.或者waitingReader&prferReader

	//读操作的等待条件:writingWriters>0||preferwriter&waitingWriters>0
	//写操作等待条件：readingReaders>0||writingWriters>0
	public synchronized void readlock() throws InterruptedException{
		while(writingWriters>0||preferwriter&waitingWriters>0){
			wait();
		}
		readingReaders++;
	}
	public synchronized void readunlock(){
		readingReaders--;//计数
		preferwriter=true;
		notifyAll();
	}
	public synchronized void writerlock() throws InterruptedException{
		//等待写线程
		waitingWriters++;
		try{
			while (readingReaders>0||writingWriters>0) {
				wait();
			}
		}
		finally{
			waitingWriters--;//等待线程被唤醒
		}
		writingWriters++;
	}
	public synchronized void writerunlock(){
		writingWriters--;
		preferwriter=false;
		notifyAll();
	}
}
