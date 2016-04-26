package jvm;

public class SingleInstanceTest implements Runnable {
    private static int static_i;// 静态变量

    private SingleInstanceTest test = null;

    /** 
     * @param args 
     * @create: 2014-10-14 下午4:14:04 chenyun
     * @history: 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // StaticInstanceTest t = new StaticInstanceTest();// 实例化一个任务
        // 模拟多线程
        SingleInstanceTest test = new SingleInstanceTest();
        System.err.println(test);
        for (int i = 0; i < 10; i++) {
            new Thread(test.getinstance(), "线程" + i).start();
            // new Thread(new StaticInstanceTest(), "线程" + i).start();
            // 不同的线程去运行多个不同的任务，由于static变量不同实例共享，所以会出现共享问题。

        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        static_i = 4;
        System.out.println("[" + Thread.currentThread().getName()
                + "]获取static_i 的值:" + static_i);
        static_i = 10;
        System.out.println("[" + Thread.currentThread().getName()
                + "]获取static_i*2 的值:" + static_i * 2);

    }

    // 单例模式
    public SingleInstanceTest getinstance() {
        if (test == null) {
            test = new SingleInstanceTest();
        }
        System.err.println(test);
        return test;
    }

}
