package cn.tzs.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class DemoQueueMessageTest {

    @Test
    public void sendMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("queue_test");
        TextMessage message = session.createTextMessage("queue的测试消息");
        MessageProducer producer = session.createProducer(queue);

//        message.setText("aaaaaaaaaaaa");
        producer.send(message);

        session.close();
        connection.close();
    }

    /**
     * 队列 异步 接收消息
     * @throws JMSException
     */
    @Test
    public void receiverMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("queue_test");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                System.out.println(message);
            }
        });


        while (true) {

        }
//        session.close();
//        connection.close();
    }/**
     * 队列 同步 接收消息
     * @throws JMSException
     */
    @Test
    public void receiver2Message() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("queue_test");
        MessageConsumer consumer = session.createConsumer(queue);

        while (true) {
            Message message= consumer.receive();
            System.out.println(message);

        }
//        session.close();
//        connection.close();
    }
}
