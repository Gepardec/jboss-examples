package at.objectbay.tests.ws.soap;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.jboss.logging.Logger;

import at.objectbay.schema.test.svc.customerread_6_0.CustomerRead;
import at.objectbay.schema.test.svc.customerread_6_0.DtoAnschriftShort;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELI;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELO;

@Stateless
@WebService
public class CustomerReadServices implements CustomerRead {

	private static final Logger log = Logger
			.getLogger(CustomerReadServices.class);

	@Resource
	WebServiceContext context;

	@Override
	public DtoCustomerLELO customerRead(DtoCustomerLELI dtoCustomerLELI) {
		if (context.getMessageContext() == null) {
			log.error("Null MessageContext in customerRead!");
		}
		DtoCustomerLELO customer = new DtoCustomerLELO();
		DtoAnschriftShort anschrift = new DtoAnschriftShort();
		String user = null;
		context.getUserPrincipal();
		user = context.getUserPrincipal().getName();
		log.debugf("User, use getUserPrincipal: %s", user);

		anschrift.setOrt(user);
		customer.setDtoAnschriftShort(anschrift);

		return customer;
	}
}
