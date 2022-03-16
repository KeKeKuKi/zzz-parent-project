package per.zzz.rocketMq.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Component;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/15 10:48
 */
@Component
@RocketMQMessageListener(topic = "spring-boot-payload", consumerGroup = "${rocketmq.producer.group}")
public class TestListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("收到消息：" + s);
    }
}
