package algorithm.tonghuashun;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by chenyun on 2022/1/21.
 */
public class DictionarySearcherBack {
    String[] words;

    public DictionarySearcherBack(String fileWords) {
        String[] input = fileWords.split(",");
        this.words = input;
    }

    public DictionarySearcherBack(String[] words) {
        this.words = words;
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
     * 
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
            if (k == -1 || strchar[j].equals(strchar[k])) {
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


    /**
     * 分批处理函数: 内存考虑:函数内部分批处理词文件 时间考虑:返回切割之后词文件,多线程处理
     *
     * @param fileName 文件路径
     * @param batchSize 批次大小
     * @return 分批切割的词文件
     */
    public static List<List<String>> batchProcess(String fileName, int batchSize) {

        List<List<String>> batchWordList = new ArrayList<>();
        /**
         * 自动关闭流
         */
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lineContent;
            int batchCount = 0;
            List<String> batchWord = new ArrayList<>();

            /**
             * readline返回null表示到达文件尾部
             */
            while (((lineContent = reader.readLine()) != null)) {
                batchCount++;
                batchWord.add(lineContent);
                /**
                 * 达到批次处理
                 */
                if (batchCount == batchSize) {
                    batchWordList.add(batchWord);

                    /**
                     * 如果考虑内存限制，分批处理位置
                     */

                    /**
                     * 重置及容器清理
                     */
                    batchCount = 0;
                    batchWord = new ArrayList<>();
                }
            }
            /**
             * 未满足条件批次剩余记录处理
             */
            if (lineContent == null) {
                if (!batchWord.isEmpty()) {
                    /**
                     * 批次处理
                     */
                    batchWordList.add(batchWord);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return batchWordList;
    }

    private static class WordTask implements Callable<Map<String, List<Integer>>>{
        final List<String> words;
        final String document;
        private DictionarySearcherBack ds;

        public WordTask(List<String> words, String document) {
            this.words = words;
            this.document = document;
            ds = new DictionarySearcherBack(words.toArray(new String[words.size()]));
        }
        @Override
        public Map<String, List<Integer>> call() throws Exception {
            return ds.search(document);
        }
    }

    public static void main(String[] args) {

        String fileWords = "浙江,浙江大学,美国,美国政府";
        String document = "美国规划协会中国办公室揭牌仪式及美国规划领域合作研讨会在浙江大学城乡规划设计研究院208会议室举行。"
                + "美国规划协会CEO James Drinan，国际项目及外联主任Jeffrey Soule先生，浙江大学党委副书记任少波，" + "浙江大学控股集团领导杨其和，西湖区政府代表应权英副主任....";

        ExecutorService executors = Executors.newFixedThreadPool(5);
        List<List<String>> batchWords = batchProcess("data.txt", 3);
        List<Future<Map<String, List<Integer>>>> wordTaskResList = new ArrayList<>();
        for (List<String> batchWord : batchWords) {
            Future<Map<String, List<Integer>>> wordTaskRes = executors.submit(new WordTask(batchWord, document));
            wordTaskResList.add(wordTaskRes);
        }

        Map<String, List<Integer>> mergeMap = new HashMap<>();
        for (Future<Map<String, List<Integer>>> future : wordTaskResList) {
            try {
                /**
                 * 主线程阻塞
                 */
                Map<String, List<Integer>> taskRes = future.get();
                mergeMap.putAll(taskRes);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                //处理捕获的线程异常
            }
        }
        executors.shutdown();
        System.err.println("多线程Java字符串匹配:"+mergeMap);


        long jdkSearchStart = System.currentTimeMillis();
        DictionarySearcherBack ds = new DictionarySearcherBack(fileWords);
        System.out.println("Java字符串匹配:" + ds.search(document));
        System.out.println("Java字符串匹配耗时:" + (System.currentTimeMillis() - jdkSearchStart));
        long kmpSearchStart = System.currentTimeMillis();
        System.out.println("KMP模式匹配:" + ds.searchKmp(document));
        System.out.println("KMP模式匹配耗时:" + (System.currentTimeMillis() - kmpSearchStart));
    }
}
