package paramCheck;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 验证注解处理类
 * @author: chenyun 
 * @since: 2015年3月22日 下午3:52:34 
 * @history:
 */

@Component
@Aspect
public class ValidateAspectHande {

    /**
     * 1.使用aop对使用了标签的方法进行代理校验
     * 2.返回值的根据不同的要求可以进行调整
     * 
     * @param pjp
     * @return 
     * @throws Throwable 
     * @create: 2015年3月22日 下午3:59:49 chenyun
     * @history:
     */
    @Around("@annotation(com.hsnet.pz.api.tool.ValidateGroup)")
    public Object validateAround(ProceedingJoinPoint pjp) throws Throwable {
        boolean flag = false;
        ValidateGroup vanGroup = null;// 方法注解
        Object[] args = null;// 方法的执行参数
        Method method = null;// 被拦截的方法
        Object target = null;
        String methodname = null;
        try {
            target = pjp.getTarget();
            methodname = pjp.getSignature().getName();
            method = getMethodByClassAndName(pjp.getClass(), methodname);
            args = pjp.getArgs();
            // 获取注解对象
            vanGroup = (ValidateGroup) getAnnotationByMethod(method,
                ValidateGroup.class);
            flag = validateFiled(vanGroup.feilds(), args);
        } catch (Exception e) {
            flag = false;
        } finally {
            if (flag) {
                System.err.println("验证通过");
                return pjp.proceed();
            } else { // 异常统一处理
                Class returntypeClass = method.getReturnType();
                if (returntypeClass == String.class) {
                    return "";
                } else if (returntypeClass == Object.class) {
                    return "";
                } else {
                    return null;
                }
            }
        }

    }

    /**
     * 验证参数合法性
     * @param feilds
     * @param args
     * @return 
     * @create: 2015年3月22日 下午4:06:55 chenyun
     * @history:
     */
    public boolean validateFiled(ValidateFeild[] feilds, Object[] args) {
        for (ValidateFeild feild : feilds) {
            Object attrvalue = null;// 属性的值
            if ("".equals(feild.feildname())) {
                attrvalue = args[feild.index()];
            } else {
                attrvalue = getFieldByObjectAndName(args[feild.index()],
                    feild.feildname());
            }
            /**
             * 校验规则判断
             */
            if (!feild.nullable()) {
                if (attrvalue == null) {
                    return false;
                }
            } else { // 如果该参数能够为空，并且当参数为空时，就不用判断后面的了 ，直接返回true
                if (attrvalue == null) {
                    return true;
                }
            }

        }
        return false;

    }

    /**
     * 运用异常控制逻辑
     * @param args
     * @param feilds 
     * @create: 2015年3月22日 下午4:08:43 chenyun
     * @history:
     */
    public void validateFiled(Object[] args, ValidateFeild[] feilds) {

    }

    /** 
     * 根据对象和属性名得到 属性----的值
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    public Object getFieldByObjectAndName(Object targetObj, String fieldname) {
        String tmp[] = fieldname.split("\\.");// ?
        Object arg = targetObj;
        for (int i = 0; i < tmp.length; i++) {
            Method method;
            try {
                method = arg.getClass().getMethod(
                    getGetterNameByFieldname(fieldname), null);
                arg = method.invoke(arg, null);
            } catch (NoSuchMethodException | SecurityException
                    | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return arg;
    }

    /** 
     * 根据属性名 得到该属性的getter方法名 
     */
    public String getGetterNameByFieldname(String fieldname) {

        return "get" + fieldname.substring(0, 1).toUpperCase()
                + fieldname.substring(1);
    }

    /** 
     * 根据目标方法和注解类型  得到该目标方法的指定注解 
     */
    public Annotation getAnnotationByMethod(Method method, Class annoClass) {
        Annotation all[] = method.getAnnotations();
        for (Annotation annotation : all) {
            if (annotation.annotationType() == annoClass) {
                return annotation;
            }
        }
        return null;
    }

    /** 
     * 根据类和方法名得到方法 
     */
    public Method getMethodByClassAndName(Class c, String methodName) {
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
    }

}
