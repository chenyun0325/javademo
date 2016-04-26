package concurrent.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadTest {

    /** 
     * @param args 
     * @create: 2014-10-20 上午10:15:40 chenyun
     * @history: 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            // synctest();
            // callabletest();
            // completionServiceTest();
            countdownlatchtest();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    /**
     * 测试多个线程共同修改一个List
     * @throws InterruptedException 
     * @create: 2014-10-20 上午10:43:33 chenyun
     * @history:
     */

    public static void synctest() throws InterruptedException {
        // 定义不同线程的临时存储结果
        final List<Integer> list = new ArrayList<Integer>();
        Thread thread1 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start.");
                Random random = new Random(100);
                synchronized (list) {
                    for (int i = 0; i < 4; i++) {
                        list.add(new Integer(random.nextInt()));
                    }
                    System.out.println("size of list:" + list.size());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end.");
            }
        };

        Thread thread2 = new Thread() {
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start.");
                Random random = new Random(100);
                synchronized (list) {
                    for (int i = 0; i < 4; i++) {
                        list.add(new Integer(random.nextInt()));
                    }
                    System.out.println("size of list:" + list.size());
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "end.");
            }
        };

        // 启动线程
        thread1.start();
        thread2.start();
        // 合并结果
        thread1.join();
        thread2.join();
        System.err.println(list.size());
        System.err.println(list);

    }

    public static void callabletest() {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Callable<String> call = new Callable<String>() {

            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                return "hello";
            }
        };
        // 执行任务，返回结果
        Future<String> result = exec.submit(call);
        try {
            System.out.println("线程返回值:" + result.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        exec.shutdown();

    }

    public static void completionServiceTest() throws InterruptedException,
            ExecutionException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        CompletionService<String> service = new ExecutorCompletionService<String>(
            exec);
        for (int i = 0; i < 10; i++) {
            // 每个任务
            Callable<String> call = new Callable<String>() {

                @Override
                public String call() throws Exception {
                    // TODO Auto-generated method stub
                    return Thread.currentThread().getName();
                }
            };
            service.submit(call);
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            Future<String> result = service.take();
            System.out.println("线程返回值：" + result.get());
        }
        exec.shutdown();
    }

    public static void countdownlatchtest() throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(5);
        ExecutorService exec = Executors.newFixedThreadPool(5);
        // 执行5个任务
        for (int i = 0; i < 50; i++) {
            Runnable runner = new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Random r = new Random();
                    try {
                        System.err.println("xxxy");
                        begin.await();// 1. 在子线程中让主线程begin.countDown之前代码先执行
                                      // 2.挂起当前线程
                        System.out.println(Thread.currentThread().getName()
                                + "开始运行");
                        Thread.sleep(r.nextInt(10) * 1000);
                        System.out.println(Thread.currentThread().getName()
                                + "结束运行");

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    } finally {
                        end.countDown();
                    }

                }
            };
            exec.submit(runner);
        }
        System.err.println("主线程先于子线程代码");
        begin.countDown();
        end.await();// 等待其它线程结束
        System.err.println(Thread.currentThread().getName() + "运行结束");
        exec.shutdown();

    }
}
