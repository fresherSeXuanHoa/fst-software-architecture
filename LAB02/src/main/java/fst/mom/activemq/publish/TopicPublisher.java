package fst.mom.activemq.publish;

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

public class TopicPublisher {
	public static void main(String[] args) throws Exception {
		// thiáº¿t láº­p mÃ´i trÆ°á»�ng cho JMS logging
		BasicConfigurator.configure();
		// thiáº¿t láº­p mÃ´i trÆ°á»�ng cho JJNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		// táº¡o context
		Context ctx = new InitialContext(settings);
		// lookup JMS connection factory
		Object obj = ctx.lookup("TopicConnectionFactory");
		ConnectionFactory factory = (ConnectionFactory) obj;
		// táº¡o connection
		Connection con = factory.createConnection("admin", "admin");
		// ná»‘i Ä‘áº¿n MOM
		con.start();
		// táº¡o session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
		Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet2");
		// táº¡o producer
		MessageProducer producer = session.createProducer(destination);
		// Táº¡o 1 message
		Message msg = session.createTextMessage("tá»« publisher: xin ChÃ ooooo");
		// gá»­i
		producer.send(msg);
		// shutdown connection
		session.close();
		con.close();
		System.out.println("Finished...");
	}
}