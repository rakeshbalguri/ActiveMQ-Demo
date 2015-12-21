package com.rakesh.mq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

public class ActiveMQBrokerCA31 {

	public static void main(String[] args) throws Exception {

		BrokerService broker = new BrokerService();

		broker.addConnector(new URI("tcp://141.202.65.31:61619"));

		SimpleAuthenticationPlugin securityPlugin = new SimpleAuthenticationPlugin();

		List<AuthenticationUser> usersList = new ArrayList<AuthenticationUser>();

		AuthenticationUser adminUser = new AuthenticationUser("admin", "pass", "admin");

		usersList.add(adminUser);

		securityPlugin.setUsers(usersList);

		BrokerPlugin[] plugins = new BrokerPlugin[1];
		plugins[0] = securityPlugin;

		broker.setPlugins(plugins);

		broker.start();

		// broker.addConnector(new URI("amqp://localhost:5672"));
		// broker.addConnector(new URI("stomp://localhost:61613"));
		// broker.addConnector(new URI("mqtt://localhost:1883"));

		// broker.addNetworkConnector(new
		// URI("static:(tcp://10.134.41.187:61616)"));
		// broker.addNetworkConnector(new
		// URI("static:(tcp://10.134.41.187:61617)"));
		//
		// List<NetworkConnector> connList = broker.getNetworkConnectors();
		// for(NetworkConnector conn : connList)
		// {
		// conn.setName("test"+Math.random());
		// conn.setUserName("admin");
		// conn.setPassword("pass");
		// conn.setDuplex(true);
		// conn.start();
		// }

	}
}
