package concurrent.multithread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public class FutureContext<T> {
    /**
     * K---代表单个任务键值
     * T---代表当个任务返回值------------批量任务就是list<returntype>
     * 
     * 存储异步计算的结果
     * 单个存储,整体存储及整理返回
     * 后续：单个返回
     */
    private List<Future<T>> futuresList = new ArrayList<Future<T>>();

    public void addFuture(Future<T> future) {
        futuresList.add(future);

    }

    public void addFutureBatch(Collection<? extends Future<T>> collection) {
        futuresList.addAll(collection);

    }

    public List<Future<T>> getFutureList() {
        return futuresList;
    }

}
