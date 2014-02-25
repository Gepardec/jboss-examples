package test;
import javax.management.MXBean;


@MXBean
public interface HelloWorldServiceMBean
{
   public String getMessage();
   public void setMessage(String message);
   
   public void printMessage();
}
