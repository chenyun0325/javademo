package jvm.threadLeak;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class JVMDemoTest {
    public static void main(String[] args) throws Exception {
        JVMDemoTest t = new JVMDemoTest();
        while (true) {
            Thread.sleep(5000);
            t.test();
        }
    }

    private void test() {
        for (int i = 0; i < 10; i++) {
            Executor mExecutors = Executors.newFixedThreadPool(3);
            for (int j = 0; j < 3; j++) {
                mExecutors.execute(() -> System.out.println("execute"));
            }
        }
    }
}
