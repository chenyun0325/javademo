/**
 * 文件名：EnumTest.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package annotations;

import java.util.EnumSet;

/**
 * 
 * 项目名称：portal 02
 * 类名称：EnumTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午12:57:53
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午12:57:53
 * 修改备注：
 * @version 
 * 
 */
public class EnumTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("all books:");
		for(Book book:Book.values()){
			System.out.printf("%-10s%-10s%s\n",book,book.getTitle(),book.getYear());
		}
		System.out.println("range books");
		for(Book book:EnumSet.range(Book.jhtp, Book.jhtk)){
			System.out.printf("%-10s%-10s%s\n",book,book.getTitle(),book.getYear());
		}
		
	}

}
