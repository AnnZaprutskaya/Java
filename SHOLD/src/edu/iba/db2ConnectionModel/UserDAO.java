package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import edu.iba.dataModel.User;

public class UserDAO extends TemplateDAO {
	public UserDAO() {
		table = "USERS";
		idCol = "\"USER\" AS USERNAME";
		mainCols = new String[1];
		mainCols[0] = "USERNAME";
		StringId = true;
	}

	@Override
	public Map<String,String> getList() throws DAOException{
		Map<String,String> result = new HashMap<String,String>();
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "SELECT \"USER\" AS USERNAME FROM LAPUSHA.USERS";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result.put(set.getString(mainCols[0]), set.getString(mainCols[0]));			
			}
			return result;
		} catch(SQLException e){
			throw new DAOException(e);
		} finally {
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	@Override
	public Object getBean(String Id) throws DAOException {
		Connection connection = null;
		User result = null;
		try {
			connection = getConnection();
			String sql = "SELECT ROLE FROM USERS WHERE \"USER\"=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				result = new User();
				result.setRole(set.getString("ROLE"));
				result.setUser(Id);
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public User login(String login, String password) throws DAOException {
		Connection connection = null;
		User result = null;
		try {
			connection = getConnection();
			String sql = "SELECT ROLE FROM LAPUSHA.USERS WHERE \"USER\" = ? AND PASSWORD = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				result = new User();
				result.setRole(set.getString("ROLE"));
				result.setUser(login);
			}
			return result;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public void setPassword(String Id, String password) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnection();
			String sql = "UPDATE USERS SET PASSWORD=? WHERE \"USER\"=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, password);
			statement.setString(2, Id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void edit(Object bean) throws DAOException {
		User user = (User) bean;
		Connection connection = null;
		try {
			connection = getConnection();
			String sql = "UPDATE USERS SET ROLE=? WHERE \"USER\"=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getRole());
			statement.setString(2, user.getUser());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public void create(Object bean) throws DAOException {
		User user = (User) bean;
		Connection connection = null;
		try {
			connection = getConnection();
			String sql = "INSERT INTO USERS (\"USER\", ROLE) VALUE (?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getUser());
			statement.setString(2, user.getRole());
			statement.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
	}

}
