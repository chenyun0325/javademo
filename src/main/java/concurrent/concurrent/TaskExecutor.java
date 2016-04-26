/**
 * 文件名：TaskExecutor.java
 *
 * 版本信息：
 * 日期：2014-6-24
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package concurrent.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 项目名称：portal 02
 * 类名称：TaskExecutor
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-24 上午11:08:53
 * 修改人：chenyun
 * 修改时间：2014-6-24 上午11:08:53
 * 修改备注：
 * @version 
 * 
 */
public class TaskExecutor {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printtask task1=new Printtask("task1");
		Printtask task2=new Printtask("task2");
		Printtask task3=new Printtask("task3");
		System.out.println("start");
		ExecutorService threadExService=Executors.newCachedThreadPool();
		//开始任务
		threadExService.execute(task1);
		threadExService.execute(task2);
		threadExService.execute(task3);
        threadExService.shutdown();
        //等待所有任务完毕 ，用CountDownLatch
        try {
			boolean taskend=threadExService.awaitTermination(1, TimeUnit.MINUTES);
			if (taskend) {
				System.out.println("end1");
			}else {
				System.out.println("wait end");
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        System.out.println("end2");
		


	}

}
