package at.objectbay.tests.ws.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;

import at.objectbay.tests.ws.soap.FabrikBean;
import at.objectbay.tests.ws.soap.FabrikBeanService;
import at.objectbay.tests.ws.soap.Person;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoPartnerLELI;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoPartnerLELO;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.PartnerLesenLang;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.SEPartnerLesenLangService;

@Stateless
//@ApplicationScoped
public class ServiceBean {

	private PartnerLesenLang port;

	public Person getPerson() {
		return new FabrikBeanService().getPort(FabrikBean.class).getPerson();
	}

	public String getPartner(String name) {
		DtoPartnerLELI search = new DtoPartnerLELI();
		search.setBeziehungsartKurz(name);
		port = new SEPartnerLesenLangService()
		.getPartnerLesenLangPort();
		authenticate(port, name, name);
		DtoPartnerLELO dto = port.partnerLesenLang(search );
		return dto.getDtoAnschriftKurz().getOrt();
	}

	public String getPartnerChached(String name) {
		DtoPartnerLELI search = new DtoPartnerLELI();
		search.setBeziehungsartKurz(name);
		authenticate(getPort(), name, name);
		DtoPartnerLELO dto = getPort().partnerLesenLang(search);
		return dto.getDtoAnschriftKurz().getOrt();
	}

	private void authenticate(PartnerLesenLang port, String name, String password) {
        BindingProvider bp = (BindingProvider)getPort();
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, name);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
      
        List<Header> headers = new ArrayList<Header>();
        Header dummyHeader;
		try {
			dummyHeader = new Header(new QName("uri:org.apache.cxf", "dummy"), name,
			                                new JAXBDataBinding(String.class));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
        headers.add(dummyHeader);
         
        //server side:
 //       context.getMessageContext().put(Header.HEADER_LIST, headers);
         
        //client side:
       bp.getRequestContext().put(Header.HEADER_LIST, headers);
       /*
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

	private PartnerLesenLang getPort() {

		if (null == port) {
			port = new SEPartnerLesenLangService().getPartnerLesenLangPort();
		}
		return port;
	}
}
