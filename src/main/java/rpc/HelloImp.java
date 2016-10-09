package rpc;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class HelloImp implements IHello {
    @Override
    public String sayHello(String name) {
        return "hello:"+name;
    }
}
