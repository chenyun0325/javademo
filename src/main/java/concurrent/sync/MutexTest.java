package concurrent.sync;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * https://www.cnblogs.com/chengxiao/p/7141160.html
 */
public class MutexTest {

    private static CyclicBarrier barrier = new CyclicBarrier(31);

    private static int a =0;

    private static Mutex mutex = new Mutex();

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        for (int i =0;i<30;i++){
            new Thread(() -> {
                for (int i12 = 0; i12 <10000; i12++){
                    increment1();
                }
                try {
                    barrier.await();
                }catch (Exception e){

                }
            }).start();
        }
        barrier.await();
        System.out.println("加锁前，a="+a);

        barrier.reset();
        a=0;
        for (int i =0;i<30;i++){
            new Thread(() -> {
                for (int i1 = 0; i1 <10000; i1++){
                    increment2();
                }
                try {
                    barrier.await();
                }catch (Exception e){

                }
            }).start();


        }
        barrier.await();
        System.out.println("加锁后，a="+a);
    }



    static void increment1(){
        a++;
    }

    static void increment2(){
        mutex.lock();
        a++;
        mutex.unlock();
    }
}
