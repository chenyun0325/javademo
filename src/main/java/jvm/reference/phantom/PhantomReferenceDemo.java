package jvm.reference.phantom;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * https://blog.csdn.net/shijiujiu33/article/details/104547837
 * https://blog.csdn.net/weixin_37695911/article/details/106836342
 *
 * -Xmx10m -Xms10m
 *
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        test3();
    }

    @Test
    public static void test0() throws InterruptedException {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        // 虚引用必须要和引用队列一起使用，他的get方法永远返回null
        PhantomReference<byte[]> phantomReference = new PhantomReference<>(
                new byte[1024 * 1024 * 5], queue);

        System.out.println(phantomReference);

        System.out.println(queue.poll());

        System.gc();

        Thread.sleep(300L);

        System.out.println(queue.poll());

        byte[] bytes = new byte[1024 * 1024 * 6];
    }



    @Test
    public static void test1() throws InterruptedException {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        // 虚引用必须要和引用队列一起使用，他的get方法永远返回null
        PhantomReference<byte[]> phantomReference = new PhantomReference<>(
                new byte[1024 * 1024 * 5], queue);

        System.out.println(phantomReference);

        System.out.println(queue.poll());

        System.gc();

        Thread.sleep(300L);

        System.out.println(queue.poll());

        // 根据JDK8的api文档介绍，将所有这样的引用被清除或者自身变得不可访问，GC才会回收
        phantomReference =null;

        byte[] bytes = new byte[1024 * 1024 * 6];
    }

    @Test
    public static void test3() throws InterruptedException {
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        SoftReference<byte[]> softReference = new SoftReference<>(
                new byte[1024 * 1024 * 5], queue);

        System.out.println(softReference);

        System.out.println(queue.poll());

        System.gc();

        Thread.sleep(5000L);

        System.out.println(queue.poll());

        byte[] bytes = new byte[1024 * 1024 * 6];

        System.out.println(queue.poll());
    }
}
