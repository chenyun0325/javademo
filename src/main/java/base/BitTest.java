/**
 * 文件名：BitTest.java
 *
 * 版本信息：
 * 日期：2014-6-18
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package base;



/**
 * 
 * 项目名称：portal 02
 * 类名称：BitTest
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-18 下午04:41:55
 * 修改人：chenyun
 * 修改时间：2014-6-18 下午04:41:55
 * 修改备注：
 * @version 
 * 
 */
public class BitTest {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	private int aclState;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

    
     BitTest bit=new BitTest();
    int result= bit.setPermission(0, true);
    System.out.println(result);
    int result1=bit.setPermission(1, true);
    System.out.println(result1);
    int result2=bit.setPermission(2, true);
    System.out.println(result2);
    int result3=bit.setPermission(2, true);
    System.out.println(result3);
    int result4=bit.setPermission(2, false);
    System.out.println(result4);
    
     
	}
	public int setPermission(int permission,boolean yes){
		int tmp = 1;
		tmp = tmp << permission;
		if(yes){
			aclState |= tmp;
		}else{
			aclState &= ~tmp;
		}
		return aclState;
	}
	public int getAclState() {
		return aclState;
	}
	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

    
	
}
