package concurrent.ref;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface Dispatcher {

    public void dispatch(Runnable r);

    public <T> Future<T> dispatch(Callable<T> r);

    public void stop();

}
