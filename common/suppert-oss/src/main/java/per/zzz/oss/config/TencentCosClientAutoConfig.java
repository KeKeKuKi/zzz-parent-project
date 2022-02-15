package per.zzz.oss.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.zzz.oss.utils.CosFileUtils;

/**
 * date 2021/10/28 13:26
 *
 * @author 阿忠 2669918628@qq.com
 */
@Configuration
@EnableConfigurationProperties(TencentCosConfig.class)
public class TencentCosClientAutoConfig {

    private final TencentCosConfig tencentCosConfig;

    public TencentCosClientAutoConfig(TencentCosConfig tencentCosConfig) {
        this.tencentCosConfig = tencentCosConfig;
    }

    @Bean
    public COSClient cosClient() {
        COSCredentials cred = new BasicCOSCredentials(tencentCosConfig.getSecretId(), tencentCosConfig.getSecretKey());
        Region region = new Region("ap-chengdu");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    @Bean
    public CosFileUtils cosFileUtils() {
        return new CosFileUtils(tencentCosConfig, cosClient());
    }
}
