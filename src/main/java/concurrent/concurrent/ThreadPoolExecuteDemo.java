/**
 * 文件名：ThreadPoolExecuteDemo.java
 *
 * 版本信息：
 * 日期：2014-6-19
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package concurrent.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 项目名称：portal 02
 * 类名称：ThreadPoolExecuteDemo
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-19 下午04:43:37
 * 修改人：chenyun
 * 修改时间：2014-6-19 下午04:43:37
 * 修改备注：
 * @version 
 * 
 */
public class ThreadPoolExecuteDemo {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	final  BlockingQueue<Runnable> queue=new SynchronousQueue<Runnable>();
	final  ThreadPoolExecutor executor=new ThreadPoolExecutor(10, 600, 30, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
	final AtomicInteger completedTask=new AtomicInteger();
	final AtomicInteger rejectedTask=new AtomicInteger();
	static long begintime;
	final  int count=1000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		begintime=System.currentTimeMillis();
		ThreadPoolExecuteDemo demo=new ThreadPoolExecuteDemo();		
		demo.start();
		//ThreadPoolExecuteDemo.start();
		
		
	}
	public  void start(){
		CountDownLatch latch=new CountDownLatch(count);
		CyclicBarrier barrier=new CyclicBarrier(count);
		for (int i = 0; i < count; i++) {
			new Thread(new Taskthread(latch, barrier)).start();			
		}
		
		try {
			latch.await();//等待所有任务完成
			//System.out.println("end");
			executor.shutdown();
		} catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	class Taskthread implements Runnable{

		/* (非Javadoc)
		  * <p>Title: run</p>
		  * <p>Description:</p> 
		  * @see java.lang.Runnable#run()
		 */
		private CountDownLatch latch;
		private CyclicBarrier barrier;
		public Taskthread(CountDownLatch latch,CyclicBarrier barrier){
			this.latch=latch;
			this.barrier=barrier;
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//System.out.println(Thread.currentThread().getName()+"begin wait");
				barrier.await();//等待所有线程进入
				//System.out.println(Thread.currentThread().getName()+"end wait");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				executor.execute(new Task(latch));
			} catch (RejectedExecutionException e) {
				// TODO: handle exception
				latch.countDown();
				System.err.println(Thread.currentThread().getName()+"---被拒绝任务数："+rejectedTask.incrementAndGet());
			}
		}
		
	}
	class Task implements Runnable{
		private CountDownLatch latch;//计数器
		public Task(CountDownLatch latch){
			this.latch=latch;
		}
		public void run(){
			//System.out.println(Thread.currentThread().getName() + ":Go");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"------------执行任务数:"+completedTask.incrementAndGet());
			System.out.println(Thread.currentThread().getName()+"----------------任务耗时："+(System.currentTimeMillis()-begintime)+"ms");
			latch.countDown();//计数减1,latch.await
		}
	}

}
