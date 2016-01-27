package at.gepardec.demo.wstrans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "transWsRequest", namespace = "http://wstrans.demo.gepardec.at/v1.0")
@XmlRootElement
public class TransWsRequest {
	
	private String service;
	private String application;
	private String traeger;
	private String stage;
	private byte[] bytes;
	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getTraeger() {
		return traeger;
	}
	public void setTraeger(String traeger) {
		this.traeger = traeger;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}

}
