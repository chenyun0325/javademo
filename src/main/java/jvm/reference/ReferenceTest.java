package jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        //创建一个引用队列
        ReferenceQueue queue = new ReferenceQueue();
        System.out.println(queue);

// 创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        WeakReference reference = new WeakReference(new Object(), queue);
        System.out.println(reference);
// 当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        System.out.println(reference.get());
        System.gc();

//        reference.clear();

//        Reference pollRef = queue.poll();
//        System.out.println(pollRef);

        Reference reference1 = queue.remove();
        System.out.println(reference1);

    }
}
