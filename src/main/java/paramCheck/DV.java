package paramCheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface DV {

    // 是否可空
    boolean nullable() default false;

    int maxLength() default 0;

    int minLength() default 0;

    // 常见正则
    RegexType regexType() default RegexType.NONE;

    // 自定义正则
    String regexExpression() default "";

    // 参数或者字段描述
    String description() default "";
}
