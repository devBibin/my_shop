package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Authorization
 */
@WebServlet("/Authorization")
public class Authorization extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authorization() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        HttpSession session = request.getSession();
		DB_Connector obj = new DB_Connector();
		int id;
		String login = request.getParameter("login").toString();
		String pass = request.getParameter("password").toString();
		
		obj.connectDatabase();
	
		
		request.setAttribute("headTag", obj.printHead("Главная"));
		if ((id = obj.loginUser(login, pass)) != 0)
		{
			session.setAttribute("login", login);
			session.setAttribute("password", pass);
			session.setAttribute("pers_id", id);
			getServletContext().getRequestDispatcher("/main_page.jsp").forward(request, response);
		}
		else
		{
			String exec_java = "<script> alert(\"Пользователь не обнаружен\") </script>";
			String red_java = "<meta http-equiv=\"refresh\" content=\"0; url=/shop\" />";
			response.getWriter().write(exec_java + red_java);		
		} 
	}

}
