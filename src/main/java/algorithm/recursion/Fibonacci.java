package algorithm.recursion;


/**
 * dp思想
 * https://www.jianshu.com/p/e788c046264d
 * https://blog.csdn.net/zw6161080123/article/details/80639932
 * https://www.jianshu.com/p/6e3dcc476c6a
 */
public class Fibonacci {

    public static void main(String[] args) {
        int[] meno = new int[51];
        for (int i=0;i<meno.length;i++){
            meno[i]=-1;
        }
        System.out.println(fib(40,meno));
    }


    public static int fib(int n){
        if (n<=1){
            return n;
        }
        return fib(n-1)+fib(n-2);
    }

    public static int fib(int n,int[]meno){
        /**
         * 已经计算过直接返回
         */
        if (meno[n]!=-1){
            return meno[n];
        }
        if (n<=1){
            meno[n]=n;
        }else {
            meno[n]=fib(n-1,meno)+fib(n-2, meno);
        }
        return meno[n];
    }

    public static int fib_dynamic(int n){
        if (n<=1){
            return n;
        }
        int[] meno = new int[n+1];
        meno[0]=0;
        meno[1]=1;
        for (int i =2;i<=n;i++){
            meno[i]=meno[i-1]+meno[i-2];
        }
        return meno[n];
    }
}
