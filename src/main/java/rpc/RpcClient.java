package rpc;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class RpcClient {

    public static void main(String[] args) {
        String ip ="10.201.69.48";
        int port =9001;
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IHello iHello = ProxyFactory.create(IHello.class, ip, port);
        System.out.println(iHello.sayHello("cy"));
    }
}
