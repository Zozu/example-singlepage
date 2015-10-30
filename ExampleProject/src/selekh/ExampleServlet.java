package selekh;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ExampleServlet")
public class ExampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public ExampleServlet()
	  {
	    super();
	  }

	//protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Controller.routeIt(request.getPathInfo(), request, response);
	}

}
