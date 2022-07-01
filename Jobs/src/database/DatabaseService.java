package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;
import beans.Work;


@Stateless
@Local
@Alternative
public class DatabaseService implements DatabaseServiceInterface {
	
	String URL= "jdbc:mysql://localhost:3306/beautifulworks";
	String user= "root";
	String password= "root";
	
	@Override
	public int insertOne(Work wk) {
		
		Connection c = null;
		PreparedStatement st = null;
		int rows = 0;
		
		try {
			c= DriverManager.getConnection(URL, user, password);
			st= c.prepareStatement("insert into beautifulworks.work_table values(null,?,?,?)");
			st.setString(1, wk.getName());
			st.setString(2, wk.getDescription());
			st.setInt(3, wk.getValue());
			
			rows = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public int updateOne(int id, Work wk) {

		Connection c = null;
		PreparedStatement st = null;
		int rows = 0;
		
		try {
			c= DriverManager.getConnection(URL, user, password);
			st= c.prepareStatement("update beautifulworks.work_table set work_name=?,work_description=?,work_value=? where id=?");
			st.setString(1, wk.getName());
			st.setString(2, wk.getDescription());
			st.setInt(3, wk.getValue());
			st.setInt(4, id);
			
			rows = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public ArrayList<Work> readAll() {
		
		Connection c = null;
		Statement st = null;
		ResultSet rs= null;
		ArrayList<Work> list = new ArrayList<Work>();
		try {
			c= DriverManager.getConnection(URL, user, password);
			st= c.createStatement();
			rs= st.executeQuery("select * from beautifulworks.work_table");
			
			while(rs.next()){
				Work wk= new Work(rs.getInt("id"), rs.getString("work_name"), rs.getString("work_description"), rs.getInt("work_value"));
				list.add(wk);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public int deleteOne(int id) {
		
		Connection c = null;
		PreparedStatement st = null;
		int rows = 0;
		
		try {
			c= DriverManager.getConnection(URL, user, password);
			st= c.prepareStatement("delete from beautifulworks.work_table where id=?");
			
			st.setInt(1, id);
			
			rows = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public boolean findConnection(String login, String pass) {
		
		Connection c =null;
		PreparedStatement st =null;
		ResultSet rs=null;
		boolean checkUser =false;
		
		try {
			c = DriverManager.getConnection(URL, user, password);
			st =c.prepareStatement("select * from beautifulworks.user_table where login=? and password=?");
			st.setString(1, login);
			st.setString(2, pass);
			
			rs = st.executeQuery();
			while(rs.next()) {
				checkUser =true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return checkUser;
	}

	@Override
	public int changePassword(User usr) {
		Connection c=null;
		PreparedStatement st =null;
		int rows=0;
		
		try {
			c= DriverManager.getConnection(URL, user, password);
			st =c.prepareStatement("update beautifulworks.user_table set password=? where login =? and password=?" );
			st.setString(1, usr.getNewPass());
			st.setString(2, usr.getLogin());
			st.setString(3, usr.getPassword());
			
			rows=st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				c.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	
	

}
