package at.gepardec.demo.wstrans.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;

import org.jboss.jbossts.txbridge.outbound.JaxWSTxOutboundBridgeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.gepardec.demo.wstrans.TransWs;
import at.gepardec.demo.wstrans.TransWsRequest;
import at.gepardec.demo.wstrans.TransWsResponse;

import com.arjuna.mw.wst11.client.JaxWSHeaderContextProcessor;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EJBWrapper4TransWsBean {

	private final static Logger timer = LoggerFactory.getLogger(EJBWrapper4TransWsBean.class.getName() + ".timer");

    private TransWs transWs;
    private String URL = "http://localhost:8080/test/TransWs";
//    private String url = "http://localhost:8180/transws/TransWs";

    
    @PostConstruct
    public void init() throws Exception {
 //   	transWs = createTransactionalWs(URL);
    }

private TransWs createTransactionalWs(String url) throws Exception {
	TransWs ws = createWebService(url);
	// add WS-AT (Atomic Transaction) Handlers
	@SuppressWarnings("rawtypes")
	List<Handler> handlers = new ArrayList<>();
	handlers.add(new JaxWSTxOutboundBridgeHandler());
	handlers.add(new JaxWSHeaderContextProcessor());
	((BindingProvider) ws).getBinding().setHandlerChain(handlers);
	return ws;
}

    private TransWs createWebService(String url) throws Exception {
        URL wsdlLocation = new URL(url + "?wsdl");
        QName qNameService = new QName("http://wstrans.demo.gepardec.at/v1.0", "TransWsBeanService");
        QName qNamePort = new QName("http://wstrans.demo.gepardec.at/v1.0", "TransWsPort");
        return Service.create(wsdlLocation, qNameService).getPort(qNamePort, TransWs.class);
    }
    
    
    public String testCallTransWsOk() throws Exception {
    	return callWs(URL);
    } 
    
    public String callWs(String url) throws Exception {
    	long start = System.currentTimeMillis();
    	transWs = createTransactionalWs(url);
//    	transWs = createWebService(url);
		timer.info("createWebService Time: " + (System.currentTimeMillis() - start) + "ms");
    	start = System.currentTimeMillis();
    	TransWsRequest request = new TransWsRequest();
    	request.setService("TestOK");
    	request.setApplication("MVB");
    	request.setTraeger("14");
        TransWsResponse response = transWs.testCall(request);
		timer.info(request.getService() + " Time: " + (System.currentTimeMillis() - start) + "ms");
        return new String(response.getBytes());
    } 
}
