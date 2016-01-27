package at.gepardec.demo.wstrans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "transWsResponse", namespace = "http://wstrans.demo.gepardec.at/v1.0")
@XmlRootElement
public class TransWsResponse {

	private byte[] bytes;

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public TransWsResponse(byte[] res) {
		this.bytes = res;
	}

	public TransWsResponse() {
	}

	public byte[] getBytes() {
		return bytes;
	}

}
