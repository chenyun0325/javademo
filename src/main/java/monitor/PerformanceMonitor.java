package monitor;


public class PerformanceMonitor {
	private static ThreadLocal<MethodPerformance> record=new ThreadLocal<MethodPerformance>();
	
	public static void begin(String methodname){
		System.err.println("begin monitor...");
		MethodPerformance performance=new MethodPerformance(methodname);
		record.set(performance);
	}
	
	public static void end(){
		System.err.println("end monitor...");
		MethodPerformance performance=record.get();
		performance.printPerformance();
	}

}
