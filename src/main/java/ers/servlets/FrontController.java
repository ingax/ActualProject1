package ers.servlets;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ers.service.EmployeeService;
import ers.service.FormService;
import ers.service.ManagerService;
import ers.serviceimpl.EmployeeServiceImpl;
import ers.serviceimpl.FormServiceImpl;
import ers.serviceimpl.ManagerServiceImpl;

public class FrontController {

	private static final EmployeeService eService = new EmployeeServiceImpl();
	private static final ManagerService mService = new ManagerServiceImpl();
	private static final FormService fService = new FormServiceImpl();
	
	private FrontController() {}
	
	public static Object process(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in frontController get");
		final String uri = request.getRequestURI().replace("/ERS/api", "");
		System.out.println(uri);
		switch(uri) {
		case "/employees":
			return eService.getAllEmployees(request, response);
		case "/employeeID":
			return eService.getEmployeeById(request, response);
		case "/managers":
			return mService.getAllManagers(request, response);
		case "/forms":
			return fService.getAllForms(request, response);
		case "/getForms":
			return fService.getFormById(request, response); //Do session
		default:
			return Collections.singletonMap("message", "Employees not implemented");
		}
	}
	public static Object processPost (HttpServletRequest request, HttpServletResponse response) {
		final String uri = request.getRequestURI().replace("/ERS/api", "");
		System.out.println(uri);
		switch(uri) {
		case "/employees":
			return eService.createEmployee(request, response);
		case "/managers":
			return mService.createManager(request, response);
		case"/forms":
			return fService.createForm(request, response);
		default:
			return Collections.singletonMap("message", "Employees not implemented");
		}
	}
}
