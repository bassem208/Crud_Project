package controllers;



import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import beans.Work;
import business.BusinessServiceInterface;


@ManagedBean
public class FormController {
	
	@Inject
	BusinessServiceInterface bs;
	
	public String submit() {
		
		FacesContext context =FacesContext.getCurrentInstance();
		Work wk =context.getApplication().evaluateExpressionGet(context, "#{work}", Work.class);
		
		int a =bs.insertOne(wk);
		
		return "display.xhtml";
	}
	
	public void onDelete(Work wk) {
		
		bs.deleteOne(wk.getId());
	}
	
	public String updateOne(Work wk) {
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("work", wk);

		return "edit.xhtml";
	}
	
	public String edit() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		Work wk = context.getApplication().evaluateExpressionGet(context, "#{work}", Work.class);
		
		bs.updateOne(wk.getId(), wk);
		
		return "display.xhtml";
		
		
	}
	
	public ArrayList<Work> getAll(){
		
		ArrayList<Work> wkList = new ArrayList<Work>();
		wkList = bs.readAll();
		return wkList;
	
	}
	
	public String connection() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		boolean checkUser= bs.findConnection(user.getLogin(), user.getPassword());
		
		if(checkUser==true) {
			return "display.xhtml";

		}else {
			FacesMessage msg = new FacesMessage("Check you Login or Password!");
			context.addMessage("connectionForm:password", msg);
			 return null;
		}
		
		
		
	}
	
	public String changePass() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		if(user.getNewPass().equals(user.getConfirmPass())) {
			
			int a = bs.changePassword(user);
			if(a==1) {
				return "connection.xhtml";
			}else {
				FacesMessage msg = new FacesMessage("check you login or your password!");
				context.addMessage("changeForm:password", msg);
			}
		}
		return null;
	}
	
	public void confirmPass() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		if(user.getNewPass().equals(user.getConfirmPass())){
			FacesMessage msg = new FacesMessage("login and password are confirmed");
			context.addMessage("changeForm:confirmPassword", msg);
		}
		FacesMessage message = new FacesMessage("your new password is not confirm!");
		context.addMessage("changeForm:confirmPassword", message);
	}
	
	public String showInsert() {
		return "insert.xhtml";
	}
	
	public String showChange() {
		return "changePassword.xhtml";
	}
	
	public String backView() {
		return "connection.xhtml";
	}
}
