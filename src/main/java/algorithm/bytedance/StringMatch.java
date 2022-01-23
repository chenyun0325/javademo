package algorithm.bytedance;

/**
 * Created by chenyun on 2022/1/22.
 */
public class StringMatch {


    public static void main(String[] args) {
        // System.out.println(strMatch("abcdef","cde"));
        // System.out.println(strMatch1("abcdef","cde"));
        // System.out.println(getPartMatch("aaaaa"));
        System.out.println(getNext2("DABCDABDE"));
        System.out.println(getNext3("DABCDABDE"));
        System.out.println(getNext1("DABCDABDE"));
        System.out.println(getNext("ABCDABCE"));
        // System.out.println(getNext1("ABCDABCE"));
        // System.out.println(getNext2("ABCDABCE"));
    }


    /**
     * https://www.cnblogs.com/yjiyjige/p/3263858.html
     * 
     * @param s
     * @return
     */
    public static int[] getNext1(String s) {
        int len = s.length();
        int[] next = new int[len];
        char[] p = s.toCharArray();
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len - 1) {
            if (k == -1 || p[j] == p[k]) {
                ++k;
                ++j;
                // k++;
                // j++;
                next[j] = k;
                /**
                 * 优化代码 https://www.cnblogs.com/yjiyjige/p/3263858.html
                 *
                 * https://blog.csdn.net/v_july_v/article/details/7041827
                 */
                while (next[j] > -1 && p[j] == p[next[j]]) {
                    // next[j] = next[k];
                    next[j] = next[next[j]];
                }

                // while (next[j] > -1 && p[j] == p[k])
                // next[j] = next[k];
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * https://www.cnblogs.com/yjiyjige/p/3263858.html
     * 
     * @param s
     * @return
     */
    public static int[] getNext2(String s) {
        int len = s.length();
        int[] next = new int[len];
        char[] p = s.toCharArray();
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < len - 1) {
            if (k == -1 || p[j] == p[k]) {
                ++k;
                ++j;
                if (p[j] != p[k]) {
                    next[j] = k;
                } else {
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * https://www.cnblogs.com/yjiyjige/p/3263858.html
     * 
     * @param ps
     * @return
     */
    public static int[] getNext3(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过

                    next[j] = next[k];

                } else {

                    next[j] = k;

                }

            } else {

                k = next[k];

            }

        }

        return next;

    }


    public static int[] getNext(String s) {
        int len = s.length();
        int[] next = new int[len];
        char[] p = s.toCharArray();
        next[0] = 0;
        int k = 0;
        int i = 1;
        while (i < len) {
            while (k > 0 && p[k] != p[i]) {
                k = next[k - 1];
            }
            if (p[k] == p[i]) {
                k++;
            }
            next[i] = k;
            i++;
        }
        return next;
    }

    public static int[] getNext_(String s) {
        int len = s.length();
        int[] next = new int[len];
        char[] p = s.toCharArray();
        next[0] = -1;
        int k = -1;
        int i = 1;
        while (i < len) {
            // k = next[i];
            while (k > 0 && p[k] != p[i - 1]) {
                k = next[k];
            }
            if (p[k] == p[i]) {
                k++;
            }
            next[i] = k;
            i++;
        }
        return next;
    }

    /**
     * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
     *
     *
     * 参考如下: https://juejin.cn/post/6844904118507536392
     * 
     * @param pattern
     * @return
     */
    public static Integer[] getPartMatch(String pattern) {
        Integer[] count = new Integer[pattern.length()];
        int plen = pattern.length();
        count[0] = 0;

        /**
         * 从第一个位置开始
         */
        for (int i = 1; i < plen; i++) {
            String strChild = pattern.substring(0, i + 1);
            /**
             * 求前缀后缀
             */
            for (int j = 0, childLen = strChild.length(); j < childLen; j++) {
                String prefix = strChild.substring(0, j);
                String suffix_ = strChild.substring(j, childLen);
                String suffix = strChild.substring(childLen - j, childLen);
                System.out.println(prefix);
                System.err.println(suffix_);
                System.out.println(suffix);
                if (prefix.equals(suffix) && !prefix.equals("")) {
                    count[i] = prefix.length();
                }
                if (count[i] == null) {
                    count[i] = 0;
                }
            }
        }

        return count;
    }

    /**
     *
     * @param sources
     * @param pattern
     * @return
     */
    public static int strMatch(String sources, String pattern) {
        int index = -1;
        int slen = sources.length();
        int plen = pattern.length();
        if (slen < plen) {
            return index;
        }
        int i = 0, j = 0;
        while (i < slen && j < plen) {
            if (sources.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == plen) {
            index = i - j;
        }
        return index;
    }

    public static int strMatch1(String sources, String pattern) {
        int index = -1;
        int slen = sources.length();
        int plen = pattern.length();
        if (slen < plen) {
            return index;
        }
        /**
         * 用滑动尺方法,i表示可以右移最大值
         */
        int i = 0, j = 0;
        int delta = slen - plen;
        while (i < delta) {
            j = 0;
            while (j < plen && sources.charAt(i + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == plen) {
                return i;
            }
            i++;
        }
        return index;
    }


}
