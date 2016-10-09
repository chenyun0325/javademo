package rpc;

import java.io.Serializable;

/**
 * Created by cy111966 on 2016/7/19.
 */
public class RpcObject implements Serializable{
    private Class c;
    private String methodName;
    private Object[] args;

    public RpcObject(Class c, String methodName, Object[] args) {
        this.c = c;
        this.methodName = methodName;
        this.args = args;
    }

    public RpcObject() {
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
