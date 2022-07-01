package business;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.User;
import beans.Work;


public interface BusinessServiceInterface {
	
	public int insertOne(Work wk);
	public int updateOne(int id, Work wk);
	public ArrayList<Work> readAll();
	public int deleteOne(int id);
	public boolean findConnection(String login, String pass);
	public int changePassword(User user);


}
