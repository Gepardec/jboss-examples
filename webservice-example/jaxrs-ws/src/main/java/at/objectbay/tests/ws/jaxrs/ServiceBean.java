package at.objectbay.tests.ws.jaxrs;

import javax.ejb.Stateless;

import at.objectbay.tests.ws.soap.FabrikBean;
import at.objectbay.tests.ws.soap.FabrikBeanService;
import at.objectbay.tests.ws.soap.Person;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoPartnerLELO;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.PartnerLesenLang;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.SEPartnerLesenLangService;

@Stateless
public class ServiceBean {

	private PartnerLesenLang port;

	public Person getPerson() {
		return new FabrikBeanService().getPort(FabrikBean.class).getPerson();
	}

	public String getPartner() {
		DtoPartnerLELO dto = new SEPartnerLesenLangService()
				.getPartnerLesenLangPort().partnerLesenLang(null);
		return "OK";
	}

	public String getPartnerChached() {
		DtoPartnerLELO dto = getPort().partnerLesenLang(null);
		return "OK";
	}

	private PartnerLesenLang getPort() {

		if (null == port) {
			port = new SEPartnerLesenLangService().getPartnerLesenLangPort();
		}
		return port;
	}
}
