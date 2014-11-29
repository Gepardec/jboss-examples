package at.objectbay.tests.protocols;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.HashMap;
import java.util.Map;

public class InvmStreamHandlerFactory implements URLStreamHandlerFactory {

	private static Map<String, URLStreamHandler> handlerMap = new HashMap<String, URLStreamHandler>(
			1);

	static {
		handlerMap.put("invmhttp",
				new at.objectbay.tests.protocols.invmhttp.Handler());
	}

	@Override
	public URLStreamHandler createURLStreamHandler(final String protocol) {
		return handlerMap.get(protocol);
	}
}