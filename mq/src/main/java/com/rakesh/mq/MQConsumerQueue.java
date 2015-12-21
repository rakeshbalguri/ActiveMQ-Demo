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

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQConsumerQueue implements MessageListener{
	
	public static String brokerURL = "tcp://localhost:61616";
	 
    private static Connection connection;
    private static Session session;
    private static MessageConsumer consumer;
 
    public static void main( String[] args ) throws JMSException
    {
    	MQConsumerQueue consumer1 = new MQConsumerQueue();
    	
    	ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
//    	connection = factory.createConnection("admin","pass");
    	Connection connection = factory.createConnection();
    	connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("lpar1");
        consumer = session.createConsumer(destination);
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
            } else
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
