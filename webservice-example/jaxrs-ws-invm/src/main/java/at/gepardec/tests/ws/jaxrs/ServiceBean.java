package at.gepardec.tests.ws.jaxrs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.jboss.logging.Logger;

import at.gepardec.schema.test.svc.customerread_6_0.CustomerRead;
import at.gepardec.schema.test.svc.customerread_6_0.DtoCustomerLELI;
import at.gepardec.schema.test.svc.customerread_6_0.DtoCustomerLELO;
import at.gepardec.schema.test.svc.customerread_6_0.SECustomerReadService;

@Stateless
// @ApplicationScoped
public class ServiceBean {

	private static final Logger log = Logger.getLogger(ServiceBean.class);
	private static URL WSDL_LOC = ServiceBean.class.getResource("/PersonenService.wsdl");


	private CustomerRead port;

	@WebServiceRef(value=SECustomerReadService.class, wsdlLocation="/WEB-INF/PersonenService.wsdl")
	private CustomerRead injectedPort;

	public String getCustomer(String name) {
		DtoCustomerLELI search = new DtoCustomerLELI();
		search.setBeziehungsartShort(name);
		CustomerRead myport = new SECustomerReadService(WSDL_LOC)
				.getCustomerReadPort();
		authenticate(myport, name, name);
		DtoCustomerLELO dto = myport.customerRead(search);
		return dto.getDtoAnschriftShort().getOrt();
	}

	public String getCustomerChached(String name) {
		DtoCustomerLELI search = new DtoCustomerLELI();
		search.setBeziehungsartShort(name);
		authenticate(getPort(), name, name);
		DtoCustomerLELO dto = getPort().customerRead(search);
		return dto.getDtoAnschriftShort().getOrt();
	}
	
	public String getCustomerInjected(String name) {
		if ( null == injectedPort ){
			throw new NullPointerException("injectedPort is null!");
		}
		DtoCustomerLELI search = new DtoCustomerLELI();
		authenticate(injectedPort, name, name);
		DtoCustomerLELO dto = injectedPort.customerRead(search);
		return dto.getDtoAnschriftShort().getOrt();
	}

	private void authenticate(CustomerRead wsPort, String name, String password) {
		BindingProvider bp = (BindingProvider) wsPort;
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, name);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

		List<Header> headers = new ArrayList<Header>();
		Header dummyHeader;
		try {
			dummyHeader = new Header(new QName("uri:org.apache.cxf", "dummy"),
					name, new JAXBDataBinding(String.class));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		headers.add(dummyHeader);

		// server side:
		// context.getMessageContext().put(Header.HEADER_LIST, headers);

		// client side:
		bp.getRequestContext().put(Header.HEADER_LIST, headers);

	}

	private CustomerRead getPort() {

		if (null == port) {
			port = new SECustomerReadService(WSDL_LOC).getCustomerReadPort();
		}
		return port;
	}
}
