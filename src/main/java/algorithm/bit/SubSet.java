package algorithm.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/weixin_46422390/article/details/106640624
 *
 * https://www.jianshu.com/p/c78326ec3d63
 */
public class SubSet {


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets1(nums));
    }

    public static List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> res = new ArrayList<>();

        for (int i =0; i<(1<< nums.length);i++){ //得到每个数选和不选的所有可能
            List<Integer> sub = new ArrayList<>();//数组

            /**
             * ps:i=3则第一二位选了
             */
            for (int j=0;j<nums.length;j++){//每个数每一位选还是没选
                if (((i>>j)&1) ==1){//选了
                   sub.add(nums[j]);
                }
            }
            res.add(sub);
        }

        return res;
    }

    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> empty = new ArrayList<>();
        result.add(empty);
        //因为题目要求升序输出，所以需要先排序。
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {        //循环所有已有的子集，用于拷贝
                List<Integer> temp = new ArrayList<Integer>(result.get(j));     //ArrayList的拷贝构造函数
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }
}
