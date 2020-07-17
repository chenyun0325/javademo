package algorithm.dp;

public class MaxTwoArraySameOrder {

    public static void main(String[] args) {

        System.out.println(MaxTwoArraySameOrder("baba","caba"));
        System.out.println(MaxTwoArraySameOrderMethod("baba", "caba"));

    }

    /**
     * 最长公共子序列
     * @param s1
     * @param s2
     * @return
     */
    public static int MaxTwoArraySameOrderMethod(String s1,String s2){
        /**
         * 设X=x1x2…xm和Y=y1y2…yn是两个序列，Z=z1z2…zk是这两个序列的一个最长公共子序列。
         * 1. 如果xm=yn，那么zk=xm=yn，且Zk-1是Xm-1，Yn-1的一个最长公共子序列；
         * 2. 如果xm≠yn，那么zk≠xm，意味着Z是Xm-1，Y的一个最长公共子序列；
         * 3. 如果xm≠yn，那么zk≠yn，意味着Z是X，Yn-1的一个最长公共子序列。
         *
         *
         *
         * 我们使用dp[i][j]来表示第一个串的前i位和第二个串的前j位中的最长公共子序列，
         * 我们很容易能发现当两个串的任意一个串的当前长度为0时，它的最长公共子序列的长度为0，
         * 所以先对dp数组的边界进行初始化。然后我们发现，如果a[i]=b[j]，dp[i][j]=dp[i-1][j-1]+1，
         * 很显然，当比对的位字符一样时，能得到该状态转移方程。如果a[i]≠b[j]，dp[i][j]=max(dp[i-1][j],dp[i][j-1])，
         * 该状态转移方程是由上面的2，3条取最大值得到的
         */
        int n = s1.length();
        int m = s2.length();

        /**
         */
        int[][] dp = new int[n+1][m+1];

        /**
         * 前0位赋值为0
         */
        for (int i=0;i<=n;i++){
            dp[i][0]=0;
        }
        for (int j=0;j<=m;j++){
            dp[j][0]=0;
        }

        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                /*
                 * 如果当c[i][j]时，字符串1从头开始长度是i，字符串2从头开始长度是j时他们最后一个字符相同
                 * 就同时把他们向前移动一位，找c[i-1][j-1]时长度最大的再加一
                 * 表现在二维数组中就是c[i][j]左上方的点
                 */
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    /*
                     * 如果当c[i][j]时，他们最后一个字符不相同
                     * 要将str1往前移动一位的c[i-1][j]的lcs长度，或者将str2往前移动一位的c[i][j-1]的lcs长度
                     * 哪个长，将它赋给c[i][j]
                     * 表现在二维数组中就是c[i][j]上方的点或者左方的点
                     */
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * 最长公共子串
     * @param s1
     * @param s2
     * @return
     */
    public static String MaxTwoArraySameOrder(String s1,String s2){
        /**
         * 设X=x1x2…xm和Y=y1y2…yn是两个序列，Z=z1z2…zk是这两个序列的一个最长公共子序列。
         * 1. 如果xm=yn，那么zk=xm=yn，且Zk-1是Xm-1，Yn-1的一个最长公共子序列；
         * 2. 如果xm≠yn，那么zk≠xm，意味着Z是Xm-1，Y的一个最长公共子序列；
         * 3. 如果xm≠yn，那么zk≠yn，意味着Z是X，Yn-1的一个最长公共子序列。
         *
         *
         *
         * 我们使用dp[i][j]来表示第一个串的前i位和第二个串的前j位中的最长公共子序列，
         * 我们很容易能发现当两个串的任意一个串的当前长度为0时，它的最长公共子序列的长度为0，
         * 所以先对dp数组的边界进行初始化。然后我们发现，如果a[i]=b[j]，dp[i][j]=dp[i-1][j-1]+1，
         * 很显然，当比对的位字符一样时，能得到该状态转移方程。如果a[i]≠b[j]，dp[i][j]=max(dp[i-1][j],dp[i][j-1])，
         * 该状态转移方程是由上面的2，3条取最大值得到的
         */
        int n = s1.length();
        int m = s2.length();

        /**
         */
        int[][] dp = new int[n+1][m+1];

        /**
         * 前0位赋值为0
         */
        for (int i=0;i<=n;i++){
            dp[i][0]=0;
        }
        for (int j=0;j<=m;j++){
            dp[j][0]=0;
        }

        int maxlen =0,maxEnd =0;
        for (int i=1;i<=n;i++){
            for (int j=1;j<=n;j++){
                /*
                 * 如果当c[i][j]时，字符串1从头开始长度是i，字符串2从头开始长度是j时他们最后一个字符相同
                 * 就同时把他们向前移动一位，找c[i-1][j-1]时长度最大的再加一
                 * 表现在二维数组中就是c[i][j]左上方的点
                 */
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    /*
                     * 如果当c[i][j]时，他们最后一个字符不相同
                     * 要将str1往前移动一位的c[i-1][j]的lcs长度，或者将str2往前移动一位的c[i][j-1]的lcs长度
                     * 哪个长，将它赋给c[i][j]
                     * 表现在二维数组中就是c[i][j]上方的点或者左方的点
                     */
                    dp[i][j] =0;
                }
                if (dp[i][j]>maxlen){
                    maxlen = dp[i][j];
                    maxEnd=i;//若记录i,则最后获取LCS时是取str1的子串
                }
            }
        }
//        return maxlen;
        return s1.substring(maxEnd-maxlen,maxEnd);
    }
}
