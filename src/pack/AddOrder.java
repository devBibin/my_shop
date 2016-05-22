package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddOrder
 */
@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DB_Connector obj  =  new DB_Connector();
		
		obj.connectDatabase();
		
		if (request.getParameter("id") != null)
			session.setAttribute("item", obj.getGood(Integer.parseInt(request.getParameter("id")))); // Если в первый раз берем из request
		else
			session.setAttribute("item", obj.getGood((int)session.getAttribute("model_id"))); // Если уже обращались, значит товар поменялся - берем из сессии
		
		if (request.getParameter("id") != null)
			session.setAttribute("model_id", Integer.parseInt(request.getParameter("id")));
		
		if(request.getParameter("add") != null)
			getServletContext().getRequestDispatcher("/AddBasket").forward(request, response);
		else
		{
			if (request.getParameter("model") != null)
				session.setAttribute("model", request.getParameter("model"));
			
			session.setAttribute("report_list", obj.getReportList((int)session.getAttribute("model_id")));
			request.setAttribute("headTag", obj.printHead("Изделие-" + session.getAttribute("model")));
			getServletContext().getRequestDispatcher("/shop_item.jsp").forward(request, response);
		}
	}

}
