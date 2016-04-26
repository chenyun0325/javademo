/**
 * 文件名：SourceEventListnerImp.java
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
 * 类名称：SourceEventListnerImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-11 下午12:07:13
 * 修改人：chenyun
 * 修改时间：2014-7-11 下午12:07:13
 * 修改备注：
 * @version 
 * 
 */
public class SourceEventListnerImp implements SourceEventListner {

	/* (非Javadoc)
	 * <p>Title: sourceevent</p>
	 * <p>Description:</p>
	 * @param event 
	 * @see event_poll.SourceEventListner#sourceevent(event_poll.SourceEvent)
	 */
	@Override
	public void sourceevent(SourceEvent event) {
		// TODO Auto-generated method stub
		//获取事件源
		EventSource source=event.getEventSource();
		//处理事件
		if (EventSource.start_event.equals(event.getType())) {
			System.out.println("发生了启动事件");
		}
		else if (EventSource.stop_event.equals(event.getType())) {
			System.out.println("发生了关闭事件");
		}
	}

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
