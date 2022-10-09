package fst.mom.activemq.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.BasicConfigurator;

public class PubAndSub extends JFrame implements ActionListener {

	private JButton btnSend;
	private JPanel contentPane;
	private JTextField txtSender;
	private JTextField txtReceiver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PubAndSub frame = new PubAndSub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PubAndSub() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblSend = new JLabel("Sender");
		GridBagConstraints gbc_lblSend = new GridBagConstraints();
		gbc_lblSend.insets = new Insets(0, 0, 5, 5);
		gbc_lblSend.anchor = GridBagConstraints.EAST;
		gbc_lblSend.gridx = 0;
		gbc_lblSend.gridy = 0;
		panel.add(lblSend, gbc_lblSend);

		txtSender = new JTextField();
		GridBagConstraints gbc_txtSender = new GridBagConstraints();
		gbc_txtSender.insets = new Insets(0, 0, 5, 0);
		gbc_txtSender.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSender.gridx = 1;
		gbc_txtSender.gridy = 0;
		panel.add(txtSender, gbc_txtSender);
		txtSender.setColumns(10);

		JLabel lblReceiver = new JLabel("Receiver");
		GridBagConstraints gbc_lblReceiver = new GridBagConstraints();
		gbc_lblReceiver.insets = new Insets(0, 0, 5, 5);
		gbc_lblReceiver.anchor = GridBagConstraints.EAST;
		gbc_lblReceiver.gridx = 0;
		gbc_lblReceiver.gridy = 5;
		panel.add(lblReceiver, gbc_lblReceiver);

		txtReceiver = new JTextField();
		txtReceiver.setColumns(10);
		GridBagConstraints gbc_txtReceiver = new GridBagConstraints();
		gbc_txtReceiver.insets = new Insets(0, 0, 5, 0);
		gbc_txtReceiver.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtReceiver.gridx = 1;
		gbc_txtReceiver.gridy = 5;
		panel.add(txtReceiver, gbc_txtReceiver);

		btnSend = new JButton("Send");
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSend.gridx = 1;
		gbc_btnSend.gridy = 7;
		panel.add(btnSend, gbc_btnSend);
		btnSend.addActionListener(this);
		subcribe();
	}

	public void actionPerformed(ActionEvent e) {
		Object tg = e.getSource();
		if (tg.equals(btnSend)) {

			publish();
		}
	}

	public void publish() {
		try {
			BasicConfigurator.configure();
			Properties settings = new Properties();
			settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
			Context ctx = new InitialContext(settings);
			Object obj = ctx.lookup("TopicConnectionFactory");
			ConnectionFactory factory = (ConnectionFactory) obj;
			Connection con = factory.createConnection("admin", "admin");
			con.start();
			Session session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
			Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet2");
			MessageProducer producer = session.createProducer(destination);
			// Message msg = session.createTextMessage("từ publisher: xin
			// Chàooooo");
			// producer.send(msg);
			Message msg = session.createTextMessage(txtSender.getText().toString());
			producer.send(msg);
			session.close();
			con.close();
			System.out.println("Finished...");
		} catch (Exception e) {
			try {
				throw new Exception(e);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public void subcribe() {
		try {
			BasicConfigurator.configure();
			Properties settings = new Properties();
			settings.setProperty(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
			Context ctx = new InitialContext(settings);
			Object obj = ctx.lookup("TopicConnectionFactory");
			ConnectionFactory factory = (ConnectionFactory) obj;
			Connection con = factory.createConnection("admin", "admin");
			con.start();
			Session session = con.createSession(/* transaction */false, /* ACK */Session.CLIENT_ACKNOWLEDGE);
			Destination destination = (Destination) ctx.lookup("dynamicTopics/thanthidet2");
			MessageConsumer receiver = session.createConsumer(destination);
			receiver.setMessageListener(new MessageListener() {
				public void onMessage(Message msg) {
					try {
						if (msg instanceof TextMessage) {
							TextMessage tm = (TextMessage) msg;
							String txt = tm.getText();
							txtReceiver.setText(txt);
							System.out.println("XML= " + txt);
							msg.acknowledge();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			try {
				throw new Exception(e);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}
