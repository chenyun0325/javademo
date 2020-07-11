package algorithm.array;

import org.junit.Test;

/**
 * https://www.jianshu.com/p/8d4668f7a374
 */
public class ArrayDemo {

    /**
     * /*
     *     二维数组：就是元素为一维数组的一个数组。
     *
     *     格式1：
     *         数据类型[][] 数组名 = new 数据类型[m][n];
     *
     *         m:表示这个二维数组有多少个一维数组。
     *         n:表示每一个一维数组的元素有多少个。
     *
     *     注意：
     *         A:以下格式也可以表示二维数组
     *             a:数据类型 数组名[][] = new 数据类型[m][n];
     *             b:数据类型[] 数组名[] = new 数据类型[m][n];
     *         B:注意下面定义的区别
     *             int x;
     *             int y;
     *             int x,y;
     *
     *             int[] x;
     *             int[] y[];
     *
     *             int[] x,y[];//这个x是一维数组，y是二维数组
     * */

    @Test
    public void test1(){
        //定义一个二维数组
        int[][] arr = new int[3][2];
        //定义了一个二维数组arr
        //这个二维数组有3个一维数组的元素
        //每一个一维数组有2个元素
        //输出二维数组名称
        System.out.println(arr); //地址值    [[I@175078b
        //输出二维数组的第一个元素一维数组的名称
        System.out.println(arr[0]); //地址值    [I@42552c
        System.out.println(arr[1]); //地址值    [I@e5bbd6
        System.out.println(arr[2]); //地址值    [I@8ee016
        //输出二维数组的元素
        System.out.println(arr[0][0]); //0
        System.out.println(arr[0][1]); //0
    }

    @Test
    public void test2(){

        int[][] arr = new int[3][];

        System.out.println(arr);    //[[I@175078b
        System.out.println(arr[0]); //null
        System.out.println(arr[1]); //null
        System.out.println(arr[2]); //null

        //动态的为每一个一维数组分配空间
        arr[0] = new int[2];
        arr[1] = new int[3];
        arr[2] = new int[1];

        System.out.println(arr[0]); //[I@42552c
        System.out.println(arr[1]); //[I@e5bbd6
        System.out.println(arr[2]); //[I@8ee016

        System.out.println(arr[0][0]); //0
        System.out.println(arr[0][1]); //0

        arr[1][0] = 100;
        arr[1][2] = 200;
    }

    @Test
    public void test3(){
        //定义数组
        int[][] arr = {{1,2,3},{4,5},{6}};

        System.out.println(arr);
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);

        System.out.println(arr[0][0]); //1
        System.out.println(arr[1][0]); //4
        System.out.println(arr[2][0]); //6

        System.out.println(arr[0][1]); //2
        System.out.println(arr[1][1]); //5
    }
}
