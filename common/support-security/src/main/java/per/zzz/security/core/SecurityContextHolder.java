package per.zzz.security.core;

import java.util.HashSet;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/9 10:58
 */
public class SecurityContextHolder {
    private static final InheritableThreadLocal<SecurityContext> SECURITY_CONTEXT_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static SecurityContext get() {
        SecurityContext securityContext = SECURITY_CONTEXT_INHERITABLE_THREAD_LOCAL.get();
        if (securityContext == null) {
            return SecurityContext.builder().permissions(new HashSet<>(0)).build();
        }

        return securityContext;
    }

    public static void set(SecurityContext securityContext) {
        SECURITY_CONTEXT_INHERITABLE_THREAD_LOCAL.set(securityContext);
    }

    public static void clear() {
        SECURITY_CONTEXT_INHERITABLE_THREAD_LOCAL.remove();
    }
}
