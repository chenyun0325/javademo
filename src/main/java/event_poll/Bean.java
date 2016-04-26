/**
 * 文件名：Bean.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package event_poll;

import java.util.ArrayList;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Bean
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 下午04:05:02
 * 修改人：chenyun
 * 修改时间：2014-7-2 下午04:05:02
 * 修改备注：
 * @version 
 * 
 */
public class Bean {
	private int id;
	private ArrayList<String> list=new ArrayList<String>();

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
