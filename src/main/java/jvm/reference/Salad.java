package jvm.reference;

import java.lang.ref.WeakReference;

/**
 * Created by chenyun on 2020/4/16.
 */
public class Salad extends WeakReference<Apple> {
    public Salad(Apple referent) {
        super(referent);
    }
}
