package at.gepardec.ejbtest.remote_ejb2;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class HelloBean implements SessionBean {
  private SessionContext sessionContext;
  public void ejbCreate() {
  }
  public void ejbRemove() {
  }
  public void ejbActivate() {
  }
  public void ejbPassivate() {
  }
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }
  public String sayHello() throws java.rmi.RemoteException {
    return "Hello World!!!!!";
  }
}