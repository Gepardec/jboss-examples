package at.objectbay.tests.protocols.invmhttp;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLConnection;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.objectbay.tests.protocols.Protocols;

public class InvmProxy {

	private static Logger log = LoggerFactory.getLogger(InvmProxy.class);

	public static URLConnection getConnection(URL url) {

		String host = "localhost";
		int port;
		try{
			port = getJBossPort();
		}catch(Throwable e){
			log.warn("Can't get port from MBeanServer, using 8080",e);
			port = 8080;
		}
		log.info("Host: {}, Port: {}", host, port);

		return new sun.net.www.protocol.http.HttpURLConnection(url, host, port);
	}

	public static Integer getJBossPort() {
		Integer jbossManagementPort;

		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			ObjectName http = new ObjectName(
					"jboss.as:socket-binding-group=standard-sockets,socket-binding=http");
			jbossManagementPort = (Integer) mBeanServer.getAttribute(http,
					"boundPort");
		} catch (Exception e) {
			throw new Error(e);
		}

		log.debug("Port: "+ jbossManagementPort);
		return jbossManagementPort;
	}

}
