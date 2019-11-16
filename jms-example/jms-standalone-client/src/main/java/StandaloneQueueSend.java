import java.io.*;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;

// Based on a example from http://www.mastertheboss.com
public class StandaloneQueueSend
{
public final static String JNDI_FACTORY="org.jboss.naming.remote.client.InitialContextFactory";

public static void main(String[] args) throws Exception
  {
     if (args.length != 1) {
         System.out.println("Usage: java StandaloneQueueSend URL");
         System.out.println("Example:  java StandaloneQueueSend remote://192.168.105.133:4647");
         return;
     } 

     InitialContext ic = getInitialContext(args[0]);
     StandaloneQueueSend demo = new StandaloneQueueSend();

     System.out.println("\n\n\t *** Following shows Looking up a Primitive Datatype located in the JNDI ***");
     demo.init(ic,"/jms/queue/sampleQueue");
  }

private static InitialContext getInitialContext(String url) throws NamingException
     {
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);

        //*************** UserName & Password for the Initial Context for JNDI lookup *************************
        env.put(Context.SECURITY_PRINCIPAL, "guest");
        env.put(Context.SECURITY_CREDENTIALS, "guest@123");
        InitialContext ic=new InitialContext(env);
        System.out.println("\n\n\t Got InitialContext ic: "+ic);
        return ic;
     }

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

	public void init(Context ctx, String queueName)throws NamingException, JMSException
	{
		qconFactory = (QueueConnectionFactory) ctx.lookup("/jms/RemoteConnectionFactory");
		qcon = qconFactory.createQueueConnection("guest","guest@123");
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		msg = qsession.createTextMessage();
		qcon.start();

                for(int i=1;i<=100;i++)
                  {
                    String message="Hello Message - "+i+" at "+ new java.util.Date();
                    msg.setText(message);
                    System.out.println("\n\t Message Sent : "+message); 
                    qsender.send(msg);
                    try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                  }
               qcon.close();                
	}

  }
