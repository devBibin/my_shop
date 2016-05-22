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
 * Servlet implementation class ConfirmOrder
 */
@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB_Connector obj  =  new DB_Connector();
		HttpSession session = request.getSession();
		obj.connectDatabase();
		List<Good> goods = (List<Good>)session.getAttribute("basket");
		int count =goods.size();
		int[] orders = new int[count];
		orders = (int[])session.getAttribute("orders");
		String type;
		String money;
		String location;
		String card;
		
		if (request.getParameter("delivery") != null)
			type = "delivery";
		else
			type = "auto";
		
		if (request.getParameter("location") != null){
			location = request.getParameter("location");
			request.setAttribute("location", null);
		}
		else
			location = "NULL";
		
		if (request.getParameter("card").length() > 0){
			card = request.getParameter("card");
			money = "card";
		}
		else{
			card = "NULL";
			money = "cash";
		}
			
		
		for (int i = 0; i < count; i++){
			obj.ConfirmOrderAuto((int)session.getAttribute("pers_id"), goods.get(i).get_id(), orders[i], type+"/"+money , location, card);
		}
		
		session.setAttribute("alert", 5);
		session.setAttribute("basket", null);
		getServletContext().getRequestDispatcher("/MainPage").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
