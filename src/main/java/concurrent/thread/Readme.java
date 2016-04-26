package concurrent.thread;


public class Readme {
    /**
     * 1.切分及控制思路：
     *  切分思路：分治算法
     *  a.根据是否有返回值,task任务可以实现callable接口、或者将计算结果转储
     *  b.控制思路：主线程发起将任务委托给n个线程去执行，如果需要知道执行结果可
     *  采用a.用countdown计数变量来控制线程执行顺序
     *     b.如果任务实现callable接口,可以用future接口get方法通过轮训
     *     方式返回执行结果
     * 2.异常处理：
     *  a.如果任务出现不可逆情况（远程服务器不可用），任务取消和返回异常及通知
     *  b.事务补偿
     *  
     * 3.其它：
     * a. 任务可以取消,取消后返回部分计算结果.
     * b. 根据要有合理选择线程池类型.
     * 
     * 
     * 4.spring集成相关：
     * a.定义一个入口方法供spring定义任务调用
     *   interface xx;
     *   xxImp implement xx
     *   @Autowired 线程调度核心类 core
     *   
     *   class core
     *   
     *   @Autowired
     *   IRiskPZAccountBO：queryRiskPZAccount 获取用户
     *   @Autowired
     *   IUserFundSnapshotBO:getUserFundRealTime 计算单个用户风险比例
     *                       saveBatch 批量插入
     *                       
     *   @Autowired
     *   
     *   
     *   
     */

}
