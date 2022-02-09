package per.zzz.security.core;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/9 10:55
 */
@Builder
@Data
public class SecurityContext {
    private final String token;

    private final Set<String> permissions;
}
