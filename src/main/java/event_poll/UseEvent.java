/**
 * 文件名：UseEvent.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.EventObject;

/**
 * 
 * 项目名称：portal 02
 * 类名称：UseEvent
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午03:31:26
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午03:31:26
 * 修改备注：
 * @version 
 * 
 */
public class UseEvent extends EventObject {

	
	/**
	 * 创建一个新的实例 UseEvent.
	 *<p>Title:</p>
	 *<p>Description:</p>
	 * @param source
	 */
	private Object source;//产生事件的事件源,eg.button
	public UseEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
		this.source=source;
	}
	public Object getSource() {
		return source;
	}
	

}
