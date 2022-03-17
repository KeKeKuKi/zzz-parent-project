package per.zzz.rocketMq.consumer.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import per.zzz.rocketMq.common.MessageDestination;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/15 10:48
 */
@Component
@RocketMQMessageListener(topic = MessageDestination.Topics.TopicOne, consumerGroup = "group1",selectorExpression = MessageDestination.Tags.Tag1)
public class TestListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("TestListener1 收到消息：" + s);
    }
}
