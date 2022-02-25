package per.zzz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/24 14:33
 */
@Configuration
@EnableConfigurationProperties(WrapperZK.class)
public class CuratorAutoConfig {
    private final WrapperZK wrapperZK;

    public CuratorAutoConfig(WrapperZK wrapperZK) {
        this.wrapperZK = wrapperZK;
    }

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        return CuratorFrameworkFactory.newClient(
                wrapperZK.getConnectString(),
                wrapperZK.getSessionTimeoutMs(),
                wrapperZK.getConnectionTimeoutMs(),
                new RetryNTimes(wrapperZK.getRetryCount(), wrapperZK.getElapsedTimeMs())
        );
    }
}
