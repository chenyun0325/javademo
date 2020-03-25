/**
 * 文件名：SocketclientTest.java
 *
 * 版本信息：
 * 日期：2014-3-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package socketclient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * 项目名称：portal 02
 * 类名称：SocketclientTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-3-11 下午10:27:44
 * 修改人：chenyun
 * 修改时间：2014-3-11 下午10:27:44
 * 修改备注：
 * @version 
 * 
 */
public class SocketclientTest {

	/**
	
	 * main(这里用一句话描述这个方法的作用)
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket=new Socket(Constant.Ip_Addr, Constant.Port);
			new Thread(new ClientThread(socket), "ArubaSocketclient").start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
