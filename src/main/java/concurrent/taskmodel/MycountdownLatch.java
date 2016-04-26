package concurrent.taskmodel;

import java.util.concurrent.CountDownLatch;

public class MycountdownLatch {
	private String identityKey;//针对哪个类,哪个方法,A-a
	private CountDownLatch latch;
	public MycountdownLatch(String identityKey, CountDownLatch latch) {
		super();
		this.identityKey = identityKey;
		this.latch = latch;
	}
	public String getIdentityKey() {
		return identityKey;
	}
	public void setIdentityKey(String identityKey) {
		this.identityKey = identityKey;
	}
	public CountDownLatch getLatch() {
		return latch;
	}
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	

}
