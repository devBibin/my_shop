package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

/**
 * Servlet implementation class AddBasket
 */
@WebServlet("/AddBasket")
public class AddBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBasket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Good> goods;
		Good cur_good = (Good)session.getAttribute("item");
		if (session.getAttribute("basket") != null)
			goods = (List<Good>)session.getAttribute("basket");
		else
			goods = new ArrayList<Good>();
		
		if (session.getAttribute("pers_id") == null)
		{
			session.setAttribute("alert", 1);
		}
		else
		{
			goods.add((Good)session.getAttribute("item"));
			session.setAttribute("basket", goods);
		}
		response.sendRedirect("/shop/basket.jsp");
	}

}
