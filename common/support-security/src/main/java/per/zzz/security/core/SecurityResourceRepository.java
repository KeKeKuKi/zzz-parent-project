package per.zzz.security.core;

import org.springframework.lang.NonNull;
import per.zzz.security.annotations.ZzzAuthTag;

import java.util.HashMap;
import java.util.Map;

public class SecurityResourceRepository {

    private static final Map<ZzzAuthTag, String> CACHE = new HashMap<>(16);

    public static void save(ZzzAuthTag name, @NonNull String fullScope) {
        CACHE.putIfAbsent(name, fullScope);
    }

    @NonNull
    public static String get(ZzzAuthTag name) {
        return CACHE.get(name);
    }

    public static Map<ZzzAuthTag, String> getCache(){
        return CACHE;
    }
}
