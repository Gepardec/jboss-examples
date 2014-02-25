package test;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanFeatureInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanParameterInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;

import org.jboss.logging.Logger;

/**
 * MBean with simple Descriptions. See
 * https://weblogs.java.net/blog/emcmanus/archive/2005/07/adding%5Finformat.html
 * for annotated Version.
 * There are more descriptions to override.
 * 
 */
@Singleton
@Startup
public class HelloWorldWithDescription extends StandardMBean implements
		HelloWorldServiceMBean {
	private static final String MBEAN_OBJECT_NAME = "test.startup:service=HelloWorldWithDescription";

	private final Logger logger = Logger.getLogger(this.getClass());

	private String message = "Sorry no message today";
	private ObjectName objectName;

	public HelloWorldWithDescription() throws NotCompliantMBeanException {
		super(HelloWorldServiceMBean.class);
		logger.info("HelloWorldService() constructed");
	}

	public String getMessage() {
		logger.info("getMessage() : " + message);
		return message;
	}

	public void setMessage(String message) {
		logger.info("setMessage() : " + message);
		this.message = message;
	}

	public void printMessage() {
		System.out.println("printMessage() : " + message);
	}

	@PostConstruct
	public void initialize() {
		MBeanServer mbeanServer = java.lang.management.ManagementFactory
				.getPlatformMBeanServer();
		logger.info("Got the MBeanServer.... " + mbeanServer);

		try {
			objectName = new ObjectName(
					MBEAN_OBJECT_NAME);
			mbeanServer.registerMBean(this, objectName);
			logger.info("MBean Registered with ObjectName:  "
					+ objectName);
		} catch (InstanceAlreadyExistsException e) {
			throw new IllegalStateException(e);
		} catch (MBeanRegistrationException e) {
			throw new IllegalStateException(e);
		} catch (NotCompliantMBeanException e) {
			throw new IllegalStateException(e);
		} catch (MalformedObjectNameException e) {
			throw new IllegalStateException(e);
		}
	}

	@PreDestroy
	public void shutdown() throws MBeanRegistrationException,
			InstanceNotFoundException {
		MBeanServer mbeanServer = java.lang.management.ManagementFactory
				.getPlatformMBeanServer();
		mbeanServer.unregisterMBean(objectName);
		logger.info("MBean Unregistered with ObjectName:  " + objectName);
	}

	@Override
	protected String getDescription(MBeanInfo info) {
		return "This MBean says Hello World!";
	}


	@Override
	protected String getDescription(MBeanConstructorInfo info) {
		System.out.println("getDescription(MBeanConstructorInfo info)");
		return "The constructor of HelloWorld";
	}


	@Override
	protected String getDescription(MBeanOperationInfo info) {
		return "Prints the message to System.out.";
	}

	@Override
	protected String getDescription(MBeanAttributeInfo info) {
		if ( "Message".equalsIgnoreCase(info.getName())){
			return "The message that should be printed.";
		}
		else{
			return "The attribute " + info.getName() + " is unknown";
		}
		
	}

}
