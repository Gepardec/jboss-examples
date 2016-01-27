package at.gepardec.demo.wstrans;

import javax.jws.WebService;

@WebService(targetNamespace = "http://wstrans.demo.gepardec.at/v1.0", name = "TransWs")
public interface TransWs {

	TransWsResponse call(TransWsRequest request);
	
	TransWsResponse testCall(TransWsRequest request);

}
