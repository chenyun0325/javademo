package monitor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;


public class PerfInterceptor implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(PerfInterceptor.class);
	
	//存储方法统计值，map需要IO持久化
	private static ConcurrentHashMap<String, MethodStats> map=new ConcurrentHashMap<String, MethodStats>();
	
	
	private static int statfreq=10;
	
	private static int methodWarningThreshold=1000;
	
	
	
	
	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();
		Object result=null;
		try {
			result= method.proceed();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
		 String methodname=method.getMethod().getName();
		 long end=System.currentTimeMillis();
		 //long elapse=end-start;		 
		 updatestatus(methodname, start,end);
		}
		return result;
	}
	
	private void updatestatus(String methodname,long begin,long end){
	 MethodStats stats=map.get(methodname);
	 if (stats==null) {
		stats=new MethodStats(methodname);
		stats.lastbegintime=begin;
		stats.lastendtime=end;
		map.put(methodname, stats);
	}
	 long elapse=end-begin;
	 //总计数与总耗时
	 stats.count++;
	 stats.totaltime+=elapse;
	 if (elapse>stats.maxtime) {
		stats.maxtime=elapse;
	}
	 //超时记录
	 if (elapse>methodWarningThreshold) {
		logger.warn("");
	}
	 //统计
	 if (stats.count%statfreq==0) {
		long avgTime=stats.totaltime/stats.count;//总的平均时间
		long runAvg=(stats.totaltime-stats.lasttotaltime)/statfreq;//运行statfreq的平均时间
		/**
		 * [stats.lastbegintime-end,avgTime]
		 */
		logger.debug("method: " + methodname + "(), cnt = " + stats.count + ", lastTime = " + elapse + ", avgTime = " + avgTime + ", runningAvg = " + runAvg + ", maxTime = " + stats.maxtime);
		//reset last
		stats.lasttotaltime=stats.totaltime;
		stats.lastbegintime=begin;
		stats.lastendtime=end;
	}
	}

}
