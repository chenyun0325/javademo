package jvm.base;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * Created by chenyun on 2020/4/12.
 */
public class WeakTest {

    public static void main(String[] args) throws InterruptedException {
        Cache<String,Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .weakValues()
                .build();
        Object value = new Object();
        System.err.println(value);
        cache.put("key1",value);
        Object valueRef = value;
        value = new Object();//原对象不再有强引用
        System.gc();
        System.out.println(valueRef);
        System.out.println(value);
        System.out.println(cache.getIfPresent("key1"));
    }
}