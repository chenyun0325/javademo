package concurrent.tuanr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class ListFutureResult extends FutureResult<String, List<ReturnType>> {

    @Override
    public List<ReturnType> getResult(Future<List<ReturnType>> future) {
        // TODO Auto-generated method stub
        List<ReturnType> list = new ArrayList<ReturnType>();
        try {
            // 轮训输出结果
            while (true) {
                if (future.isDone() && !future.isCancelled()) {// 表明任务完成
                    System.err.println("future:" + future + "result:"
                            + future.get());
                    list.addAll(future.get());
                    break;
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }

    @Override
    public List<ReturnType> call() {
        // TODO Auto-generated method stub
        System.err.println("start");
        List<ReturnType> resultlist = new ArrayList<ReturnType>();
        // 批量返回异步线程future对象
        List<Future<List<ReturnType>>> list = this.getFutureContext()
            .getFutureList();
        for (Future<List<ReturnType>> ele : list) {
            List<ReturnType> tempt = (List<ReturnType>) getResult(ele);
            resultlist.addAll(tempt);
        }
        return resultlist;
    }

}
