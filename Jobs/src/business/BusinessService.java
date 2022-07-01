package business;

import java.util.ArrayList;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import beans.User;
import beans.Work;
import database.DatabaseServiceInterface;

@Stateless
@Local
@Alternative
public class BusinessService implements BusinessServiceInterface {

	@Inject
	DatabaseServiceInterface db;
	
	@Override
	public int insertOne(Work wk) {
		
		return db.insertOne(wk);
	}

	@Override
	public int updateOne(int id, Work wk) {
		
		return db.updateOne(id, wk);
	}

	@Override
	public ArrayList<Work> readAll() {
		
		return db.readAll();
	}

	@Override
	public int deleteOne(int id) {
		
		return db.deleteOne(id);
	}

	@Override
	public boolean findConnection(String login, String pass) {
		
		return db.findConnection(login, pass);
	}

	@Override
	public int changePassword(User user) {

		return db.changePassword(user);
	}

}
