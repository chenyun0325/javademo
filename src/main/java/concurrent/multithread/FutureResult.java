package concurrent.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class FutureResult<T> implements Callable<T> {

    private FutureContext<T> context;

    public void setFutureContext(FutureContext<T> context) {
        this.context = context;

    }

    public FutureContext<T> getFutureContext() {
        return context;
    }

    public abstract T getResult(Future<T> future);

    @Override
    public abstract T call();

}
