package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SendReport
 */
@WebServlet("/SendReport")
public class SendReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReport() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DB_Connector obj = new DB_Connector();
		
		obj.connectDatabase();
		
		if (session.getAttribute("pers_id") == null)
		{
			session.setAttribute("alert", 1);
		}
		else
		{
			session.setAttribute("alert", 4);
			int id = Integer.parseInt(session.getAttribute("pers_id").toString());
			int id_model = Integer.parseInt(session.getAttribute("model_id").toString());
			String author = session.getAttribute("login").toString();
			String report = request.getParameter("report").toString();
			obj.makeReport(id, report, id_model, author);
		}
		getServletContext().getRequestDispatcher("/AddOrder").forward(request, response);
	}

}
