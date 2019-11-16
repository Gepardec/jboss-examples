package at.gepardec.tests.protocols.invmhttp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handler extends URLStreamHandler {
	private static Logger log = LoggerFactory.getLogger(Handler.class);

	@SuppressWarnings("restriction")
	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		log.info("Url: " + url);

		return InvmProxy.getConnection(url);
	}
}