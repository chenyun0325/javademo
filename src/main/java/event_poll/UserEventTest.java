/**
 * 文件名：UserEventTest.java
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
 * 类名称：UserEventTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午03:57:52
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午03:57:52
 * 修改备注：
 * @version 
 * 
 */
public class UserEventTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//新建事件监听器--处理器
		UserEventListener listener=null;
		listener=new UserEventListenerImp();
		//新建事件源-----------------------------受监控对象
		Bean oldbean=new Bean();
		//------------------------------检测bean是否发生更新?
		//轮询检测-----------1.一直检测  2.设置计划任务检测
		while(true){
			Bean newbBean=null;
		//获取新bean;  newbBean=getnew(oldbean);
			if (newbBean!=null) {//发生更新触发事件
				//新建事件
				UseEvent useEvent=new UseEvent(newbBean);
				//处理事件
				
				if(listener.changedLast(useEvent)){
					break;
				}
		   oldbean=(Bean) useEvent.getSource();
		}
		

	}
	}
}

