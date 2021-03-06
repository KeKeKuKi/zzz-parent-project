package per.zzz.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/1/17 13:08
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("per.zzz")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
