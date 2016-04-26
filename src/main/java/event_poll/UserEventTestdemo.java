/**
 * 文件名：UserEventTestdemo.java
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
 * 类名称：UserEventTestdemo
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午04:53:06
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午04:53:06
 * 修改备注：
 * @version 
 * 
 */
public class UserEventTestdemo {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//新建事件源
		UseEventSources ds=new UseEventSources();
		UserEventListener ls=null;
		ls=new UserEventListenerImp();
		ds.addlistener(ls);
		ls=new UserEventListenerImp1();
		ds.addlistener(ls);
		//触发事件源上所有事件
		ds.fire();

	}

}
