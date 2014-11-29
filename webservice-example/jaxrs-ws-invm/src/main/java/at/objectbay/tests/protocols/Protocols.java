package at.objectbay.tests.protocols;

public class Protocols {
	
	public static void register() {
		String protocol = "at.objectbay.tests.protocols";
		String pkgs = System.getProperty("java.protocol.handler.pkgs");
		if (pkgs == null || pkgs.trim().length() == 0) {
			pkgs = protocol;
			System.setProperty("java.protocol.handler.pkgs", pkgs);
		} else if (pkgs.contains("org.jboss.virtual.protocol") == false) {
			pkgs += "|"+protocol;
			System.setProperty("java.protocol.handler.pkgs", pkgs);
		}
	}
}
