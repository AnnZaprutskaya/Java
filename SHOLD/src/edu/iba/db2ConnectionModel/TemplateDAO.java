package edu.iba.db2ConnectionModel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public abstract class TemplateDAO {
	protected String table="";
	protected boolean StringId=false;
	protected String idCol="";
	protected Integer procType = null;
	protected String mainCols[]=null;
	protected  String subCols[]=null;
	protected final Connection getConnection() throws DAOException{
		try{
			InitialContext context = new InitialContext();
			DataSource source = (DataSource)context.lookup("jcc/Student_Helper_DB");
			return source.getConnection();
		}
		catch (NamingException e){
			throw new DAOException(e);
		} catch (SQLException e){
			throw new DAOException(e);
		} 
	}
	public void recountAvgMark() throws DAOException {
		Connection connection = null;

		try {
			connection = getConnection();
			String SQL = "{call UPDATE_AVGMARK_NATIVE_SQL (?)}";
			CallableStatement cStmt = connection.prepareCall(SQL);
			cStmt.setInt(1, procType);
			
			boolean hadResults = cStmt.execute();
			
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public Map<String,String> getList() throws DAOException{
		Map<String,String> result = new HashMap<String,String>();
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "SELECT "+idCol;
			for (int i = 0; i < mainCols.length; i++) {
				if (!mainCols[i].equals(idCol)){
					sql = sql +", "+ mainCols[i];
				}
			}
			sql=sql+" FROM LAPUSHA."+table+";";
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				String value="";
				for(int i=0;i<mainCols.length;i++){
					System.out.println(mainCols[i]);
					value=value+set.getString(mainCols[i]);
					if(i!=mainCols.length-1){
						value=value+" ";
					}
				}
				result.put(set.getString(idCol), value);			
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
	public final String getMainValue(String id) throws DAOException{
		String result = null;
		Connection connection = null;
		if(StringId){
			id="'"+id+"'";
		}
		try{
			connection = getConnection();
			String sql = "SELECT "+idCol;
			for (int i = 0; i < mainCols.length; i++) {
				if (!mainCols[i].replaceAll("\"", "").equals(idCol.replaceAll("\"", ""))){
					sql = sql +", "+ mainCols[i];
				}
			}
			sql=sql+" FROM LAPUSHA."+table+" WHERE "+ idCol+ " = "+id+";";
			PreparedStatement statement = connection.prepareStatement(sql);
			System.out.println(sql);
			ResultSet set = statement.executeQuery();	
			while(set.next()){
				result="";
				for(int i=0;i<mainCols.length;i++){
					result=result+set.getString(mainCols[i]);
					if(i!=mainCols.length-1){
						result=result+" ";
					}
				}			
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
	public abstract Object getBean(String Id) throws DAOException, ParseException;
	public abstract void create(Object bean) throws DAOException;
	public final void delete(String Id) throws DAOException{
		Connection connection = null;
		try{
			connection = getConnection();
			String sql = "DELETE FROM LAPUSHA."+table+ " WHERE "+idCol+"="+Id+";";
			PreparedStatement statement = connection.prepareStatement(sql);
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
	public abstract void edit(Object bean) throws DAOException;
}
