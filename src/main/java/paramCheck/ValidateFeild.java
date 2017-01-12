package paramCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateFeild {

    /**
     * 参数索引位置
     */
    int index() default -1;

    /** 
     * 如果参数是基本数据类型或String ，就不用指定该参数，如果参数是对象，要验证对象里面某个属性，就用该参数指定属性名 
     */
    String feildname() default "";

    // 是否可空
    boolean nullable() default false;

    int maxLength() default 0;

    int minLength() default 0;

    /** 
     *最大值 ，用于验证数字类型数据 
     */
    public int maxVal() default -1;

    /** 
     *最小值 ，用于验证数值类型数据 
     */
    public int minVal() default -1;

    // 常见正则
    RegexType regexType() default RegexType.NONE;

    // 自定义正则
    String regexExpression() default "";

}
