/**
 * 文件名：Book.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package annotations;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Book
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午12:51:44
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午12:51:44
 * 修改备注：
 * @version 
 * 
 */
public enum Book {
jhtp("x","1"),jhtn("y","2"),jhtk("z","3"),jhtl("r","4"),jhts("t","5");
//instance fields
 private final String title;
 private final String year;
 //enum constructor
 Book(String title,String year){
	 this.title=title;
	 this.year=year;
 }
public String getTitle() {
	return title;
}
public String getYear() {
	return year;
}
 
}
