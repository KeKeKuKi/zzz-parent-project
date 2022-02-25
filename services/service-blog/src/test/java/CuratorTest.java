import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;
import per.zzz.blog.BlogServerApplication;
import per.zzz.zookeeper.CuratorService;

import javax.annotation.Resource;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/2/24 17:55
 */
@SpringBootTest(classes = BlogServerApplication.class)
public class CuratorTest {
    @Resource
    public CuratorFramework curatorFramework;

    @Test
    public void testCurator() throws Exception {
        System.out.println(curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/node","data".getBytes()));
    }
}
