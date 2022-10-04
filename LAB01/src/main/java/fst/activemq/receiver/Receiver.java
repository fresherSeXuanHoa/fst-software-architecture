package fst.activemq.receiver;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.BasicConfigurator;

public class Receiver {
	public static void main(String[] args) throws Exception {
		// Thiết lập môi trường cho JMS
		BasicConfigurator.configure();
		
		// Thiết lập môi trường cho JJNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		
		// Tạo context
		Context ctx = new InitialContext(settings);
		
		// Lookup JMS connection factory
		Object obj = ctx.lookup("ConnectionFactory");
		ConnectionFactory factory = (ConnectionFactory) obj;
		
		// Lookup destination
		Destination destination = (Destination) ctx.lookup("dynamicQueues/thanthidet");
		
		// Tạo connection
		Connection con = factory.createConnection("admin", "admin");
		
		// Nối đến MOM
		con.start();
		
		// Tạo session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.CLIENT_ACKNOWLEDGE);
		
		// Tạo consumer
		MessageConsumer receiver = session.createConsumer(destination);
		
		// blocked-method for receiving message - sync
		// receiver.receive();
		// Cho receiver lắng nghe trên queue, chừng có message thì notify -
		// async
		
		System.out.println("Tý was listened on queue...");
		receiver.setMessageListener(new MessageListener() {
			
			// Có Message đến queue, phương thức này được thực thi
			public void onMessage(Message msg) {
				try {
					if (msg instanceof TextMessage) {
						TextMessage tm = (TextMessage) msg;
						String txt = tm.getText();
						System.out.println("Recieved: " + txt);
						
						// Gửi tín hiệu ACK
						msg.acknowledge();
					} else if (msg instanceof ObjectMessage) {
						ObjectMessage om = (ObjectMessage) msg;
						System.out.println(om);
					}
					
					// Others message type....
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}