package cn.tzs.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * spring 整合 avtiveMq
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mq.xml")
public class SpringTopicMessageTest {

    @Autowired
    @Qualifier("jmsTopicTemplate")
    private JmsTemplate template;

    @Test
    public void sendMessage() throws JMSException {
        template.send("spring_topic_test", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("aaaaaaaaaaaaaaa");
                return message;
            }
        });
    }

    /**
     * 队列 同步 接收消息
     *
     * @throws JMSException
     */
    @Test
    public void receiverMessage() throws JMSException {
        Message message = template.receive("spring_topic_test");
        System.out.println(message);
    }

}
