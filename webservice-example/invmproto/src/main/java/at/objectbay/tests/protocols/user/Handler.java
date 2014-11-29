package at.objectbay.tests.protocols.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
public class Handler extends URLStreamHandler {
  @Override
  protected URLConnection openConnection(URL url) throws IOException {
    return new UserURLConnection(url);
  }
  private static class UserURLConnection extends URLConnection {
    private String fileName;
    public UserURLConnection(URL url) {
      super(url);
      fileName = url.getPath();
    }
    @Override
    public void connect() throws IOException {
    }
    @Override
    public InputStream getInputStream() throws IOException {
      File absolutePath = new File(System.getProperty("user.home"), fileName);
      return new FileInputStream(absolutePath);
    }
  }
}