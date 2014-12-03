package at.gepardec.ejbtest.remote_ejb2;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloHome extends EJBHome {
  public HelloObject create() throws RemoteException, CreateException;
}