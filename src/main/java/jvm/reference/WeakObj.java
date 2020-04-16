package jvm.reference;

/**
 * Created by chenyun on 2020/4/16.
 */
public class WeakObj {
    private String a;
    private String b;

    public WeakObj(String a, String b) {
        this.a = a;
        this.b = b;
    }
    /**
     * 覆盖finalize，在回收的时候会执行。
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("a： " + a + " finalize。");
    }

    @Override
    public String toString() {
        return "obj{" +
                "a='" + a + '\'' +
                '}' + ", hashCode:" + this.hashCode();
    }
}
