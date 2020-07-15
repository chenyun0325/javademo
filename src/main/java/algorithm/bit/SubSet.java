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



    public static List<List<Integer>> subsets2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        robot(0,nums,visited,res);
        return res;
    }

    /**
     * 回溯算法
     * @param idx
     * @param nums
     * @param visited
     * @param res
     */
    private static void robot(int idx,int[]nums,boolean[]visited,List<List<Integer>> res){

        /**
         * 递归退出条件
         */
        if (idx>=nums.length){
            List<Integer> tmp = new ArrayList<>();
            for (int i=0;i<nums.length;i++){
                if (visited[i]){
                    tmp.add(nums[i]);
                }
            }
            res.add(tmp);
            return;
        }
        //不选
        visited[idx]=false;
        robot(idx+1,nums,visited,res);
        //选
        visited[idx]=true;
        robot(idx+1,nums,visited,res);
    }

    private static void robot_v1(int idx,int[]nums,List<Integer>temp,List<List<Integer>> res){
        //如果已经搜索到最大深度，说明到达了叶子结点，则停止搜索。
        if (idx == nums.length){
            res.add(temp);
            return;
        }
       //假设左子树表示选择了当前数字，则先遍历选择的情况，拷贝当前已经构造的集合
        List<Integer> choiced = new ArrayList<>(temp);
        //添加当前数字进去
        choiced.add(nums[idx]);
        //继续遍历左分支
        robot_v1(idx + 1, nums,choiced, res);

        //若未选择当前数字，既是遍历右子树，因此不需要对集合做任何更改，直接传递到右子树继续遍历。
        robot_v1( idx + 1, nums,temp, res);
    }
}
