package at.objectbay.tests.ws.soap;

import javax.jws.WebService;

import at.objectbay.tests.domain.Person;

@WebService
public class FabrikBean {

	public Person getPerson() {
		return new Person();
	}
}
