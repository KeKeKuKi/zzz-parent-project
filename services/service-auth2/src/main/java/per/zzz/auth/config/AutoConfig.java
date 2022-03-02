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
import per.zzz.security.core.SecurityResourceRepository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
            List<ZzzAuthEntity> authEntities = new ArrayList<>();
            Method[] declaredMethods = bean.getClass().getDeclaredMethods();
            for (Method method : declaredMethods) {
                Annotation[] annotations = method.getAnnotations();
                for (Annotation annotation : annotations) {
                    if(annotation.annotationType().isAssignableFrom(ZzzAuthTagCustom.class)){
                        ZzzAuthTagCustom authTagCustom = (ZzzAuthTagCustom) annotation;
                        authEntities.add(ZzzAuthEntity.builder().name(authTagCustom.name()).value(authTagCustom.value()).build());
                    }else if(isTagAnnotation(annotation)){
                        ZzzAuthTag annotation1 = annotation.annotationType().getAnnotation(ZzzAuthTag.class);
                        if (annotation1 != null){
                            authEntities.add(ZzzAuthEntity.builder().name(annotation1.name()).value(annotation1.value()).build());
                            SecurityResourceRepository.save(annotation1, "application01:" + annotation2.value() + ":" + annotation1.value());
                        }
                    }
                }
            }
            for (ZzzAuthEntity next : authEntities) {

                String name = annotation2.name() + "-" + next.getName();
                String value = "application01:" + annotation2.value() + ":" + next.getValue();
                // 保存至数据库
                savePermission(name, value);

                // 保存至本地缓存

            }
        }
    }

    private boolean isTagAnnotation(Annotation annotation){
        return annotation.annotationType().isAssignableFrom(ZzzAuthTagAdd.class)
                || annotation.annotationType().isAssignableFrom(ZzzAuthTagDel.class)
                || annotation.annotationType().isAssignableFrom(ZzzAuthTagSelect.class)
                || annotation.annotationType().isAssignableFrom(ZzzAuthTagUpdate.class);
    }

    private void savePermission(String name, String value){
        permissionService.add(PermissionDTO.builder().name(name).value(value).build());
    }

    @Data
    @Builder
    private static class ZzzAuthEntity{
        private String name;
        private String value;
    }
}
