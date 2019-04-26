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
 * Servlet implementation class ManagerLoginServlet
 */
public class ManagerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final LoginService service = new LoginServiceImpl();
    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("mngrid");
		String password = request.getParameter("mngrpassword");
		
		if(username == null || username.equals("") || password == null || password.equals("")) {
			response.setContentType("text/json");
			response.getOutputStream().write(mapper.writeValueAsBytes("Please fill in credentials"));
			System.out.println("Null cred");
		}
		else if(service.checkManager(username, password)){
			HttpSession session = request.getSession();
			session.setAttribute("managerName", username);
			response.sendRedirect("ERS/managers.html");
			return;
		}
		else {
			response.getOutputStream().write(mapper.writeValueAsBytes("Invalid credentials"));
			System.out.println("Invalid");
			response.sendRedirect("homepage.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
