package at.gepardec.tests.ws.soap;

import javax.jws.WebService;

import at.gepardec.tests.domain.Person;

@WebService
public class FabrikBean {

	public Person getPerson() {
		return new Person();
	}
}
