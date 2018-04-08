package cn.tzs.jms.consumer;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class TopicConsumer2 implements MessageListener{
    public void onMessage(Message message) {
        MapMessage msg = (MapMessage) message;

        try {
            System.out.println("TopicConsumer2-----------------------"+msg.getString("1"));
            System.out.println("TopicConsumer2-----------------------"+msg.getString("2"));
            System.out.println("TopicConsumer2-----------------------"+msg.getString("3"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
