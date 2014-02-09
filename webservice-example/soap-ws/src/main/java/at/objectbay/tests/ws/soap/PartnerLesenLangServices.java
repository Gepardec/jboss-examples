package at.objectbay.tests.ws.soap;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.jaxws.context.WrappedMessageContext;
import org.apache.cxf.message.Message;
import org.jboss.logging.Logger;
import org.w3c.dom.Element;

import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoAnschriftKurz;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoPartnerLELI;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.DtoPartnerLELO;
import at.sozialversicherung.schema.zpv.ibs.partnerlesenlang_6_0.PartnerLesenLang;

@Stateless
@WebService
public class PartnerLesenLangServices implements PartnerLesenLang {

    private static final Logger log = Logger.getLogger(PartnerLesenLangServices.class);

    @Resource
	WebServiceContext context;

	@Override
	public DtoPartnerLELO partnerLesenLang(DtoPartnerLELI dtoPartnerLELI) {
		if ( context.getMessageContext() == null ){
			System.out.println("Null MessageContext in partnerLesenLang!");
		}
		String headerUser = getHeader("dummy");
		DtoPartnerLELO partner = new DtoPartnerLELO();
		DtoAnschriftKurz anschrift = new DtoAnschriftKurz();
		String user = null;
		if ( null != context.getUserPrincipal()){
			user = context.getUserPrincipal().getName();
			log.debugf("User, use getUserPrincipal: %s", user);
		}
		else if (null != headerUser ){
			user = headerUser;
			log.debugf("User, use headerUser: %s", user);
		}
		else{
			System.out.println("Null getUserPrincipal in partnerLesenLang!");
			user = dtoPartnerLELI.getBeziehungsartKurz();
			log.debugf("User, use dtoPartnerLELI.getBeziehungsartKurz: %s", user);
		}
		anschrift.setOrt(user);			
		partner.setDtoAnschriftKurz(anschrift);
		/*
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return partner;
	}
    @Resource  
    private void setWSContext(WebServiceContext inContext)  
    {  
        System.out.println("***** Got the WS context: " + inContext);  
        context = inContext;  
    }  
    private List<Header> getHeaders() {
        MessageContext messageContext = context.getMessageContext();
        if (messageContext == null || !(messageContext instanceof WrappedMessageContext)) {
            return null;
        }
        Message message = ((WrappedMessageContext) messageContext).getWrappedMessage();
        return CastUtils.cast((List<?>) message.get(Header.HEADER_LIST));
    }

    private String getHeader(String name) {
    	log.debug("------- getHeaders -------");
        List<Header> headers = getHeaders();
        if (headers != null) {
            for (Header header : headers) {
            	Element el = (Element)header.getObject(); 
                log.debug(el.getTagName() + ":" + el.getTextContent());
                if ( name.equals(el.getTagName()) ){
                	return el.getTextContent();
                }
            }
        }
    	log.debug("------------------------");
        return null;
    }
}
