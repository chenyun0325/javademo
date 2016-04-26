/**
 * 文件名：InitArray.java
 *
 * 版本信息：
 * 日期：2014-7-2
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package base;

import java.util.Random;

/**
 * 
 * 项目名称：portal 02
 * 类名称：InitArray
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-7-2 上午09:20:58
 * 修改人：chenyun
 * 修改时间：2014-7-2 上午09:20:58
 * 修改备注：
 * @version 
 * 
 */
public class InitArray {

	/**
	
	 * <p>Title: main</p>
	 * <p>Description:</p>
	 * @param args 
	 * @return void 
	
	
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array= new int[10];
		System.out.printf("%s%8s\n", "index","value");
		for (int i = 0; i < array.length; i++) {
			System.out.printf("%d%8d\n",i,array[i]);
		}
		
		int[] arrayint={1,2,2,33,55,677,4,89,87};
		int sum=0;
		System.out.printf("%s%8s\n", "index","value");
		for (int i = 0; i < arrayint.length; i++) {
			System.out.printf("%d%8d\n",i,arrayint[i]);
			sum+=arrayint[i];
			/*arrayint[i]=2*arrayint[i];
			System.out.printf("%d%8d\n",i,arrayint[i]);	*/
		}
		System.out.println(sum);
		Random random=new Random();
		int[] frequency=new int[7];
		for(int roll=1;roll<=60000;roll++){
			int face=1+random.nextInt(6);
			++frequency[face];
		}
		System.out.printf("%s%10s\n","face","frequency");
		for (int i = 1; i < frequency.length; i++) {
			System.out.printf("%4d%10d\n",i,frequency[i]);				
		}
		//二维数组
		int[][] b={{1,2},{3,4}};
		int[][] c={{1,2},{3},{4,5,6}};
		int[][] d=new int[2][];
		d[0]=new int[5];
		d[1]=new int[3];
		outputarray(b);
		outputarray(c);
		outputarray(d);

	}
	public static void outputarray(int[][] array){
		//
		int sum=0;
		int rowsum=0;
		for(int row=0;row<array.length;row++){
			//控制行不变
			for(int column=0;column<array[row].length;column++){
			//array[row][column]=0;赋值
			sum+=array[row][column];
			rowsum+=array[row][column];
			System.out.printf("%d",array[row][column]);	
			}
			System.out.println();
			System.out.println("行小计："+rowsum);
			rowsum=0;//行小计初始化
			System.out.println();//输出新行
		}
		System.out.println("总计："+sum);
	}

}
