package at.objectbay.tests.protocols.user;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import at.objectbay.tests.protocols.Protocols;

public class UserHandlerTest {

	@Test
	public void testUserProtocol() throws Exception {

		Protocols.register();
		
		URL url = new URL("user:.profile");
		InputStream ins = url.openStream();
		assertNotNull(ins);
		ins.close();
	}

}
