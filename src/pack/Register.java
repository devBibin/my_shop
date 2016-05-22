package pack;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 	 HttpSession session = request.getSession();
		 String name = request.getParameter("name").toString();
		 String surname = request.getParameter("surname").toString();
		 String second_name = request.getParameter("sec_name").toString();
		 String phone = request.getParameter("phone");
		 String mail = request.getParameter("mail");
		 String password = request.getParameter("password");
		 
		 String[] results = request.getParameterValues("sex");
		 String sex = new String();
		 for (int i = 0; i < results.length; i++)
			 if (results[i] != null)
				 sex = results[i];
		
		 Person pers= new Person(name, second_name, surname, sex, phone, mail, password);
		
		 DB_Connector obj = new DB_Connector();
		 obj.connectDatabase();
		 obj.RegisterNewUser(pers);
		 
		 session.setAttribute("registed", true);
		 response.sendRedirect("MainPage");
	}

}
