package ers.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMaker;
import ers.beans.Employees;
import ers.dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao{
	
	public List<Employees> getAllEmployees(){
		List<Employees> employees = new ArrayList<>();
		try (Connection conn = ConnectionMaker.getConnection()){
			Statement stmt = conn.createStatement();
			System.out.println("Getting employees...");
			ResultSet rs = stmt.executeQuery("SELECT * FROM ers_employees");
			System.out.println("Selecting " + rs);
			while(rs.next()) {
				employees.add(new Employees(rs.getString("employee_id"), rs.getString("first_name"),
								rs.getString("last_name"), rs.getString("address"),
								rs.getLong("phone_number"), rs.getString("email"), rs.getString("password")));
			}
			System.out.println(employees);
			return employees;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return null;
	}
	
	public Employees getEmployeeById(String employee_id) {
			try (Connection conn = ConnectionMaker.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ers_employees WHERE employee_id = ?");
				stmt.setString(1, employee_id);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					return new Employees(rs.getString("employee_id"), rs.getString("first_name"),
							rs.getString("last_name"), rs.getString("address"),
							rs.getLong("phone_number"), rs.getString("email"), rs.getString("password"));
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	public Employees createEmployee(Employees e) {
		try (Connection conn = ConnectionMaker.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ers_employees VALUES (?, ?, ?, ?, ?, ?, ?");
			
			stmt.setString(1, e.getEmployee_Id());
			stmt.setString(2, e.getFirst_name());
			stmt.setString(3, e.getLast_name());
			stmt.setString(4, e.getAddress());
			stmt.setLong(5, e.getPhone_number());
			stmt.setString(6, e.getEmail());
			stmt.setString(7,  e.getPassword());
			
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted == 1)
				return e;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return new Employees();
	}
	
	public Employees updateEmployee(Employees updateEmp) {
		try(Connection conn = ConnectionMaker.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE TABLE ers_employees SET column-name = ? WHERE employee_id= " + updateEmp.getEmployee_Id());
			stmt.setString(1, updateEmp.getEmployee_Id());
			stmt.setString(2, updateEmp.getFirst_name());
			stmt.setString(3, updateEmp.getLast_name());
			stmt.setString(4, updateEmp.getAddress());
			stmt.setLong(5, updateEmp.getPhone_number());
			stmt.setString(6, updateEmp.getEmail());
			stmt.setString(7,  updateEmp.getPassword());
			
			int rowsUpdated = stmt.executeUpdate();
			if(rowsUpdated == 1)
				return updateEmp;
		}catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}	
		return null;
	}
	
	public long deleteEmployee(Employees... deleteEmp) {
		return 0;
	}
	
}