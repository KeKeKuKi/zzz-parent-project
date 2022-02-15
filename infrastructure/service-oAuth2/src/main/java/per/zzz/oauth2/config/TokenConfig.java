package per.zzz.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/15 15:10
 */
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
