package at.objectbay.tests.ws.jaxrs;

import javax.ejb.Stateless;

import at.objectbay.tests.ws.soap.FabrikBean;
import at.objectbay.tests.ws.soap.FabrikBeanService;
import at.objectbay.tests.ws.soap.Person;

@Stateless
public class ServiceBean {

	public Person getPerson() {
		return new FabrikBeanService().getPort(FabrikBean.class).getPerson();
	}
}
