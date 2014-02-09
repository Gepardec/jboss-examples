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

import at.objectbay.schema.test.svc.customerread_6_0.CustomerRead;
import at.objectbay.schema.test.svc.customerread_6_0.DtoAnschriftShort;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELI;
import at.objectbay.schema.test.svc.customerread_6_0.DtoCustomerLELO;





@Stateless
@WebService
public class CustomerReadServices implements CustomerRead {

    private static final Logger log = Logger.getLogger(CustomerReadServices.class);

    @Resource
	WebServiceContext context;

	@Override
	public DtoCustomerLELO customerRead(DtoCustomerLELI dtoCustomerLELI) {
		if ( context.getMessageContext() == null ){
			log.error("Null MessageContext in customerRead!");
		}
		String headerUser = getHeader("dummy");
		DtoCustomerLELO customer = new DtoCustomerLELO();
		DtoAnschriftShort anschrift = new DtoAnschriftShort();
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
			log.info("Null getUserPrincipal() in customerRead!");
			user = dtoCustomerLELI.getBeziehungsartShort();
			log.debugf("User, use dtoCustomerLELI.getBeziehungsartKurz: %s", user);
		}
		anschrift.setOrt(user);			
		customer.setDtoAnschriftShort(anschrift);

		return customer;
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
