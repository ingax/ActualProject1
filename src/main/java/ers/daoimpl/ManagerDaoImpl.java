package ers.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionMaker;
//import ers.beans.Employees;
import ers.beans.Managers;
import ers.dao.ManagerDao;

public class ManagerDaoImpl implements ManagerDao{
	public List<Managers> getAllManagers(){
		List<Managers> managers = new ArrayList<>();
		try (Connection conn = ConnectionMaker.getConnection()){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ers_managers");
			while(rs.next()) {
				managers.add(new Managers(rs.getString("manager_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("password")));
			}
			return managers;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return null;
	}
	public Managers getManagerById(String manager_id) {
		try (Connection conn = ConnectionMaker.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ers_managers WHERE id = ?" + manager_id);
			stmt.setString(1,  manager_id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new Managers(rs.getString("manager_id"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("password"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Managers createManager(Managers m) {
		try (Connection conn = ConnectionMaker.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO ers_managers VALUES (?, ?, ?");
			
			stmt.setString(1, m.getManager_id());
			stmt.setString(2, m.getFirst_name());
			stmt.setString(3, m.getLast_name());
			
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted == 1)
				return m;
		} catch (SQLException err) {
			err.printStackTrace();
		}
		return new Managers();
	}
	public Managers updateManager(Managers updateMngr) {
		return null;
	}
	public long deleteManager(Managers... deleteMngr) {
		return 0;
	}
}
