package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iba.dataModel.Student;

public class StudentDAO extends TemplateDAO{
	   public StudentDAO() { 
	       table="STUDENTS";
	       idCol="ID";
	       mainCols=new String[2];
	       mainCols[0]="SECOND_NAME";
	       mainCols[1]="FIRST_NAME";
	       procType=1;
	    }
	@Override
	public Object getBean(String Id) throws DAOException {
		Connection connection = null;
		Student result = null;
		try{
			connection = getConnection();
			String sql = "SELECT FIRST_NAME, SECOND_NAME, AVG_MARK, GROUP_NUMBER FROM LAPUSHA.STUDENTS WHERE ID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result= new Student();
				result.setFirst_name(set.getString("FIRST_NAME"));
				result.setSecond_name(set.getString("SECOND_NAME"));
				result.setAvg_mark(set.getFloat("AVG_MARK"));
				result.setGroup_number(set.getString("GROUP_NUMBER"));
				result.setId(Id);
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
	public void edit(Object bean) throws DAOException {
		Student student = (Student) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "UPDATE LAPUSHA.STUDENTS SET FIRST_NAME=?, SECOND_NAME=?, GROUP_NUMBER=? WHERE ID=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, student.getFirst_name());
			statement.setString(2, student.getSecond_name());
			statement.setString(3, student.getGroup_number());
			statement.setString(4, student.getId());
			statement.executeUpdate();			
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
	public void create(Object bean) throws DAOException {
		Student student = (Student) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "INSERT INTO LAPUSHA.STUDENTS (FIRST_NAME, SECOND_NAME, GROUP_NUMBER) VALUE (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, student.getFirst_name());
			statement.setString(2, student.getSecond_name());
			statement.setString(3, student.getGroup_number());
			statement.execute();			
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
}













