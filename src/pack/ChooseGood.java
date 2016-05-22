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
 * Servlet implementation class ChooseGood
 */
@WebServlet("/ChooseGood")
public class ChooseGood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseGood() {
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
		int index = Integer.parseInt(request.getParameter("company").toString());
		List<Stores> stores = obj.getStoresList(index);
        session.setAttribute("stores", stores);
        session.setAttribute("chosen_good", index);
        request.setAttribute("headTag", obj.printHead("Самовывоз"));
        getServletContext().getRequestDispatcher("/auto_delivery.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
