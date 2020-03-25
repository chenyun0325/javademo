package concurrent.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by chenyun on 2018/12/26.
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int i = 0;
                    while (true) {
                        System.out.println("xxxxxx:"+i);
                        Thread.sleep(10000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        System.out.println("main");
    }
}
