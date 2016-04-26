/**
 * 文件名：UserEventListener.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.EventListener;

/**
 * 
 * 项目名称：portal 02
 * 类名称：UserEventListener
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午03:36:31
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午03:36:31
 * 修改备注：
 * @version 
 * 
 */
public interface UserEventListener extends EventListener {
	
	//事件处理
	/**
	 * 
	 */
	public boolean changedLast(UseEvent useEvent);

}
