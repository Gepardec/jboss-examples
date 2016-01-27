package at.gepardec.demo.wstrans.impl;


import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.gepardec.demo.wstrans.TransWs;
import at.gepardec.demo.wstrans.TransWsRequest;
import at.gepardec.demo.wstrans.TransWsResponse;

@WebService(name = "TransWs", targetNamespace = "http://wstrans.demo.gepardec.at/v1.0")
@MTOM(enabled = true, threshold = 1024)
@Addressing(enabled = true, required = false)
@HandlerChain(file = "/META-INF/jaxws-handlers-server.xml")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class TransWsBean implements TransWs {
	 private final static Logger timer = LoggerFactory.getLogger(TransWsBean.class.getName() + ".timer");
	 
    public TransWsResponse call(TransWsRequest request) {
    	long start = System.currentTimeMillis();
		TransWsResponse response = new TransWsResponse("Response".getBytes());
		timer.info(request.getService() + " Time: " + (System.currentTimeMillis() - start) + "ms");
		return response;
    }
    

	@Override
    public TransWsResponse testCall(TransWsRequest request) {
    	long start = System.currentTimeMillis();
        byte[] res = "Hello,Hello".getBytes();
        System.out.println("Respond: " + new String(res));
        TransWsResponse response =  new TransWsResponse(res);
		timer.info(request.getService() + " Time: " + (System.currentTimeMillis() - start) + "ms");
		return response;
    }
}
