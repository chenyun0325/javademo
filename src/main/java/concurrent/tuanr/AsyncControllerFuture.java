package concurrent.tuanr;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncControllerFuture {
    // 线程池
    private ExecutorService executorService;

    // 保存异步执行结果

    private FutureContext<String, List<ReturnType>> context;

    // 构造方法
    public AsyncControllerFuture() {
        this.executorService = Executors.newFixedThreadPool(10);
        this.context = new FutureContext<String, List<ReturnType>>();

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // 启动异步计算
        AsyncControllerFuture controllerFuture = new AsyncControllerFuture();
        controllerFuture.startAsync();

        // 启动异步计算结果输出线程，该线程扫描异步计算Futrue的状态，如果已经完成，则输出异步计算结果
        ListFutureResult scan = new ListFutureResult();
        scan.setFutureContext(controllerFuture.getfuturecontext());
    }

    // 开始任务切割与计算
    public void startAsync() {
        // 1.总量，每批大小,多少批
        int total = 10;
        int batch = 3;
        int num = (int) Math.ceil(total / batch);
        // 初始化线程池大小
        for (int i = 1; i <= num; i++) {
            int begin = (i - 1) * batch + 1;
            int end = i * batch;
            // 构建任务
            BatchTask batchTask = new BatchTask(begin, end);
            // 委托任务
            Future<List<ReturnType>> batchFuture = this.executorService
                .submit(batchTask);

            // 批量保存异步计算结果Future,需要线程去轮询结果
            this.context.addFuture(batchFuture);

        }
    }

    // 返回计算结果
    public FutureContext<String, List<ReturnType>> getfuturecontext() {
        return this.context;
    }

}
