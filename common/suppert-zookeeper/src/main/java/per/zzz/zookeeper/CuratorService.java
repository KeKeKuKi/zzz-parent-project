package per.zzz.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/24 17:00
 */
@Component
public class CuratorService {
    @Resource
    public CuratorFramework curatorFramework;

    public String createNode(String node,String data, CreateMode createMode ) throws Exception {
        return curatorFramework.create().withMode(createMode).forPath(node,data.getBytes());
    }

}
