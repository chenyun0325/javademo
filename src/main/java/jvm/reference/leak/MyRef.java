package jvm.reference.leak;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class MyRef extends WeakReference<Data> {

    private int id;
    public MyRef(Data referent, ReferenceQueue<? super Data> q) {
        super(referent, q);
        this.id = referent.getId();
    }

    @Override
    public String toString() {
        return "myRef:"+id;
    }
}
