/**
 * 文件名：Delegate.java
 *
 * 版本信息：
 * 日期：2014-6-27
 * Copyright chenyun 2014 
 * 版权所有
 *
 */
package annotations;

import java.lang.annotation.*;

/**
 * 
 * 项目名称：portal 02
 * 类名称：Delegate
 * 类描述：
 * 创建人：chenyun
 * 创建时间：2014-6-27 下午01:10:14
 * 修改人：chenyun
 * 修改时间：2014-6-27 下午01:10:14
 * 修改备注：
 * @version 
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Delegate {
String interfacename();
String methodname();
Policy policy();
}
