/**
 * 文件名：ByteTest.java
 *
 * 版本信息：
 * 日期：2014-6-22
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package base;

import java.io.UnsupportedEncodingException;

/**
 * 
 * 项目名称：portal 02
 * 类名称：ByteTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-22 下午10:25:06
 * 修改人：chenyun
 * 修改时间：2014-6-22 下午10:25:06
 * 修改备注：
 * @version 
 * 
 */
public class ByteTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	 * @throws UnsupportedEncodingException 
	
	
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String str="中文";
		String ret="";
		byte[] arr;
		arr=str.getBytes("unicode");
		int len=arr.length;
		System.out.println(len);
		for (int i = 0; i < arr.length; i++) {
			String hex=Integer.toHexString(arr[i]&0xFF);
			if (hex.length()==1) {
				hex="0"+hex;
			}
			ret+=hex;
			System.out.println(arr[i]);
		}
		System.out.println(ret);

	}

}
