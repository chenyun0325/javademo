/**
 * 文件名：EventSource.java
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
 * 类名称：EventSource
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-11 上午10:35:25
 * 修改人：chenyun
 * 修改时间：2014-7-11 上午10:35:25
 * 修改备注：
 * @version 
 * 
 */
public interface EventSource {
	/**
	 * 产生事件的源 eg 1.button
	 * 2.一条数据,修改、删除可以定义为事件，如在集群环境中 修改数据后需要同步到其它服务器
	 *  事件的调用时机----1.delete(){
	 *  SourceEventListner的实例.sourceevent(SourceEvent实例) //--------事件触发方式
	 * }
	 *    2.通过一线程while(true)循环对比数据是否修改，修改则新建事件（传事件源测）-->新建事件监听（传事件）-->触发事件-----轮询方式
	 * 3.容器，容器的启动,启动前,启动后，停用，停用前，停用后都可以定义为事件
	 */
	public static final String start_event="start";
	public static final String before_start_event="before_start";
	public static final String after_start_event="after_start";
	public static final String stop_event="stop";
	//删除,修改
	public static final String delete_event="delete";
	
	public void delete();//对应删除事件
	
	public void start();
	public void stop();
	
	//为事件源添加、删除、查找监听器
	public void addSourceEventlistner(SourceEventListner listner);
	public void removeSourceEventlistner(SourceEventListner listner);
	public SourceEventListner[] findSourceEventListners();

}
