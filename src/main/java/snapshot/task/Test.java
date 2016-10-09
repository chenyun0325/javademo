package snapshot.task;

/**
 * JAVA线程根据给定URL生成网页快照
 * User: Administrator
 * Date: 13-10-31
 * Time: 下午10:45
 * To change this template use File | Settings | File Templates.
 */


import snapshot.model.Page;

import java.util.Timer;

public class Test {
    private TaskQueue<Page> queue;
    private Timer timer;

    protected void setUp() {
        queue = new TaskQueue<Page>();
        //queue.put(new Page("xyz", "http://www.baidu.com/"));
        queue.put(new Page("wy", "http://www.163.com/"));
        //queue.put(new Page("xxx","https://item.taobao.com/item.htm?id=534354362822"));
    }

    public void setRun() {
        timer = new Timer();
        SnapshotProcessorDeamon deamon = new SnapshotProcessorDeamon();
        deamon.setAvgmax(2);
        //File f = new File(this.getClass().getResource("/").getPath()); // 获取类加载的根路径 例如：D:/IdeaProjects/cdby_wan/WebRoot/WEB-INF/classes
        //deamon.setExec(f.toString().replaceAll("\\\\","/")+"/IECapt.exe");
        deamon.setExec("c:\\IECapt.exe");
        deamon.setMax(5);
        deamon.setImageRoot("c:/snapshot/");
        deamon.setTaskQueue(queue);
        deamon.setTime(1000l);
        deamon.setTimeout(200000l);
        timer.schedule(deamon, 10000, 10000);
        do {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (deamon.getProcessorCount() > 0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("开始时间：" + start);
        Test t = new Test();
        t.setUp();
        t.setRun();
        long end = System.currentTimeMillis();
        System.out.println("结束时间：" + end);
        System.out.println("花费时间：" + (end - start) / 1000 + "秒");
    }
}