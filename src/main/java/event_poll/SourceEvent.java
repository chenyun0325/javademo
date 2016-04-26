/**
 * 文件名：SourceEvent.java
 *
 * 版本信息：
 * 日期：2014-7-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.EventObject;

/**
 * 
 * 项目名称：portal 02
 * 类名称：SourceEvent
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-11 上午10:46:11
 * 修改人：chenyun
 * 修改时间：2014-7-11 上午10:46:11
 * 修改备注：
 * @version 
 * 
 */
public class SourceEvent extends EventObject {


/**
 * 事件类
 */
private EventSource eventSource;//事件源
private String type;//对应EventSource的事件类型，eg:delete_event
private Object object;//其它数据

public SourceEvent(EventSource eventSource,String type,Object obj){
	super(eventSource);//-------------传递事件源
	this.eventSource=eventSource;
	this.type=type;
	this.object=obj;
	
}
public SourceEvent(EventSource eventSource,String type){
	this(eventSource, type, null);
}
public EventSource getEventSource() {
	return eventSource;
}
public String getType() {
	return type;
}
public Object getObject() {
	return object;
}


}
