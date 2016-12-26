package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import edu.iba.dataModel.Mark;

public class MarkDAO extends TemplateDAO{
	   public MarkDAO(){ 
	       table="MARKS";
	       idCol="ID";
	       mainCols=new String[1];
	       mainCols[0]="MARK";
	    }
	@Override
	public Object getBean(String Id) throws DAOException, ParseException {
		Connection connection = null;
		Mark result = null;
		try{
			connection = getConnection();
			String sql = "SELECT MARK, STUDY_ID, PROFESSOR_ID,STUDENT_ID, DATE, COMMENTS FROM LAPUSHA.MARKS WHERE ID=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result= new Mark();
				result.setMark(new Integer(set.getInt("MARK")).toString());
				result.setId(Id);
				result.setStudy_id(set.getString("STUDY_ID"));
				result.setProfessor_id(set.getString("PROFESSOR_ID"));
				result.setStudent_id(set.getString("STUDENT_ID"));
				result.setDate(set.getDate("DATE").toString());
				result.setComments(set.getString("COMMENTS"));
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

	}
	@Override
	public void create(Object bean) throws DAOException {
		Mark mark = (Mark) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "INSERT INTO LAPUSHA.MARKS (MARK, STUDY_ID, PROFESSOR_ID,STUDENT_ID, DATE, COMMENTS) VALUE (?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, mark.getMark());
			statement.setString(2, mark.getStudy_id());
			statement.setString(3, mark.getProfessor_id());
			statement.setString(4, mark.getStudent_id());
			statement.setDate(5, mark.getDate());
			statement.setString(6, mark.getComments());
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
