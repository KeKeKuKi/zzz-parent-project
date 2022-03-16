package per.zzz;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import per.zzz.rocketMq.RocketMqTestApplication;

import javax.annotation.Resource;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/14 9:58
 */
@SpringBootTest(classes = {RocketMqTestApplication.class})
public class rocketTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendTest(){
        for (int i = 0; i < 100000; i++) {
            rocketMQTemplate.convertAndSend("spring-boot-payload", i+"hello mq!");
            System.out.println("send_success: spring-boot-payload:"+i+"hello mq!");
            try {
                Thread.sleep((int)(Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
