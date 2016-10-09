package rpc;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class RpcClient {

    public static void main(String[] args) {
        String ip ="30.33.48.190";
        int port =9001;
        IHello iHello = ProxyFactory.create(IHello.class, ip, port);
        System.out.println(iHello.sayHello("cy"));
    }
}
