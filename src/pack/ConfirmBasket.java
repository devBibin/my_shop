package pack;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ConfirmBasket
 */
@WebServlet("/ConfirmBasket")
public class ConfirmBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmBasket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DB_Connector obj  =  new DB_Connector();
		HttpSession session = request.getSession();
		obj.connectDatabase();
		List<Good> goods = (List<Good>)session.getAttribute("basket");
		int count =goods.size();
		int[] orders = new int[count];
		for (int i = 0; i < count; i++){
			orders[i] = Integer.parseInt(request.getParameter("" + (i+1)).toString());
		}
		session.setAttribute("orders", orders);
		
		if (request.getParameter("delivery") != null){
			request.setAttribute("headTag", obj.printHead("Доставка"));
			getServletContext().getRequestDispatcher("/delivery.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("headTag", obj.printHead("Самовывоз"));
			getServletContext().getRequestDispatcher("/auto_delivery.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
