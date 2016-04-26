package concurrent.tuanr;

import java.util.List;
import java.util.concurrent.Callable;

public class BatchTask implements Callable<List<ReturnType>> {

    private List<InputType> inputTypelList;

    // 任务可以取消
    private boolean cancel = false;

    private int begin;

    private int end;

    public BatchTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public List<ReturnType> call() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public void cancel() {
        cancel = true;
    }

}
