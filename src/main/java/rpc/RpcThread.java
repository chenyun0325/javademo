package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by cy111966 on 2016/7/19.
 * rpc调用处理线程
 */
public class RpcThread extends Thread {
    private Socket s;

    public RpcThread(Socket socket){
        this.s=socket;
    }

    public void run(){
        ObjectInputStream is=null;
        ObjectOutputStream os=null;
        try {
            //获取输入
            is = new ObjectInputStream(s.getInputStream());
            //获取远程调用参数
            RpcObject rpcObject= (RpcObject) is.readObject();

            //获取接口
            Class interfaceClass = rpcObject.getC();
            //获取接口实现类实例
            Object o = getObject(interfaceClass);

            //反射执行
            Object res = executeMethod(o, rpcObject.getMethodName(), rpcObject.getArgs());
            //输出返回值
           os= new ObjectOutputStream(s.getOutputStream());
            os.writeObject(res);
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private Object getObject(Class c){
        Object obj=null;
        try {
          obj=  Config.conf.get(c.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
      return obj;
    }

    private Object executeMethod(Object o,String methodName,Object[] args){
        Object res= null;
        Class[] cs = new Class[args.length];
        for (int i=0;i<args.length;i++) {
            cs[i]=args[i].getClass();
        }
        try {
            Method method = o.getClass().getMethod(methodName, cs);
            res=method.invoke(o,args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

}
