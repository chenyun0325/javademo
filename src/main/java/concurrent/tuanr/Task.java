package concurrent.tuanr;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<ReturnType> {

    private InputType inputType;

    // 任务可以取消
    private boolean cancel = false;

    public Task(InputType inputType) {
        this.inputType = inputType;
    }

    @Override
    public ReturnType call() throws Exception {
        ReturnType returnType = new ReturnType();
        try {
            if (cancel) {
                return returnType;// 返回部分计算结果
            }
            // TODO Auto-generated method stub
            String identity = inputType.getIdentity();
            // 1.根据identity查询相关计算任务
            // 2.需要注入其他辅助对象private type xx;
            TimeUnit.SECONDS.sleep(100);

        } catch (InterruptedException e) {
            // TODO: handle exception
            Thread.currentThread().interrupt();
        }
        return returnType;

    }

    public void cancel() {
        cancel = true;
    }

}
