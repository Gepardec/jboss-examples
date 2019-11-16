package at.objectbay.jmstests;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Topic;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = -4257270508512265869L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("Session ID: " + request.getSession().getId());

		if (request.getSession().getAttribute("test") == null) {
			out.println("New session. Current session is empty.");

			request.getSession().setAttribute("test", new Integer(1));
			System.out.println("Here we start with a new session");

			for (int i = 0; i < 50; i++) {
				byte[] buffer = new byte[2048];
				for (int x = 0; x < 2048; x++) {
					buffer[x] = 100;
				}
				request.getSession().setAttribute("binary." + i, buffer);
			}

		} else {
			Integer value = (Integer) request.getSession().getAttribute("test");
			out.println("Existing session: Value is " + value);

			request.getSession().setAttribute("test",
					new Integer(value.intValue() + 1));
		}

		String msg = "Session ID: " + request.getSession().getId() + " Value:"
				+ (Integer) request.getSession().getAttribute("test");
		System.out.println("Store: " + msg);
		sendMessage(msg);

		out.println("Message sento to the JMS Provider");
	}

	private Connection sendMessage(String msg) {

		String destinationName = "topic/Habarama";
		Context ic;
		ConnectionFactory cf;
		Connection connection = null;

		try {
			ic = new InitialContext();
			cf = (ConnectionFactory) ic.lookup("java:/RemoteJmsXA");
			Topic queue = (Topic) ic.lookup(destinationName);

			connection = cf.createConnection();
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(queue);

			connection.start();

			TextMessage message = session.createTextMessage(msg);
			publisher.send(message);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {

			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		return connection;
	}
}
