package concurrent.tuanr;

import java.util.*;
import java.util.concurrent.Future;

public class FutureContext<K, T> {
    /**
     * K---代表单个任务键值
     * T---代表当个任务返回值
     * 
     * 存储异步计算的结果
     * 单个存储,整体存储及整理返回
     * 后续：单个返回
     */
    private List<Future<T>> futuresList = new ArrayList<Future<T>>();

    private Map<K, Future<T>> map = new HashMap<K, Future<T>>();

    public void addFuture(Future<T> future) {
        futuresList.add(future);

    }

    public void addFutureBatch(Collection<? extends Future<T>> collection) {
        futuresList.addAll(collection);

    }

    public List<Future<T>> getFutureList() {
        return futuresList;
    }

    public void addFutureMap(K k, Future<T> future) {
        map.put(k, future);
    }

    public void addFutureMapBatch(Map<? extends K, ? extends Future<T>> map) {
        this.map.putAll(map);
    }

    public Map<K, Future<T>> getFutureMap() {
        return map;
    }

}
