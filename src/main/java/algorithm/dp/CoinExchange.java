package algorithm.dp;

import java.util.HashMap;
import java.util.Map;

public class CoinExchange {


    /**
     * 暴力搜索
     *
     * @param penny 不同面额(1,5,10)
     * @param index 步骤0开始
     * @param aim   目标钱数
     * @return
     */
    public static int core1(int[] penny, int index, int aim) {
        int result = 0;
        if (index == penny.length) {//所有币种用完
            result = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; i * penny[index] <= aim; i++) {
                result += core1(penny, index + 1, aim - i * penny[index]);
            }
        }
        return result;
    }

    static Map<String, Integer> map = new HashMap<>();

    /**
     * 记忆化搜索
     *
     * @param penny
     * @param index
     * @param aim
     * @return
     */
    public static int core2(int[] penny, int index, int aim) {
        String key = String.join("_", String.valueOf(index), String.valueOf(aim));
        if (map.containsKey(key)) {
            return map.get(key);
        }
        int result = 0;
        if (index == penny.length) {//所有币种用完
            result = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; i * penny[index] <= aim; i++) {
                result += core2(penny, index + 1, aim - i * penny[index]);
            }
        }
        map.put(key, result);
        return result;
    }

    /**
     * https://www.jianshu.com/p/6e3dcc476c6a
     * 动态规划
     *
     * @param penny
     * @param aim
     * @return
     */
    public static int core3(int[] penny, int aim) {
        /**
         * dp[i][j],前i种币种可以凑好j的方案数
         */
        int n = penny.length;
        int[][] dp = new int[n][aim + 1];
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i % penny[0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {

                /**
                 * 凑好j的方案
                 * 对第i种币种，可以使用0-j/penny[i]次
                 */
                for (int m = 0; m * penny[i] <= j; m++) {
                    dp[i][j] += dp[i - 1][j - m * penny[i]];
                }

            }
        }
        return dp[n - 1][aim];
    }

    public static int core4(int[] penny, int aim) {
        /**
         * dp[i][j],前i种币种可以凑好j的方案数
         */
        int n = penny.length;
        int[][] dp = new int[n][aim + 1];
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i % penny[0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                if (j <= penny[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - penny[i]];
                }
            }
        }
        return dp[n - 1][aim];
    }

    public static int core5(int[] penny,int aim){
        int n = penny.length;
        /**
         * dp[j]凑足目标j的方案数
         */
        int[] dp = new int[aim+1];

        /**
         * 初始化
         */
        dp[0]=1;
        for (int i=1;i<=aim;i++){
            if (i%penny[0] == 0){
                dp[i]=1;
            }else {
                dp[i]=0;
            }
        }

        for (int j=1;j<n;j++){
            for (int k =1;k<=aim;k++){
                if (k>=penny[j]){
                    dp[k]=dp[k]+dp[k-penny[j]];
                }
            }
        }

        return dp[aim];
    }

    /**
     * https://www.cnblogs.com/hapjin/p/5578852.html
     *
     * @param coinsValues 可用来找零的硬币 coinsValues.length是硬币的种类
     * @param n 待找的零钱
     * @return 最少硬币数目
     */
    public static int charge(int[] coinsValues, int n){
        int[][] c = new int[coinsValues.length + 1][n + 1];

        //特殊情况1
        for(int i = 0; i <= coinsValues.length; i++)
            c[i][0] = 0;
        for(int i = 0; i <= n; i++)
            c[0][i] = Integer.MAX_VALUE;

        for(int j_money = 1; j_money <=n; j_money++)
        {

            for(int i_coinKinds = 1; i_coinKinds <= coinsValues.length; i_coinKinds++)
            {
                if(j_money < coinsValues[i_coinKinds-1])//特殊情况2，coinsValues数组下标是从0开始的,  c[][]数组下标是从1开始计算的
                {
                    c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];//只能使用 第 1...(i-1)枚中的硬币
                    continue;
                }

                //每个问题的选择数目---选其中较小的
                if(c[i_coinKinds - 1][j_money] < (c[i_coinKinds][j_money - coinsValues[i_coinKinds-1]] +1))
                    c[i_coinKinds][j_money] = c[i_coinKinds - 1][j_money];
                else
                    c[i_coinKinds][j_money] = c[i_coinKinds][j_money - coinsValues[i_coinKinds-1]] +1;
            }
        }
        return c[coinsValues.length][n];
    }

    public static void main(String[] args) {
        int[] penny = new int[]{3,4,7};
        int n = penny.length;
        int aim =33;
        System.out.println(core2(penny,0,aim));
        System.out.println(core4(penny,aim));
//        System.out.println(charge(penny, aim));
    }
}
