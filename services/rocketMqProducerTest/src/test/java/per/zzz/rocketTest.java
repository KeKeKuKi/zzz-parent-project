package per.zzz;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
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
    public void sendTest() {
        for (int i = 0; i < 100000; i++) {
            // rocketMq 底层使用冒号分割Topic 与 tag
            // 若想要保证消息消费的顺序性需要使用 syncSendOrderly() ，且参数 hashKey 为定值（此值Hash决定使用Topic的某个队列），同时消费端设置 consumeMode = ConsumeMode.ORDERLY
            rocketMQTemplate.syncSendOrderly("spring-boot-payload:tag2", MessageBuilder.withPayload("mes" + i).build(), "15");
            System.out.println("send_success: spring-boot-payload:" + i + "hello mq!");
        }

    }
}
