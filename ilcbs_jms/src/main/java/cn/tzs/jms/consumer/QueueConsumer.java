package cn.tzs.jms.consumer;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class QueueConsumer implements MessageListener{
    public void onMessage(Message message) {
        System.out.println("=======spring监听器接收到的消息========="+message);
    }
}
