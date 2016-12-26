package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iba.dataModel.Study;

public class StudyDAO extends TemplateDAO{
	   public StudyDAO(){ 
	       table="STUDIES";
	       idCol="ID";
	       mainCols=new String[1];
	       mainCols[0]="NAME";
	       procType=3;
	    }
	@Override
	public Object getBean(String Id) throws DAOException {
		Connection connection = null;
		Study result = null;
		try{
			connection = getConnection();
			String sql = "SELECT NAME, HOURS, PROFESSOR_ID, AVG_MARK FROM LAPUSHA.STUDIES WHERE ID=?";
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result= new Study();
				result.setName(set.getString("NAME"));
				result.setId(Id);
				result.setHours(new Integer(set.getInt("HOURS")).toString());
				result.setProfessor_id(set.getString("PROFESSOR_ID"));
				result.setAvg_mark(set.getFloat("AVG_MARK"));
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
		Study study = (Study) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "UPDATE LAPUSHA.STUDIES SET NAME=?, HOURS=?, PROFESSOR_ID=? WHERE ID=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, study.getName());
			statement.setInt(2, Integer.parseInt(study.getHours()));
			statement.setString(3, study.getProfessor_id());
			statement.setString(4, study.getId());
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
		Study study = (Study) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "INSERT INTO LAPUSHA.STUDIES (NAME, HOURS, PROFESSOR_ID) VALUE (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, study.getName());
			statement.setInt(2, Integer.parseInt(study.getHours()));
			statement.setString(3, study.getProfessor_id());
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
