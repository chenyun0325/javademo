package jvm.reference.leak;

import org.junit.Test;

import java.lang.ref.ReferenceQueue;

/**
 * https://www.jianshu.com/p/7a3bd7b536e6
 */
public class ReferenceQueueTest {

    @Test
    public void test1() {
        //实例化一个引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        //弱引用持有的实际对象
        Data d = new Data();
        //实例化一个自定义弱引用，传入实际对象和引用队列
        MyRef ref = new MyRef(d, queue);
        //显示触发gc
        System.gc();

        sleep();

        System.out.println("After call gc(): ");
        System.out.println("ref.get: " + ref.get());
        System.out.println("queue.poll: " + queue.poll());
    }

    @Test
    public void test2() {
//实例化一个引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        //弱引用持有的实际对象
        Data d = new Data();
        //实例化一个自定义弱引用，传入实际对象和引用队列
        MyRef ref = new MyRef(d, queue);
        //垃圾回收之前删除所有的强引用
        d = null;

        //显示触发gc
        System.gc();

        sleep();


        System.out.println("After call gc(): ");
        System.out.println("ref.get: " + ref.get());
        System.out.println("queue.poll: " + queue.poll());
    }

    @Test
    public void test3() {
        //实例化一个引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        //弱引用持有的实际对象
        Data d = new Data();
        //实例化一个自定义弱引用，传入实际对象和引用队列
        MyRef ref = new MyRef(d, queue);

        //发生GC前手动清空弱引用持有的实际对象
        ref.clear();

        //显示触发gc
        System.gc();

        sleep();

        System.out.println("After call gc(): ");
        System.out.println("ref.get: " + ref.get());
        System.out.println("queue.poll: " + queue.poll());
    }

    public void sleep(){
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
