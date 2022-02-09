package per.zzz.security.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import per.zzz.security.annotations.ZzzAuthTag;
import per.zzz.security.annotations.ZzzAuthTagAdd;
import per.zzz.security.annotations.ZzzAuthTagSelect;
import per.zzz.security.core.SecurityContext;
import per.zzz.security.core.SecurityContextHolder;
import per.zzz.security.core.SecurityResourceRepository;

import java.util.Map;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/8 19:20
 */
@Aspect
@Component
public class SecurityAutoConfig{

    @Before("@annotation(zzzAuthTagSelect)")
    public void checkPermission(ZzzAuthTagSelect zzzAuthTagSelect) {
        SecurityContext securityContext = SecurityContextHolder.get();
        ZzzAuthTag zzzAuthTag = zzzAuthTagSelect.annotationType().getAnnotation(ZzzAuthTag.class);
        Map<ZzzAuthTag, String> cache = SecurityResourceRepository.getCache();

        System.out.println("```````````````````````````````" + zzzAuthTagSelect);
    }

}
