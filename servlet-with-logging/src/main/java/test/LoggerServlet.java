package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(urlPatterns = "/log")
public class LoggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(LoggerServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>LoggingExample</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

        out.println("Schreibe Error<br/>");
        log.error("Error von LoggingServlet");
        
        out.println("Schreibe Warning<br/>");
        log.warn("Warning von LoggingServlet");
        
        out.println("Schreibe Info<br/>");
        log.info("Info von LoggingServlet");
        
        out.println("Schreibe Debug<br/>");
        log.debug("Debug von LoggingServlet");
        
        out.println("Schreibe Tracr<br/>");
        log.trace("Trace von LoggingServlet");
        

		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}
}
