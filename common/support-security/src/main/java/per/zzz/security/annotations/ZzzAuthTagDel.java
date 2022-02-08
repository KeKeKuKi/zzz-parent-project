package per.zzz.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/8 16:31
 *
 * 删除权限注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZzzAuthTagDel {
    String name() default "删除";

    String value() default "delete";
}
