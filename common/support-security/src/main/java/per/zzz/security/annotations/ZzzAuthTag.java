package per.zzz.security.annotations;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZzzAuthTag {
    String name();

    String value();
}
