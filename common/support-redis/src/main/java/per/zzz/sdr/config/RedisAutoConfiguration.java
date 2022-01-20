package per.zzz.sdr.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import per.zzz.sdr.service.CacheService;
import per.zzz.sdr.service.RedisTemplateCacheServiceImpl;


/**
 * date 2021/11/4 11:41
 *
 * @author 阿忠 2669918628@qq.com
 */
@Slf4j
@Configuration
public class RedisAutoConfiguration {

    @Bean
    @ConditionalOnBean(StringRedisTemplate.class)
    public CacheService redisTemplateCacheServiceImpl(StringRedisTemplate stringRedisTemplate) {
        log.info(">>>>>> RedisTemplateCacheServiceImpl init");
        return new RedisTemplateCacheServiceImpl(stringRedisTemplate);
    }
}
