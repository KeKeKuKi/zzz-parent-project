package per.zzz.mybatis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author HuSen
 * @since 2020/10/20 3:23 下午
 */
@Data
@ConfigurationProperties(prefix = "com.openform.db")
public class DbProperties {

    private String config;
    private String group = "COMMON";
}
