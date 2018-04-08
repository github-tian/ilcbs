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
public class SpringQueueMessageTest {

    @Qualifier("jmsQueueTemplate")
    @Autowired
    private JmsTemplate template;

    @Test
    public void sendMessage() throws JMSException {
        template.send("spring_queue_test", new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage("spring_queue_test方式的测试写入");
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
        Message message = template.receive("spring_queue_test");
        System.out.println(message);
    }

}
