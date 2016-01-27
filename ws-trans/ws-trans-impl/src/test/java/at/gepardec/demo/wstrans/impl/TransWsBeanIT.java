package at.gepardec.demo.wstrans.impl;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.gepardec.demo.wstrans.TransWs;

@RunWith(Arquillian.class)
public class TransWsBeanIT {

	private final static Logger timer = LoggerFactory.getLogger(TransWsBeanIT.class.getName() + ".timer");

    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, TransWs.class.getPackage())
                .addPackages(true, TransWsBean.class.getPackage())
                .addAsWebInfResource("jboss-deployment-structure.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/jaxws-handlers-server.xml", "META-INF/jaxws-handlers-server.xml");
    }


    @EJB
    private EJBWrapper4TransWsBean ejbWrapper4TransWsBean;

    @Test
    public void testCallOk() throws Exception {
    	long start;
    	for ( int i=0; i<10; i++){
        	start = System.currentTimeMillis();
    		ejbWrapper4TransWsBean.testCallTransWsOk();
    		
    		timer.info("testCallOk Time: " + (System.currentTimeMillis() - start) + "ms");
    	}
    }
}