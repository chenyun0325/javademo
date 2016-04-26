/**
 * 文件名：ClientThread.java
 *
 * 版本信息：
 * 日期：2014-3-11
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package socketclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


import socketclient.Constant;

/**
 * 
 * 项目名称：portal 02
 * 类名称：ClientThread
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-3-11 下午09:39:45
 * 修改人：chenyun
 * 修改时间：2014-3-11 下午09:39:45
 * 修改备注：
 * @version 
 * 
 */
public class ClientThread implements Runnable {
	Socket socket=null;
	public ClientThread(Socket socket){
		this.socket=socket;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		OutputStream writer=null;
		InputStream reader=null;
		System.out.println("conect aruba server");
		try {
			writer=socket.getOutputStream();//输出流,用于向服务器发送数据
			reader=socket.getInputStream();//输入流,用于接收服务器数据
			//交互的5个步骤
			for (int i = 0; i <5; i++) {
				dowriter(writer, i);
				//System.out.println(i);
				doreader(reader);		
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				reader.close();
				writer.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		// TODO Auto-generated method stub

	}
	public static boolean dowriter(OutputStream out,int i){
		String str=null;
		switch (i) {
		case 0:
         str=Constant.ServerUser;
			break;
		case 1:
	     str=Constant.ServerPass;
			break;
		case 2:
			str=Constant.ModelUser;
			break;
		case 3:
			str=Constant.ModelPass;
			break;
		case 4:
			str="local-userdb-guest add username cyyxdmn password ddddds expiry duration 400";
			break;
		}
		str=str+"\n";
		try {
			out.write(str.getBytes());
			out.flush();
			str=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public static boolean doreader(InputStream in){
		
		try {
		byte bytes[]=new byte[1024];	
		in.read(bytes);
		System.out.println(new String(bytes,"US-ASCII").trim() );
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

}
