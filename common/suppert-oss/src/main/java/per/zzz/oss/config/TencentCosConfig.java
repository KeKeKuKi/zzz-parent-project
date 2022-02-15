package per.zzz.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * date 2021/10/28 14:12
 *
 * @author 阿忠 2669918628@qq.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "qcloud")
public class TencentCosConfig {
    private String secretId;

    private String secretKey;

    private String bucket;
}
