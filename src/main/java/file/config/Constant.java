package file.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Constant {

    public static int batch1;

    public static int batch2;

    public static int poolnum;

    private Map<String, PoolBean> poolMapconfig = new HashMap<String, PoolBean>();

    public static String path = "/config.properties";

    private static Properties props = null;

    public static Map<String, ExecutorService> poolMap = new HashMap<String, ExecutorService>();

    public void init() {

        props = new Properties();
        try {

            InputStream in = Constant.class.getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            /*
             * File file = new File(path); InputStream in = new
             * FileInputStream(file.getAbsolutePath());
             */
            props.load(in);
            // 需要初始化线程池数目
            if (props.getProperty("poolnum") != null) {
                poolnum = new Integer(props.getProperty("poolnum")).intValue();
            }
            // 载入线程池参数
            if (poolnum == 0) {
                throw new Exception("线程参数初始化失败");
            }
            for (int i = 1; i <= poolnum; i++) {
                PoolBean poolBean = new PoolBean();
                if (props.getProperty("pool" + i + "_type") != null) {
                    poolBean.setPooltype(props
                        .getProperty("pool" + i + "_type"));
                }
                if (props.getProperty("pool" + i + "_queuesize") != null) {
                    poolBean.setQueuesize(new Integer(props.getProperty("pool"
                            + i + "_queuesize")).intValue());
                }
                if (props.getProperty("pool" + i + "_switch") != null) {
                    poolBean.setSwitchs(props.getProperty("pool" + i
                            + "_switch"));

                }
                if (props.getProperty("pool" + i + "_corepoolsize") != null) {
                    poolBean.setCorepoolsize(new Integer(props
                        .getProperty("pool" + i + "_corepoolsize")).intValue());

                }
                if (props.getProperty("pool" + i + "_maximunpoolsize") != null) {
                    poolBean.setMaximunpoolsize(new Integer(props
                        .getProperty("pool" + i + "_maximunpoolsize"))
                        .intValue());

                }
                if (props.getProperty("pool" + i + "_keepAliveTime") != null) {
                    poolBean
                        .setKeepAliveTime(new Integer(props.getProperty("pool"
                                + i + "_keepAliveTime")).intValue());
                }
                if (props.getProperty("pool" + i + "_unit") != null) {
                    poolBean.setUnit(props.getProperty("pool" + i + "_unit"));

                }
                if (props.getProperty("pool" + i + "_queuetype") != null) {
                    poolBean.setQueueType(props.getProperty("pool" + i
                            + "_queuetype"));
                }
                if (props.getProperty("pool" + i) != null) {
                    poolBean.setPoolname(props.getProperty("pool" + i));
                }

                poolMapconfig.put("pool" + i, poolBean);
            }
            // 初始化业务参数
            batch1 = new Integer(props.getProperty("batch1", "100")).intValue();
            batch2 = new Integer(props.getProperty("batch2", "100")).intValue();
            // 构建线程池

            createpools(poolMapconfig);

            System.err.println(poolMap);
            System.err.println(poolMapconfig);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void createpools(Map<String, PoolBean> map) throws Exception {

        if (map == null) {
            throw new Exception("线程参数初始化失败");
        }
        int size = map.size();

        for (int i = 1; i <= size; i++) {
            PoolBean bean = map.get("pool" + i);
            createpool(bean);
        }
    }

    // poolbean 可以注入根据任务定制的任务队列大小queuesize
    public void createpool(PoolBean poolBean) {
        // LinkedBlockingQueue/ArrayBlockingQueue/SynchronousQueue
        String queueType = poolBean.getQueueType();
        int queuesize = poolBean.getQueuesize();
        int corepoolsize = poolBean.getCorepoolsize();

        int maximunpoolsize = poolBean.getMaximunpoolsize();
        String poolname = poolBean.getPoolname();

        // fix/cached/sigle

        String pooltype = poolBean.getPooltype();

        String switchs = poolBean.getSwitchs();

        int keepAliveTime = poolBean.getKeepAliveTime();

        String unit = poolBean.getUnit();

        if (switchs.equals("Y")) {
            BlockingQueue<Runnable> queue = null;
            if (queueType.equalsIgnoreCase("ArrayBlockingQueue")) {
                queue = new ArrayBlockingQueue<Runnable>(queuesize);
            }
            if (queueType.equalsIgnoreCase("LinkedBlockingQueue")) {
                queue = new LinkedBlockingQueue<Runnable>();
            }
            if (queueType.equalsIgnoreCase("SynchronousQueue")) {
                queue = new SynchronousQueue<Runnable>();
            }

            TimeUnit internalTimeUnit = null;
            if (unit.equalsIgnoreCase("day")) {
                internalTimeUnit = TimeUnit.DAYS;
            }
            if (unit.equalsIgnoreCase("hours")) {
                internalTimeUnit = TimeUnit.HOURS;
            }

            if (internalTimeUnit != null) {
                // 构建运行池
                ExecutorService executor = new ThreadPoolExecutor(corepoolsize,
                    maximunpoolsize, keepAliveTime, internalTimeUnit, queue);
                Constant.poolMap.put(poolname, executor);
            }

        }

    }

    /**
     * get set 方法
     * @return 
     * @create: 2014年10月29日 下午1:24:26 chenyun
     * @history:
     */

    public static int getBatch1() {
        return batch1;
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        Constant.path = path;
    }

    public static void setBatch1(int batch1) {
        Constant.batch1 = batch1;
    }

    public static int getBatch2() {
        return batch2;
    }

    public static void setBatch2(int batch2) {
        Constant.batch2 = batch2;
    }

    public Map<String, PoolBean> getPoolMapconfig() {
        return poolMapconfig;
    }

    public void setPoolMapconfig(Map<String, PoolBean> poolMapconfig) {
        this.poolMapconfig = poolMapconfig;
    }

    public static Map<String, ExecutorService> getPoolMap() {
        return poolMap;
    }

    public static void setPoolMap(Map<String, ExecutorService> poolMap) {
        Constant.poolMap = poolMap;
    }

    public static void close() {
        Iterator<ExecutorService> iterator = poolMap.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().shutdownNow();
        }
    }

}
