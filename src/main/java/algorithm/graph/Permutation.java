package algorithm.graph;

/**
 * https://segmentfault.com/a/1190000018771841
 * https://www.cnblogs.com/cndccm/p/12461902.html
 * https://blog.csdn.net/weixin_33884611/article/details/91404986
 */
public class Permutation {

    public static void main(String[] args) {
        char[] num ={'a','b','c'};
        backtrack(0,num.length-1,num);

    }

    public static void backtrack(int start ,int end,char[] num){

        if (start == end){
            System.out.println(num);
            return;
        }
        //注意循环下标迭代
        for (int i =start;i<=end;i++){
            /**
             * 交换数组元素
             */
            char x = num[start];
            num[start]=num[i];
            num[i] = x;

            backtrack(start+1,end,num);
            //回溯
            x = num[start];
            num[start]=num[i];
            num[i] = x;
        }
    }

    /**
     * https://segmentfault.com/a/1190000018771841
     * @param k
     * @param n
     * @param num
     * @param temp
     * @param hash
     */
    public static void backtrack(int k,int n,char[] num,char[] temp,boolean[]hash){
        if (k==n){
            System.out.println(temp);
            return;
        }

        for (int i=0;i<num.length;i++){
            //如果不在
            if (!hash[i]){
                hash[i]=true;
                temp[i]=num[i];
                backtrack(k+1,n,num,temp,hash);
                //回溯
                hash[i]=false;
            }
        }
    }


}
