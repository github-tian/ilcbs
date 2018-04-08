package cn.tzs.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

/**
 * spring 整合 avtiveMq
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mq-consumer.xml")
public class SpringListenerMessageTest {


    @Test
    public void testRun() throws JMSException {
        while (true) {
        }
    }

    @Test
    public void sendQueueMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("spring_queue");
        TextMessage message = session.createTextMessage("spring_queue的测试消息");
        MessageProducer producer = session.createProducer(queue);

        producer.send(message);

        session.close();
        connection.close();
    }

    @Test
    public void sendTopicMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("spring_topic");
        MessageProducer producer = session.createProducer(topic);
        MapMessage message = session.createMapMessage();

        message.setString("1", "spring_topic    map_topic_message1");
        message.setString("2", "spring_topic    map_topic_message2");
        message.setString("3", "spring_topic    map_topic_message3");
        producer.send(message);

        session.close();
        connection.close();
    }


}
