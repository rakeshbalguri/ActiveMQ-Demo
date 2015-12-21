package com.rakesh.mq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.MessageAuthorizationPolicy;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

public class ActiveMQBroker {

	
	public static void main(String[] args) throws Exception {
		
//		BrokerService broker = BrokerFactory.createBroker("xbean:conf/activemq.xml");
		
//		broker.setMessageAuthorizationPolicy(messageAuthorizationPolicy);
		
//		broker.addConnector(new URI("tcp://localhost:61616"));
//		broker.addConnector(new URI("amqp://localhost:5672"));
//		broker.addConnector(new URI("stomp://localhost:61613"));
//		broker.addConnector(new URI("mqtt://localhost:1883"));
		
		
		BrokerService broker = new BrokerService();

		broker.addConnector(new URI("tcp://10.132.78.191:61616"));

		SimpleAuthenticationPlugin securityPlugin = new SimpleAuthenticationPlugin();

		List<AuthenticationUser> usersList = new ArrayList<AuthenticationUser>();

		AuthenticationUser adminUser = new AuthenticationUser("admin", "pass", "admin");

		usersList.add(adminUser);

		securityPlugin.setUsers(usersList);

		BrokerPlugin[] plugins = new BrokerPlugin[1];
		plugins[0] = securityPlugin;

		broker.setPlugins(plugins);
		
		ManagementContext context = new ManagementContext();
		context.setConnectorPort(2011);
		
//		broker.setDataDirectory("E:\\UIM\\Eclipse\\Workspace\\mq\\activemq-data\\localhost\\NewDB");
		
		broker.setManagementContext(		context);

		broker.start();

		// broker.addConnector(new URI("amqp://localhost:5672"));
		// broker.addConnector(new URI("stomp://localhost:61613"));
		// broker.addConnector(new URI("mqtt://localhost:1883"));

//		 broker.addNetworkConnector(new
//		 URI("static:(tcp://141.202.65.31:61619)"));
//		 broker.addNetworkConnector(new
//		 URI("static:(tcp://10.134.118.87:61620)"));
//		
//		 List<NetworkConnector> connList = broker.getNetworkConnectors();
//		 for(NetworkConnector conn : connList)
//		 {
//		 conn.setName("test"+Math.random());
//		 conn.setUserName("admin");
//		 conn.setPassword("pass");
//		 conn.setDuplex(true);
//		 conn.start();
//		 }
	}
}
