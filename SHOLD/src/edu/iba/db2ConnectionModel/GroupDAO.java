package edu.iba.db2ConnectionModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.iba.dataModel.Group;

public class GroupDAO extends TemplateDAO{
	public GroupDAO(){
	       table="GROUPS";
	       idCol="GROUP_NUMBER";
	       mainCols=new String[1];
	       mainCols[0]="GROUP_NUMBER";
	       StringId=true;
	    }
	@Override
	public Object getBean(String Id) throws DAOException {
		Connection connection = null;
		Group result = null;
		try{
			connection = getConnection();
			String sql = "SELECT AVG_MARK FROM LAPUSHA.GROUPS WHERE GROUP_NUMBER=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, Id);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result= new Group();
				result.setAvg_mark(set.getFloat("AVG_MARK"));
				result.setGroup_number(Id);
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
		Group group = (Group) bean;
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "INSERT INTO LAPUSHA.GROUPS (GROUP_NUMBER) VALUE (?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, group.getGroup_number());
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
