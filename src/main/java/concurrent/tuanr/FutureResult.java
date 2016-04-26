package concurrent.tuanr;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class FutureResult<K, T> implements Callable<T> {
    /**
     * 修改 collection<T>-----><T>
     */

    private FutureContext<K, T> context;

    public void setFutureContext(FutureContext<K, T> context) {
        this.context = context;

    }

    public FutureContext<K, T> getFutureContext() {
        return context;
    }

    public abstract T getResult(Future<T> future);

    @Override
    public abstract T call();

    /*
     * public void getResult(Future<T> future) { try { while (true) { if
     * (future.isDone() && !future.isCancelled()) {
     * System.err.println(future.get()); // 输出内容 break;// 结束线程 } else {
     * Thread.sleep(1000); } } } catch (Exception e) { // TODO: handle exception
     * e.printStackTrace(); } }
     */
}
