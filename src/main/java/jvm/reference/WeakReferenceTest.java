package jvm.reference;

import java.lang.ref.WeakReference;

/**
 * Created by chenyun on 2020/4/16.
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
//        WeakObj obj = new WeakObj("a", "b");
        WeakReference<WeakObj> objWeakRef = new WeakReference<>(new WeakObj("a", "b"));
//        int i =0;

        System.out.println(objWeakRef.get());

        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(objWeakRef.get());

//        /**
//         * while 循环之后 obj 是否被回收?
//         */
//        while (true){
//            if (objWeakRef.get()!=null) {
//                i++;
//                System.out.println("Object is alive for "+i+" loops - "+objWeakRef);
//            }else {
//                System.out.println("Object has been collected.");
//                break;
//            }
//        }
    }
}
