package at.gepardec.ejbtest.remote_ejb2;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface HelloObject extends EJBObject {
  public String sayHello() throws RemoteException;
}