package monitor;

public class MethodPerformance {
	
	private  String methodname;
	private long begintime;
	private long endtime;
	
	public MethodPerformance(String methodname){
		this.methodname=methodname;
		begintime=System.currentTimeMillis();
	}
	
	public void printPerformance(){
		endtime=System.currentTimeMillis();
		long elapse=endtime-begintime;
		System.err.println(methodname+"花费"+elapse+"毫秒");
	}

}
