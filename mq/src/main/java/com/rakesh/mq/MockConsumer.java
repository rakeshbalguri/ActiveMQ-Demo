package com.rakesh.mq;

import javax.jms.JMSException;

import com.ca.eventgenerator.client.Consumer;
import com.ca.eventgenerator.client.EventListener;
import com.ca.eventgenerator.configuration.ConnectionProperties;
import com.ca.eventgenerator.configuration.ConnectionType;
import com.ca.eventgenerator.model.event.Event;
import com.ca.eventgenerator.model.event.MessageEvent;

public class MockConsumer {

	public static void main(String[] args) throws JMSException {
		
		Consumer cons = new Consumer(new ConnectionProperties(ConnectionType.CONSUMER, "10.131.53.145", 61616, "lpar1"));
		
		cons.subscribe(new EventListener() {
			
			public void onEvent(Event event) {
				
				MessageEvent msgEvent = (MessageEvent) event;
				
				StringBuffer message = new StringBuffer();
				
				message.append(msgEvent.getText() +" ");
				
				System.out.println("Message Receieved: "+message);
				
			}
		});
		
		cons.connect();
	}
	
}
