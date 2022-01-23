package algorithm.tonghuashun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenyun on 2022/1/21.
 */
public class CombinationProblem {


    public static List<List<String>> getSetCombination(List<List<String>> inputs) {

        List<List<String>> outputs = new ArrayList<>();

        /**
         * 边界条件判断
         */
        if (inputs == null || inputs.isEmpty() || inputs.size() == 1) {
            return inputs;
        }

        /**
         * 第一个待合并列表
         */
        List<String> first = inputs.get(0);

        for (int i = 1; i < inputs.size(); i++) {

            first = combine(first, inputs.get(i));
        }

        /**
         * 转化为目标格式
         */
        for (String item : first) {
            outputs.add(new ArrayList<>(Arrays.asList(item.split(""))));
        }


        return outputs;

    }

    public static void main(String[] args) {

        List<List<String>> inputs1 = Arrays.asList(Arrays.asList("a","b","c"),
                Arrays.asList("1","2","3","4"),
                Arrays.asList("A","B","C"));

        List<List<String>> inputs2 = Arrays.asList(Arrays.asList("1","2","3","4"),
                Arrays.asList("a","b","c"),
                Arrays.asList("A","B","C"));

        System.out.println(getSetCombination(inputs1));

        System.out.println(getSetCombination(inputs2));

    }

    /**
     * 两个列表笛卡尔乘积
     * 
     * @param first
     * @param second
     * @return
     */
    public static List<String> combine(List<String> first, List<String> second) {
        List<String> combineRes = new ArrayList<>();
        for (String outerItem : first) {
            for (String interItem : second) {
                String combineItem = outerItem + interItem;
                combineRes.add(combineItem);
            }
        }
        return combineRes;
    }
}
