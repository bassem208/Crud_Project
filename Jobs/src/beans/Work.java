package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Work {
	
	int id;
	String name;
	String description;
	int value;
	
	
	
	public Work() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Work(int id, String name, String description, int value) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	

}
