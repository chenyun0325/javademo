package paramCheck;

import com.hsnet.pz.api.exception.ValidException;

import java.lang.reflect.Field;

/**
 * 运用抛出异常控制逻辑
 * @author: chenyun 
 * @since: 2015年3月22日 下午3:12:42 
 * @history:
 */
public class ValidateService {
    private static DV dv;

    // 解析入口
    public static void valid(Object obj) throws IllegalArgumentException,
            IllegalAccessException, ValidException {
        // 获取object类型
        Class<? extends Object> cls = obj.getClass();
        // 获取成员变量
        Field[] fields = cls.getDeclaredFields();
        // 遍历属性
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            validate(fields[i], obj);
            fields[i].setAccessible(false);
        }
    }

    /**
     * 验证单个字段
     * @param field
     * @param obj 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @create: 2015年3月22日 下午3:17:28 chenyun
     * @history:
     */
    public static void validate(Field field, Object obj)
            throws IllegalArgumentException, IllegalAccessException,
            ValidException {
        String description;// 字段描述
        Object value;// 字段值

        // 获取注解信息
        dv = field.getAnnotation(DV.class);
        value = field.get(obj);
        if (dv == null) {
            return;
        }
        description = dv.description().equals("") ? field.getName() : dv
            .description();
        /**
         * 注解解析工作开始
         */
        if (!dv.nullable()) {
            if (value == null || StringUtils.isBlank(value.toString())) {
                throw new ValidException(description + "");
            }
        }
        if (value.toString().length() > dv.maxLength() && dv.maxLength() != 0) {

            throw new ValidException(description + "" + dv.maxLength());

        }
        if (value.toString().length() < dv.minLength() && dv.minLength() != 0) {

            throw new ValidException(description + "" + dv.minLength());

        }
        if (dv.regexType() != RegexType.NONE) {
            switch (dv.regexType()) {
                case NONE:

                    break;
                case SPECIALCHAR:
                    if (RegexUtils.hasSpecialChar(value.toString())) {
                        throw new ValidException(description + "");
                    }

                    break;
                case CHINESE:
                    if (RegexUtils.isChinese2(value.toString())) {
                        throw new ValidException(description + "");
                    }
                    break;
                case EMAIL:
                    if (RegexUtils.isEmail(value.toString())) {
                        throw new ValidException(description + "");
                    }

                    break;

                case IP:
                    if (RegexUtils.isIp(value.toString())) {
                        throw new ValidException(description + "");
                    }
                    break;
                case NUMBER:
                    if (RegexUtils.isNumber(value.toString())) {
                        throw new ValidException(description + "");
                    }
                    break;
                case PHONENUMBER:
                    if (RegexUtils.isPhoneNumber(value.toString())) {
                        throw new ValidException(description + "");
                    }
                    break;

                default:
                    break;
            }
        }
        if (!dv.regexExpression().equals("")) {
            if (!value.toString().matches(dv.regexExpression())) {
                throw new ValidException(description + "");
            }
        }
    }

}
