package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Assortment_Controller
 */
@WebServlet("/Assortment_Controller")
public class Assortment_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assortment_Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DB_Connector obj = new DB_Connector();
		HttpSession session = request.getSession();
		session.setAttribute("name", request.getParameter("type"));
		
		obj.connectDatabase();
		
		request.setAttribute("headTag", obj.printHead((String) session.getAttribute("name")));
		
		session.setAttribute("goods", obj.getGoodList(request.getParameter("type")));
		getServletContext().getRequestDispatcher("/assortment.jsp").forward(request, response);
	}

}
