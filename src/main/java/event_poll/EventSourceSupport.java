/**
 * 文件名：EventSourceSupport.java
 *
 * 版本信息：
 * 日期：2014-7-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

/**
 * 
 * 项目名称：portal 02
 * 类名称：EventSourceSupport
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-11 上午11:41:10
 * 修改人：chenyun
 * 修改时间：2014-7-11 上午11:41:10
 * 修改备注：
 * @version 
 * 
 */
public class EventSourceSupport {
	private EventSource eventSource;//事件源
	private SourceEventListner[] listners=new SourceEventListner[0];//事件处理器数组
	private final Object lock=new Object();//同步锁

	/**
	 * 增删查---监听器
	 * 触发事件监听器
	 */
	public EventSourceSupport(EventSource eventSource){//构造方法
		super();
		this.eventSource=eventSource;
	}
	public void addSourceEventlistner(SourceEventListner listner){
		synchronized (lock) {
			SourceEventListner[] result=new SourceEventListner[listners.length+1];//新建数组，长度+1
			for (int i = 0; i < listners.length; i++) {
				//拷贝监听器数组
				result[i]=listners[i];
			}
			//新传入的监听器加入数组尾部
			result[listners.length]=listner;
		  //赋值--回传
			listners=result;
		}
	}
	public void removeSourceEventlistner(SourceEventListner listner){
		
	}
	public void fireSourceEvent(String type,Object data){
		
	}
	public SourceEventListner[] findSourceEventListners(){
		return listners;
	}
	
}
