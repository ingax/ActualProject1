package ers.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMaker;
import ers.beans.Forms;
import ers.dao.FormsDao;


public class FormsDaoImpl implements FormsDao{
	public List<Forms> getAllForms(){
		List<Forms> forms = new ArrayList<>();
		try (Connection conn = ConnectionMaker.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ers_forms");
			while(rs.next()) {
				forms.add(new Forms(rs.getInt("form_id"),
						rs.getFloat("amount"),
						rs.getString("reason"), 
						rs.getString("decision"),
						rs.getString("employee_id"),
						rs.getString("manager_id")));
			}
			System.out.println("Printing forms: " + forms);
			return forms;
		}catch (SQLException err) {
			err.printStackTrace();
		}
		return null;
	}
	
	public Forms getFormById(int form_id) {
		try (Connection conn = ConnectionMaker.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ers_forms WHERE form_id = ?");
			stmt.setInt(1,  form_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new Forms (rs.getInt("form_id"), rs.getDouble("amount"),
						rs.getString("reason"), rs.getString("decisionMade"),
						rs.getString("employee_id"), rs.getString("manager_id"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Forms createForm(Forms f) {
		try(Connection conn = ConnectionMaker.getConnection()){
			System.out.println("Inside createform try block");
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ers_forms COLUMNS (form_id, amount, reason, employee_id,decision) VALUES (?, ?, ?, ?,'pending')");
			
			stmt.setInt(1, f.getForm_id());
			stmt.setDouble(2, f.getAmount());
			stmt.setString(3, f.getReason());
			stmt.setString(4, f.getEmployee_id());
			
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted == 1)
				return f;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return new Forms();
	}
	public Forms updateForm(Forms updateForm) {
		return null;
	}
	public long deleteForm(Forms... deleteForm) {
		return 0;
	}
}
