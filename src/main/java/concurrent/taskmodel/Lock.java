package concurrent.taskmodel;

import java.util.concurrent.CountDownLatch;

/**
 * 同一jvm中解决线程挂起
 * @author chenyun
 *
 */
public class Lock {
	private long id;//任务唯一编号
	private CountDownLatch[] countDownLatchs;
	public Lock(long id, CountDownLatch[] countDownLatchs) {
		super();
		this.id = id;
		this.countDownLatchs = countDownLatchs;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public CountDownLatch[] getCountDownLatchs() {
		return countDownLatchs;
	}
	public void setCountDownLatchs(CountDownLatch[] countDownLatchs) {
		this.countDownLatchs = countDownLatchs;
	}
	
	

}
