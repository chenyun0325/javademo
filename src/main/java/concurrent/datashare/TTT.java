package concurrent.datashare;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TTT {
    private static Map<Long, Bean> map = new ConcurrentHashMap<Long, Bean>();

    /** 
     * @param args 
     * @create: 2014-10-20 下午3:27:17 chenyun
     * @history: 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        for (int i = 0; i < 100000; i++) {
            // 建立n个任务对象
            Thread tt = new Thread(new task());
            tt.start();
        }

    }

    // 定义内部任务类
    static class task implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            test();
        }

        public void test() {
            TTT ttt = new TTT();
            ttt.set1();
            String s1 = map.get(1L).getStatus();
            ttt.set2();
            String s2 = map.get(1L).getStatus();
            if (s1.equals(s2)) {
                System.err.println(s1 + ":" + s2);
            }

        }

    }

    // 对map修改一
    public void set1() {
        Bean bean1 = new Bean();
        bean1.setStatus("1");
        map.put(1L, bean1);
    }

    // 对map修改二
    public void set2() {
        Bean bean2 = new Bean();
        bean2.setStatus("2");
        map.put(1L, bean2);
    }

}
