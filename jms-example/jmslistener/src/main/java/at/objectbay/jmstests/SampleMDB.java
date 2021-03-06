package at.objectbay.jmstests;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
// import org.jboss.ejb3.annotation.ResourceAdapter;

// @ResourceAdapter("activemq-ext")
@MessageDriven(
//    name = "SampleMDB",
    activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Habarama::Watering"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
    }
)
/*
    mappedName = "java:/SampleQueue",
@MessageDriven(name = "MessageMDBSample", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/sampleQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
*/
public class SampleMDB implements MessageListener {
	public void onMessage(Message message) {
//		TextMessage tm = (TextMessage) message;
//		try {
			System.out.println("Received message " + message);
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
	}
}
