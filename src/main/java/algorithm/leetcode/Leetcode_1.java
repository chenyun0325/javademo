package algorithm.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * Created by chenyun on 2020/4/15.
 */
public class Leetcode_1 {
    public static void main(String[] args) {
        int[] nums ={2,9,7,15,34};
        int[] index = twoSum(nums, 9);
        System.out.println(JSON.toJSONString(index));
    }

    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public static int[] sum_target(int[] nums,int target){
        int[] index = new int[2];
        int[] index_value = new int[2];
        int length = nums.length;
        for (int i = 0;i<length;i++){
            for (int j=i;j<length;j++){
                if (nums[i]+nums[j]==target){
                    index[0]=i;
                    index[1]=j;
                    index_value[0]=nums[i];
                    index_value[1]=nums[j];
                    return index;
                }
            }
        }

        return index;
    }

    public static int[] twoSum(int[] nums,int target){
        int length = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i =0;i<length;i++){
                int value = target - nums[i];
                if (map.containsKey(value)) {
                    return new int[]{i, map.get(value)};
                }
                map.put(nums[i], i);
        }
        return new int[2];
    }
}
