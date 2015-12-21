package com.rakesh.mq;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.activemq.broker.BrokerPlugin;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.jmx.ManagementContext;
import org.apache.activemq.network.NetworkConnector;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;

public class ActiveMQBrokerLocal {

	public static void main(String[] args) throws Exception {

		BrokerService broker = new BrokerService();

		broker.addConnector(new URI("tcp://10.134.119.108:61619"));

		SimpleAuthenticationPlugin securityPlugin = new SimpleAuthenticationPlugin();

		List<AuthenticationUser> usersList = new ArrayList<AuthenticationUser>();

		AuthenticationUser adminUser = new AuthenticationUser("admin", "pass", "admin");

		usersList.add(adminUser);

		securityPlugin.setUsers(usersList);

		BrokerPlugin[] plugins = new BrokerPlugin[1];
		plugins[0] = securityPlugin;
		
		ManagementContext context = new ManagementContext();
        context.setConnectorPort(2011);
        
        broker.setDataDirectory("E:\\UIM\\Eclipse\\Workspace\\mq\\activemq-data\\localhost\\NewDB");
        
        broker.setManagementContext(        context);

		broker.setPlugins(plugins);

		broker.start();

		// broker.addConnector(new URI("amqp://localhost:5672"));
		// broker.addConnector(new URI("stomp://localhost:61613"));
		// broker.addConnector(new URI("mqtt://localhost:1883"));

		 broker.addNetworkConnector(new
		 URI("static:(tcp://10.134.119.108:61620)"));
		// broker.addNetworkConnector(new
		// URI("static:(tcp://10.134.41.187:61617)"));
		//
		 List<NetworkConnector> connList = broker.getNetworkConnectors();
		 for(NetworkConnector conn : connList)
		 {
		 conn.setName("test"+Math.random());
		 conn.setUserName("admin");
		 conn.setPassword("pass");
		 conn.setDuplex(true);
		 conn.start();
		 }

		 
//		 List<NetworkConnector> connList1 = broker.getNetworkConnectors();
//         for(NetworkConnector conn : connList1)
//         {
//             conn.start();
//         }
//
//		 
//		 
//		 broker.addNetworkConnector(new
//		                            URI("static:(tcp://10.134.119.108:61620)"));
//		                           // broker.addNetworkConnector(new
//		                           // URI("static:(tcp://10.134.41.187:61617)"));
//		                           //
//		                            List<NetworkConnector> connList2 = broker.getNetworkConnectors();
//		                            for(NetworkConnector conn : connList2)
//		                            {
//		                            conn.setName("test"+Math.random());
//		                            conn.setUserName("admin");
//		                            conn.setPassword("pass");
//		                            conn.setDuplex(true);
//		                            conn.start();
//		                            }

		 
	}
}
