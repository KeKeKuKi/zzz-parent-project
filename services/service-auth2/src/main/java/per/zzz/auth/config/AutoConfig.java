package per.zzz.auth.config;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import per.zzz.auth.dto.permission.PermissionDTO;
import per.zzz.auth.service.PermissionService;
import per.zzz.security.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/8 15:51
 */
@Component
@Slf4j
public class AutoConfig implements BeanPostProcessor {

    private final PermissionService permissionService;

    public AutoConfig(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 初始化权限信息
        initAuth(bean, beanName);
        return bean;
    }

    private void initAuth(Object bean, String beanName){
        ZzzAuthGroup annotation2 = bean.getClass().getAnnotation(ZzzAuthGroup.class);
        if(Objects.nonNull(annotation2)){
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    ZzzAuthEntity tagAnnotation = isTagAnnotation(annotation);
                    if (tagAnnotation != null){
                        String system = "application01";
                        String group = annotation2.value();
                        String tag = tagAnnotation.getValue();
                        String permission = system + ":" + group + ":" + tag;
                        String permissionName = annotation2.name() + "-" + tagAnnotation.getName();
                        savePermission(permission, permissionName);
                    }
                }

            }
        }
    }

    private ZzzAuthEntity isTagAnnotation(Annotation annotation){
        if(annotation.annotationType().isAssignableFrom(ZzzAuthTagAdd.class)){
            ZzzAuthTagAdd a = (ZzzAuthTagAdd) annotation;
            return ZzzAuthEntity.builder().name(a.name()).value(a.value()).build();
        }
        if(annotation.annotationType().isAssignableFrom(ZzzAuthTagDel.class)){
            ZzzAuthTagDel a = (ZzzAuthTagDel) annotation;
            return ZzzAuthEntity.builder().name(a.name()).value(a.value()).build();
        }
        if(annotation.annotationType().isAssignableFrom(ZzzAuthTagUpdate.class)){
            ZzzAuthTagUpdate a = (ZzzAuthTagUpdate) annotation;
            return ZzzAuthEntity.builder().name(a.name()).value(a.value()).build();
        }
        if(annotation.annotationType().isAssignableFrom(ZzzAuthTagSelect.class)){
            ZzzAuthTagSelect a = (ZzzAuthTagSelect) annotation;
            return ZzzAuthEntity.builder().name(a.name()).value(a.value()).build();
        }
        if(annotation.annotationType().isAssignableFrom(ZzzAuthTagCustom.class)){
            ZzzAuthTagCustom a = (ZzzAuthTagCustom) annotation;
            return ZzzAuthEntity.builder().name(a.name()).value(a.value()).build();
        }

        return null;
    }

    private void savePermission(String value, String name){
        permissionService.add(PermissionDTO.builder().name(name).value(value).build());
    }

    @Data
    @Builder
    private static class ZzzAuthEntity{
        private String name;

        private String value;
    }
}
