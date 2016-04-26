/**
 * 文件名：UserEventListenerImp.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

/**
 * 
 * 项目名称：portal 02
 * 类名称：UserEventListenerImp
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午03:51:46
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午03:51:46
 * 修改备注：
 * @version 
 * 
 */
public class UserEventListenerImp1 implements UserEventListener {

	/* (非Javadoc)
	 * <p>Title: changedLast</p>
	 * <p>Description:</p>
	 * @param useEvent
	 * @return 
	 * @see event_poll.UserEventListener#changedLast(event_poll.UseEvent)
	 */
	@Override
	public boolean changedLast(UseEvent useEvent) {
		// TODO Auto-generated method stub	
		System.out.println("x2");
		System.out.println(useEvent);
		return false;
	}

}
