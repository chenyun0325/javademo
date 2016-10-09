package rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class ProxyFactory {
    public static <T> T create(Class<T> c,String ip,int port){
        InvocationHandler handler = new RpcProxy(ip,port,c);
        return (T) Proxy.newProxyInstance(c.getClassLoader(),new Class[]{c},handler);
    }
}
