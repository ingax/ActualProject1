package ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.service.LoginService;
import ers.serviceimpl.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final LoginService service = new LoginServiceImpl();
	private static final ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("emplid");
		String password = request.getParameter("emplpassword");
		
		
		if(username == null || username == "" || password == null || password == "") {
			response.setContentType("text/json");
			response.getOutputStream().write(mapper.writeValueAsBytes("Please fill in credentials"));
		}
		
		else if(service.checkEmployee(username, password)){
			System.out.println("User passed back to EmployeeLoginServlet");
			HttpSession session = request.getSession();
			session.setAttribute("employeeName", username);
			response.sendRedirect("ERS/employees.html");
		}
		
		else {
			response.getOutputStream().write(mapper.writeValueAsBytes("Invalid credentials"));
			System.out.println("Invalid credentials");
			response.sendRedirect("homepage.html");
		}
	}

}
