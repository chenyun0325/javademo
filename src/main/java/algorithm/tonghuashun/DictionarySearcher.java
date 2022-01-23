package algorithm.tonghuashun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyun on 2022/1/21.
 * https://www.cnblogs.com/well-is-good-man/p/15521593.html
 */
public class DictionarySearcher {
    String[] words;

    public DictionarySearcher(String fileWords) {
        String[] input = fileWords.split(",");
        this.words = input;
    }


    public Map<String, List<Integer>> search(String document) {
        Map<String, List<Integer>> output = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (document.contains(words[i])) {
                List<Integer> count = new ArrayList<>();
                int firstIndex = document.indexOf(words[i]);
                count.add(firstIndex);
                while (document.indexOf(words[i], firstIndex + words[i].length()) != -1) {
                    int pos = document.indexOf(words[i], firstIndex + words[i].length());
                    count.add(pos);
                    firstIndex = pos;
                }
                output.put(words[i], count);
            }
        }
        return output;
    }

    public Map<String, List<Integer>> searchKmp(String document) {
        Map<String, List<Integer>> output = new HashMap<>();
        for (String word : words) {
            List<Integer> count = KMP(document.split(""), word);
            if (!count.isEmpty()) {
                output.put(word, count);
            }
        }
        return output;
    }

    /**
     * https://wentinghwt.github.io/tinkerbaby.github.io/posts/KMP_Impl_notes/
     * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
     * @param t
     * @param word
     * @return
     */
    private List<Integer> KMP(String[] t, String word) {
        String[] p = word.split("");
        List<Integer> indexs = new ArrayList<>();
        int i = 0;
        int j = 0;
        int[] next = getNext(word);
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i].equals(p[j])) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == p.length) {
                indexs.add(i - j);
                j = 0;
                next = getNext(word);
            }
        }
        return indexs;
    }

    private int[] getNext(String word) {
        int length = word.length();
        int[] next = new int[length];
        String[] strchar = word.split("");
        int j = 0;
        int k = -1;
        next[0] = -1;
        while (j < length - 1) {
            if (k == -1 || strchar[j].equals(strchar[k]) ) {
                j++;
                k++;
                if (!strchar[j].equals(strchar[k])) {
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

    public static void main(String[] args) {
        String fileWords = "浙江,浙江大学,美国,美国政府";
        String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。"
                + "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" + "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....";
        long jdkSearchStart = System.currentTimeMillis();
        DictionarySearcher ds = new DictionarySearcher(fileWords);
        System.out.println("Java字符串匹配:" + ds.search(document));
        System.out.println("Java字符串匹配耗时:" + (System.currentTimeMillis() - jdkSearchStart));
        long kmpSearchStart = System.currentTimeMillis();
        System.out.println("KMP模式匹配:" + ds.searchKmp(document));
        System.out.println("KMP模式匹配耗时:" + (System.currentTimeMillis() - kmpSearchStart));
        ds.getNext("DABCDABDE");
    }
}
