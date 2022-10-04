package fst.activemq.sender;

import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.BasicConfigurator;

import fst.activemq.core.Person;
import fst.activemq.core.XMLConvert;

public class Sender {
	public static void main(String[] args) throws Exception {
		// Config environment for JMS
		BasicConfigurator.configure();
		
		// Config environment for JNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		
		// Create context
		Context ctx = new InitialContext(settings);
		
		// Lookup JMS connection factory
		ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
		
		// Lookup destination. (If not exist --> ActiveMQ create once)
		Destination destination = (Destination) ctx.lookup("dynamicQueues/thanthidet");
		
		// Get connection using credential
		Connection con = factory.createConnection("admin", "admin");
		
		// Connect to MOM
		con.start();
		
		// Create session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
		
		// Create producer
		MessageProducer producer = session.createProducer(destination);
		
		// Create text message
		Message msg = session.createTextMessage("Hello mesage from ActiveMQ");
		producer.send(msg);
		Person p = new Person(1001, "Thân Thị Đẹt", new Date());
		String xml = new XMLConvert<Person>(p).object2XML(p);
		msg = session.createTextMessage(xml);
		producer.send(msg);
		
		// Shutdown connection
		session.close();
		con.close();
		System.out.println("Finished...");
	}
}