package concurrent.taskmodel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ComTasksTest {

	/**
	 * @param args
	 */
	final  BlockingQueue<Runnable> queue=new SynchronousQueue<Runnable>();
	final  ThreadPoolExecutor executor=new ThreadPoolExecutor(10, 600, 30, TimeUnit.SECONDS, queue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		//生成一种方法运行次序,反射动态执行
		
		/**
		 * -1:根据定义文件,组装方法需要的参数.
		 * 定义文件-------------类
		 * 参数---------------实例
		 */
		
		/**
		 * 0.根据定义文件,构建网状数据结构。
		 * 层次遍历,循环调用1-3步骤
		 * 
		 */
		
		/**
		 * 1.构建本次方法运行的计数器
		 */
		CountDownLatch cLatch=new CountDownLatch(1);
		MycountdownLatch latch=new MycountdownLatch("taskmodel.ComTasks.method1", cLatch);
		/**
		 * 2.构建本次方法运行的前置条件
		 * a.从树关系中找出本节点的父节点
		 * 
		 * Task task1=new ComTasks.Task(latch,null);
		 */
		
		Lock lock=new Lock(1, null);//任务编号-------类与实例关系
		
		Task task=new Task(latch, lock,null);
		
		ComTasksTest run=new ComTasksTest();
		
		/**
		 * 3.任务委托给同一jvm的不同线程池中的不同线程去执行
		 * 
		 * 根据taskmodel.ComTasks.method1方法中执行的线程池定义
		 * threadpool
		 */
		
		run.executor.submit(task);
		
		
		
		/**
		 * 1-3步骤抽象出一个类
		 */
		

	}
	
	public static void method1(){
		System.out.println("任务开始方法：method1");
	}
	
	public static void method2(){
		System.out.println("任务方法：method2---->前置方法：method1");
	}
	public static void method3(){
		System.out.println("任务方法：method3---->前置方法：method2");
	}
	public static void method4(){
		System.out.println("任务方法：method4---->前置方法：method1");
	}
	public static void method5(){
		System.out.println("任务方法：method5---->前置方法：method3&&前置方法：method4");
	}
	
	
	

}
