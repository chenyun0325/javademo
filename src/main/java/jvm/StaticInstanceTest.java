package jvm;

public class StaticInstanceTest implements Runnable {
    private static int static_i;// 静态变量

    /** 
     * @param args 
     * @create: 2014-10-14 下午4:14:04 chenyun
     * @history: 
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        StaticInstanceTest t = new StaticInstanceTest();// 实例化一个任务
        // 模拟多线程
        for (int i = 0; i < 3000; i++) {
            new Thread(t, "线程" + i).start();
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

}
