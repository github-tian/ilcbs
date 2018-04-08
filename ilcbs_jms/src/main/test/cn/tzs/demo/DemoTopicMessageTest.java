package cn.tzs.demo;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class DemoTopicMessageTest {

    @Test
    public void sendMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("topic_test");
        MessageProducer producer = session.createProducer(topic);
        MapMessage message = session.createMapMessage();

        message.setString("1", "map_topic_message1");
        message.setString("2", "map_topic_message2");
        message.setString("3", "map_topic_message3");
        producer.send(message);


        session.close();
        connection.close();
    }

    /**
     * 订阅 异步 接收消息
     *
     * @throws JMSException
     */
    @Test
    public void receiverMessage() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("topic_test");
        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                MapMessage mess = (MapMessage) message;
                try {
                    System.out.println(mess.getString("1"));
                    System.out.println(mess.getString("2"));
                    System.out.println(mess.getString("3"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });


        while (true) {

        }
//        session.close();
//        connection.close();
    }

    /**
     * 订阅 同步 接收消息
     *
     * @throws JMSException
     */
    @Test
    public void receiver2Message() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("topic_test");
        MessageConsumer consumer = session.createConsumer(topic);


        while (true) {
            MapMessage message = (MapMessage) consumer.receive();
            System.out.println(message.getString("1"));
            System.out.println(message.getString("2"));
            System.out.println(message.getString("3"));

        }
//        session.close();
//        connection.close();
    }
}
