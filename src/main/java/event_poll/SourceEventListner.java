/**
 * 文件名：SourceEventListner.java
 *
 * 版本信息：
 * 日期：2014-7-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.EventListener;

/**
 * 
 * 项目名称：portal 02
 * 类名称：SourceEventListner
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-11 上午10:37:43
 * 修改人：chenyun
 * 修改时间：2014-7-11 上午10:37:43
 * 修改备注：
 * @version 
 * 
 */
public interface SourceEventListner extends EventListener{
	/**
	 * 监控Source变化进行的事件处理
	 */
 public void sourceevent(SourceEvent event);//传入事件
}
