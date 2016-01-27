package at.gepardec.demo.wstrans.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/callws")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private EJBWrapper4TransWsBean svc;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int count = 1;
		try {
			count = Integer.parseInt(request.getParameter("count"));
		} catch (NumberFormatException e) {
			// I don't care
		}

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Call Webservice</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

		out.println("Rufe <a href=\"callws?count="+count+"\">callws?cout="+count+"</a><br/>");

		String wsUrl = request.getRequestURL().substring(0, request.getRequestURL().lastIndexOf("/") + 1) + "TransWs";
		// wsUrl = "http://localhost:8080/transws/TransWs";
		while (count-- > 0) {
			try {
	        	long start = System.currentTimeMillis();
				svc.callWs(wsUrl);
				out.println("testCallOk Time: " + (System.currentTimeMillis() - start) + "ms<br/>");

			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
