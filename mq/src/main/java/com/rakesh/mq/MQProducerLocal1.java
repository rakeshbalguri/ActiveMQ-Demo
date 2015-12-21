package com.rakesh.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MQProducerLocal1 {
	
	public static String brokerURL = "tcp://SAGRA02-M4800.ca.com:61616";
	
	public static void main(String[] args) throws JMSException {

		// setup the connection to ActiveMQ
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
//        Connection connection = factory.createConnection("admin","pass");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("lpar1");
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Creating Message " + i);
            Message message = session.createTextMessage("Local saying Hello!!!! "+i);
            producer.send(message);
        }
        
        
        
//        Session session1 = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination destination1 = session.createQueue("lpar2");
//        MessageProducer producer1 = session.createProducer(destination1);
//        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//        
//        for (int i = 0; i < 100; i++)
//        {
//            System.out.println("Creating Message " + i);
//            Message message1 = session1.createTextMessage("lpar2 saying Hello!!!! "+i);
//            producer1.send(message1);
//        }
        
		connection.close();
        
	}

}