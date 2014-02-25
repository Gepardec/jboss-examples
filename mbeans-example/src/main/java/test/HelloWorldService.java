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

@Singleton
@Startup
public class HelloWorldService implements HelloWorldServiceMBean {
	private static final String MBEAN_OBJECT_NAME = "test.startup:service=HelloWorld";

	private final Logger logger = Logger.getLogger(this.getClass());

	private String message = "Sorry no message today";
	private ObjectName objectName;

	public HelloWorldService() throws NotCompliantMBeanException {
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
		logger.info("printMessage() : " + message);
	}

	@PostConstruct
	public void initialize() {
		MBeanServer mbeanServer = java.lang.management.ManagementFactory
				.getPlatformMBeanServer();
		System.out.println("Got the MBeanServer.... " + mbeanServer);

		try {
			objectName = new ObjectName(MBEAN_OBJECT_NAME);
			mbeanServer.registerMBean(this, objectName);
			System.out.println("MBean Registered with ObjectName:  "
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
		System.out
				.println("MBean Unregistered with ObjectName:  " + objectName);
	}

}
