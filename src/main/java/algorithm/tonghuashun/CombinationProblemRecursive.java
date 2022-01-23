package algorithm.tonghuashun;

/**
 * Created by chenyun on 2022/1/21.
 * https://www.cnblogs.com/tusheng/articles/8109068.html
 */
public class CombinationProblemRecursive {

    private static String[] aa = { "aa1", "aa2" };
    private static String[] bb = { "bb1", "bb2", "bb3" };
    private static String[] cc = { "cc1", "cc2", "cc3", "cc4" };
    private static String[][] xyz = { aa, bb, cc };
    private static int counterIndex = xyz.length - 1;
    private static int[] counter = { 0, 0, 0 };

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < aa.length * bb.length * cc.length; i++) {
            System.out.print(aa[counter[0]]);
            System.out.print("\t");
            System.out.print(bb[counter[1]]);
            System.out.print("\t");
            System.out.print(cc[counter[2]]);
            System.out.println();
            handle();
        }
    }

    public static void handle() {
        counter[counterIndex]++;
        if (counter[counterIndex] >= xyz[counterIndex].length) {
            counter[counterIndex] = 0;
            counterIndex--;
            if (counterIndex >= 0) {
                handle();
            }
            counterIndex = xyz.length - 1;
            System.out.println(counterIndex);
        }
    }

}