package pack;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Filter
 */
@WebServlet("/Filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filter() {
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
		
		int low_price = Integer.parseInt(request.getParameter("low_price").toString());
		int high_price = Integer.parseInt(request.getParameter("high_price").toString());
		double low_rating = Double.parseDouble(request.getParameter("low_rate").toString());
		double high_rating = Double.parseDouble(request.getParameter("high_rate").toString());
		String company = request.getParameter("company").toString();
		
		obj.connectDatabase();
		
		request.setAttribute("headTag", obj.printHead((String) session.getAttribute("name")));
		
		if (Objects.equals("any", company))
			session.setAttribute("goods", obj.getFilteredGoodList(low_price, high_price, low_rating, high_rating, request.getParameter("type")));
		else
			session.setAttribute("goods", obj.getFilteredGoodList(low_price, high_price, low_rating, high_rating, request.getParameter("type"), company));
		
		getServletContext().getRequestDispatcher("/assortment.jsp").forward(request, response);
	}

}
