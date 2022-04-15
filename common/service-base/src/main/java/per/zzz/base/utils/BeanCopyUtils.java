package per.zzz.base.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author zzz
 * @since 2020/9/24 7:27 下午
 */
public class BeanCopyUtils extends BeanUtils {
    public static <T> T copy(Object source, T target) {
        if (source == null) {
            return null;
        }
        copyProperties(source, target);
        return target;
    }
}
