/**
 * 文件名：Printtask.java
 *
 * 版本信息：
 * 日期：2014-6-24
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package concurrent.concurrent;

import java.util.Random;


/**
 * 
 * 项目名称：portal 02
 * 类名称：Printtask
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-24 上午10:22:10
 * 修改人：chenyun
 * 修改时间：2014-6-24 上午10:22:10
 * 修改备注：
 * @version 
 * 
 */
public class Printtask implements Runnable {

	/* (非Javadoc)
	 * <p>Title: run</p>
	 * <p>Description:</p> 
	 * @see java.lang.Runnable#run()
	 */
	private final int sleeptime;
	private final String taskname;
	private static Random random=new Random();
	public Printtask(String taskname){
		this.taskname=taskname;
		this.sleeptime=random.nextInt(5000);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
    try {
		System.out.printf("%s going to sleep for %d milliseconds.\n",taskname,sleeptime);
		Thread.sleep(sleeptime);
	} catch (InterruptedException e) {
		// TODO: handle exception
		System.out.printf("%s %s \n",taskname,"interrupt");
	}
	System.out.printf("%s done sleeping \n", taskname);
	}

}
