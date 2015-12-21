package com.rakesh.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.util.StringUtils;

public class MQConsumerTopic1 implements MessageListener{
	
	public static String brokerURL = "tcp://10.134.118.99:61616";
	 
    private static Connection connection;
    private static Session session;
    private static MessageConsumer consumer;
 
    public static void main( String[] args ) throws JMSException
    {
    	MQConsumerTopic1 consumer1 = new MQConsumerTopic1();
    	
    	ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
    	connection = factory.createConnection("admin","pass");
    	connection.setClientID("probe1");
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Topic topic = session.createTopic("lpar1");
        
        consumer = session.createDurableSubscriber(topic, "probe1Subscriber");
        consumer.setMessageListener(consumer1);
    }
 
    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof TextMessage)
            {
                TextMessage txtMessage = (TextMessage)message;
                System.out.println("Message received: " + txtMessage.getText());
            }else 
            if (message instanceof ObjectMessage)
            {
            	ObjectMessage objectMessage = (ObjectMessage) message;
            	System.out.println("Message received: " + objectMessage.toString());
            	            	
            }
            else
            {
                System.out.println("Invalid message received.");
            }
        }
        catch (JMSException e)
        {
            System.out.println("Caught:" + e);
            e.printStackTrace();
        }
    }

}
