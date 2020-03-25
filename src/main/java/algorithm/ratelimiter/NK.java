package algorithm.ratelimiter;

import org.junit.Test;

/**
 * 给一组大小为n的整数数组，计算长度为k的子数组的最大值
 *
 * 考虑数组arr [] = { 5,2，-1,0,3 }，k = 3和n = 5的值
 */
public class NK {



    public static int maxSum(int[] arr,int n,int k){
        if (n<k){
            throw new IllegalArgumentException("n<k");
        }
        int maxSum = 0;

        /**
         * 计算index=0开始k长度的窗口sum并且作为窗口最大值
         */
        for (int index =0;index<k;index++){
            maxSum+=arr[index];
        }
        int windowsSum = maxSum;

        /**
         * 从index=0开始窗口滑动，滑动step=1,重新计算窗口sum值
         */
        for (int index = k;index<n;index++ ){
            windowsSum+=arr[index]-arr[index-k];
            /**
             * 重新比较大小
             */
            maxSum = Math.max(maxSum,windowsSum);
        }

        return maxSum;
    }

    @Test
    public void test(){
        int[] arr = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k =4;
        int n = arr.length;
        int maxSum = NK.maxSum(arr, n, k);
        System.out.println(maxSum);
    }

}
