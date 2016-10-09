package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by cy111966 on 2016/7/19.
 * * 客户端接口代理
 * 当客户端接口方法被调用的时候，把方法名，方法参数作为参数。
 * 传送给远程服务执行，然后获取返回值
 */
public class RpcProxy implements InvocationHandler,Serializable {
    private String ip;
    private int port;
    private Class c;

    public RpcProxy(String ip, int port, Class c) {
        this.ip = ip;
        this.port = port;
        this.c = c;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o=null;//返回值
        //通过socket调用远程服务
        Socket s = new Socket(ip, port);
        //组装rpcObject
        RpcObject rpcObject = new RpcObject(c, method.getName(), args);
        ObjectOutputStream os =null;
        ObjectInputStream is = null;
        try {
            //发送数据
            os = new ObjectOutputStream(s.getOutputStream());
            os.writeObject(rpcObject);
            os.flush();
            //从远程获取数据
            is = new ObjectInputStream(s.getInputStream());
            o = is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            os.close();
            is.close();
        }
        return o;
    }
}
