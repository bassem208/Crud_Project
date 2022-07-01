package database;

import java.util.ArrayList;

import javax.ejb.Local;

import beans.User;
import beans.Work;

@Local
public interface DatabaseServiceInterface {
	
	public int insertOne(Work wk);
	public int updateOne(int id, Work wk);
	public ArrayList<Work> readAll();
	public int deleteOne(int id);
	public boolean findConnection(String login, String password);
	public int changePassword(User user);
}
