package fst.mom.activemq.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import org.apache.log4j.BasicConfigurator;

public class GUI_PubSub extends JFrame implements ActionListener {

	private JButton buttonSender;
	private JTextField text1;
	private JTextField text2;
	private static String rcText;

	public GUI_PubSub() {
		setTitle("Pub Sub");
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 300));
		setMaximumSize(new Dimension(600, 500));
		buttonSender = new JButton("send");

		add(buttonSender, BorderLayout.EAST);
		text1 = new JTextField();
		add(text1, BorderLayout.NORTH);
		text2 = new JTextField();
		add(text2, BorderLayout.SOUTH);
		buttonSender.addActionListener(this);

		buttonSender.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					publish();
					subcribe();
					text2.setText(rcText);
					// JOptionPane.showMessageDialog(this, "hehe", rcText);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void publish() throws Exception {
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
		Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet");
		// táº¡o producer
		MessageProducer producer = session.createProducer(destination);
		// Táº¡o 1 message
		Message msg = session.createTextMessage(text1.getText().toString());
		// gá»­i
		producer.send(msg);
		// shutdown connection
		session.close();
		con.close();
		System.out.println("Finished...");
	}

	private void subcribe() throws Exception {
		String receiveText = "";
		// thiÃ¡ÂºÂ¿t lÃ¡ÂºÂ­p mÃƒÂ´i trÃ†Â°Ã¡Â»ï¿½ng cho JMS
		BasicConfigurator.configure();
		// thiÃ¡ÂºÂ¿t lÃ¡ÂºÂ­p mÃƒÂ´i trÃ†Â°Ã¡Â»ï¿½ng cho JJNDI
		Properties settings = new Properties();
		settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
		// tÃ¡ÂºÂ¡o context
		Context ctx = new InitialContext(settings);
		// lookup JMS connection factory
		Object obj = ctx.lookup("TopicConnectionFactory");
		ConnectionFactory factory = (ConnectionFactory) obj;
		// tÃ¡ÂºÂ¡o connection
		Connection con = factory.createConnection("admin", "admin");
		// nÃ¡Â»â€˜i Ã„â€˜Ã¡ÂºÂ¿n MOM
		con.start();
		// tÃ¡ÂºÂ¡o session
		Session session = con.createSession(/* transaction */false, /* ACK */Session.CLIENT_ACKNOWLEDGE);
		// tÃ¡ÂºÂ¡o consumer
		Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet");
		MessageConsumer receiver = session.createConsumer(destination);
		// receiver.receive();//blocked method
		// Cho receiver lÃ¡ÂºÂ¯ng nghe trÃƒÂªn queue, chÃ¡Â»Â«ng cÃƒÂ³ message
		// thÃƒÂ¬ notify
		receiver.setMessageListener(new MessageListener() {
			// @Override
			// cÃƒÂ³ message Ã„â€˜Ã¡ÂºÂ¿n queue, phÃ†Â°Ã†Â¡ng thÃ¡Â»Â©c nÃƒÂ y
			// Ã„â€˜Ã†Â°Ã¡Â»Â£c thÃ¡Â»Â±c thi
			public void onMessage(Message msg) {// msg lÃƒÂ  message nhÃ¡ÂºÂ­n
												// Ã„â€˜Ã†Â°Ã¡Â»Â£c
				try {
					if (msg instanceof TextMessage) {
						TextMessage tm = (TextMessage) msg;
						rcText = tm.getText();
						System.out.println("XML= " + rcText);
						msg.acknowledge();// gÃ¡Â»Â­i tÃƒÂ­n hiÃ¡Â»â€¡u ack
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void main(String[] args) {
		new GUI_PubSub().setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
	}

}
