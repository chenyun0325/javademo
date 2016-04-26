/**
 * 文件名：UseEventSources.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.Enumeration;
import java.util.Vector;


/**
 * 
 * 项目名称：portal 02
 * 类名称：UseEventSources
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午04:41:00
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午04:41:00
 * 修改备注：
 * @version 
 * 
 */
public class UseEventSources {
	//事件处理器列表
	private Vector<UserEventListener> sources=new Vector<UserEventListener>();
	UserEventListener uListener;
	public void addlistener(UserEventListener uEventListener){
		sources.addElement(uEventListener);
	}
	//触发事件源上的事件
    public void fire(){
    	Enumeration<UserEventListener> enumeration=sources.elements();
    	while (enumeration.hasMoreElements()) {
			uListener = (UserEventListener) enumeration.nextElement();
			//触发事件
			uListener.changedLast(new UseEvent(this));//this指事件源对象
			
		}
    }
}
