package at.objectbay.tests.ws.jaxrs;

import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceRef;

import org.jboss.logging.Logger;

import at.objectbay.schema.test.svc.customerread_6_0.CustomerRead;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELI;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELO;
import at.objectbay.schema.test.svc.customerread_6_0.SECustomerReadService;

@Stateless
public class ServiceBean {

	private static final Logger log = Logger.getLogger(ServiceBean.class);

	@WebServiceRef(value=SECustomerReadService.class, wsdlLocation="/WEB-INF/PersonenService.wsdl")
	private CustomerRead injectedPort;

	
	public String getCustomer(String name) {
		DtoCustomerLELI search = new DtoCustomerLELI();
		authenticate(injectedPort, name, name);
		DtoCustomerLELO dto = injectedPort.customerRead(search);
		return dto.getDtoAnschriftShort().getOrt();
	}

	private void authenticate(CustomerRead wsPort, String name, String password) {
		BindingProvider bp = (BindingProvider) wsPort;
		bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, name);
		bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
	}
}
