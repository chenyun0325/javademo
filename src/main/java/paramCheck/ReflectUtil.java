package paramCheck;

public class ReflectUtil {

    public static Object getInstance(String classname) {
        Object result = null;
        Class cls = null;
        try {
            cls = Class.forName(classname);
            if (cls != null) {
                result = cls.newInstance();// 被代理对象
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
    }

}
