package concurrent.taskmodel;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CountDownLatch;

public class Task implements Runnable{
	private MycountdownLatch latch;//本次方法运行的计数变量
	private Lock lock;//本次方法运行的前置条件
	//private String taskname;	
	private Object[] datas;// datas-----------------------执行任务需要的数据

	public Task(MycountdownLatch latch, Lock lock,Object[] args) {
		super();
		this.latch = latch;
		this.lock = lock;			
		this.datas=args;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//0.参数合法性校验
		
		//1.检查任务的前置条件是否满足
	if (lock!=null) {
		int length=lock.getCountDownLatchs().length;
		CountDownLatch[] countDownLatchs=lock.getCountDownLatchs();
		for (int i = 0; i < length; i++) {
		try {
			countDownLatchs[i].await();//如果前置条件不满足,挂起本线程
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		}	
	}
	    //2.根据taskname反射执行
	   String taskname=latch.getIdentityKey();
	   try {
		InvokeTool.invokeMethod(taskname, datas);
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	    //3.计数器操作
	   latch.getLatch().countDown();
		
	}
	
}