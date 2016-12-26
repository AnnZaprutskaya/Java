package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import edu.iba.dataModel.Professor;

public class ProfessorDAO extends TemplateDAO{
	   public ProfessorDAO() { 
	       table="PROFESSORS";
	       idCol="ID";
	       mainCols=new String[3];
	       mainCols[0]="SECOND_NAME";
	       mainCols[1]="FIRST_NAME";
	       mainCols[2]="FATHER_NAME";
	       procType=2;
	    }
	@Override
	public Object getBean(String Id) throws DAOException, ParseException {
		Connection connection = null;
		Professor result = null;
		try{
			connection = getConnection();
			String sql = "SELECT FIRST_NAME, SECOND_NAME, FATHER_NAME, AVG_MARK, BIRTH_DATE FROM LAPUSHA.PROFESSORS  WHERE ID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result= new Professor();
				result.setFirst_name(set.getString("FIRST_NAME"));
				result.setSecond_name(set.getString("SECOND_NAME"));
				result.setAvg_mark(set.getFloat("AVG_MARK"));
				result.setBirth_date(set.getDate("BIRTH_DATE").toString());
				result.setFather_name(set.getString("FATHER_NAME"));
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
		Professor professor = (Professor) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "UPDATE LAPUSHA.PROFESSORS SET FIRST_NAME=?, SECOND_NAME=?, FATHER_NAME=?, BIRTH_DATE=? WHERE ID=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, professor.getFirst_name());
			statement.setString(2, professor.getSecond_name());
			statement.setString(3, professor.getFather_name());
			statement.setDate(4, professor.getBirth_date());
			statement.setString(5, professor.getId());
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
		Professor professor = (Professor) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "INSERT INTO LAPUSHA.PROFESSORS (FIRST_NAME, SECOND_NAME, FATHER_NAME, BIRTH_DATE) VALUE (?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, professor.getFirst_name());
			statement.setString(2, professor.getSecond_name());
			statement.setString(3, professor.getFather_name());
			statement.setDate(4, professor.getBirth_date());
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
