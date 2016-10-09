package rpc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class Config {
    public static Map<String,Class> conf= new HashMap<>();
    static {
        conf.put("rpc.IHello",HelloImp.class);
    }
}
