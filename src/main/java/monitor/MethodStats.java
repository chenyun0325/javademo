package monitor;

public class MethodStats {
	public String methodname;
	public long count;//调用总次数
	public long lastcount;//上次统计时点调用次数
	public long totaltime;
	public long lasttotaltime;//上次统计时点调用总时间
	public long lastbegintime;
	public long lastendtime;
	public long maxtime;
	
	public MethodStats(String methodname) {
		super();
		this.methodname = methodname;
	}
	public String getMethodname() {
		return methodname;
	}
	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getLastcount() {
		return lastcount;
	}
	public void setLastcount(long lastcount) {
		this.lastcount = lastcount;
	}
	public long getTotaltime() {
		return totaltime;
	}
	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}
	public long getLasttotaltime() {
		return lasttotaltime;
	}
	public void setLasttotaltime(long lasttotaltime) {
		this.lasttotaltime = lasttotaltime;
	}
	public long getLastbegintime() {
		return lastbegintime;
	}
	public void setLastbegintime(long lastbegintime) {
		this.lastbegintime = lastbegintime;
	}
	public long getLastendtime() {
		return lastendtime;
	}
	public void setLastendtime(long lastendtime) {
		this.lastendtime = lastendtime;
	}
	public long getMaxtime() {
		return maxtime;
	}
	public void setMaxtime(long maxtime) {
		this.maxtime = maxtime;
	}
	
	

}
