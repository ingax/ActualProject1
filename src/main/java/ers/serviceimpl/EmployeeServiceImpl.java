package ers.serviceimpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import ers.beans.Employees;
import ers.dao.EmployeeDao;
import ers.daoimpl.EmployeeDaoImpl;
import ers.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

	private final EmployeeDao eDao = new EmployeeDaoImpl();
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public List<Employees> getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		return eDao.getAllEmployees();
	}

	@Override
	public Employees getEmployeeById(HttpServletRequest request, HttpServletResponse response) {
			System.out.println("empservice");
			HttpSession session = request.getSession(false);
			String employeeById = (String) session.getAttribute("employeeName");
			System.out.println("employee in EmpServImp testing session " + employeeById);
			if(employeeById == null)
				return new Employees();
			
			return eDao.getEmployeeById(employeeById);
	}

	@Override
	public Employees createEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employees employee = mapper.readValue(request.getInputStream(), Employees.class);
			return eDao.createEmployee(employee);
		}catch(IOException e) {
			return null;
		}
	}

	@Override
	public Employees updateEmployee(HttpServletRequest request, HttpServletResponse response) {
		try {
			Employees employee = mapper.readValue(request.getInputStream(), Employees.class);
			return eDao.updateEmployee(employee);
		}catch(IOException e) {
			return null;
		}
	}
		

	@Override
	public long deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return 0;
	}

}
