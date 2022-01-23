package algorithm.skiplist;

import java.util.concurrent.ConcurrentSkipListMap;

public class SkipListTest {
    public static void main(String[] args) {
//        ConcurrentSkipListMap<String, Long> map = new ConcurrentSkipListMap<>();
//        map.put("a", 1l);
//        map.put("c", 2l);
//        map.put("b", 3l);
//        for(String entry : map.descendingKeySet()) {
//            System.out.println(entry);
//        }
//        System.out.println(map);

        ConcurrentSkipListMap<SortableObject, Long> sortedPcuAnchors = new ConcurrentSkipListMap<>();

        sortedPcuAnchors.put(new SortableObject("a",5l),5l);
        sortedPcuAnchors.put(new SortableObject("b",5l),5l);
        sortedPcuAnchors.put(new SortableObject("c",3l),3l);
        sortedPcuAnchors.put(new SortableObject("d",31l),31l);
        for (SortableObject sortableObject : sortedPcuAnchors.descendingKeySet()) {
            System.out.println(sortableObject);
        }
        System.out.println(sortedPcuAnchors);
    }

    public static class SortableObject implements Comparable{

        /**
         * 主播ID
         */
        public String id;
        /**
         * 在线观看该主播直播的用户数（score是类似 redis sorted set 的 score 概念）
         */
        public Long score;

        public SortableObject(String id, Long score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Object obj) {
            if (obj == null || !(obj instanceof SortableObject)) {
                return 1;
            }
            SortableObject o2 = (SortableObject) obj;
            int d = o2.score.compareTo(score);
            if (d == 0) {
                d = id.compareTo(o2.id);
            }
            return d;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SortableObject) {
                SortableObject o2 = (SortableObject) obj;
                boolean eq = score.equals(o2.score);
                if (eq) {
                    eq = id.equals(o2.id);
                }
                return eq;
            }
            return false;
        }

        @Override
        public String toString() {
            return "[" + id + ": " + score + "]";
        }
    }
}
