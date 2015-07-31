package model;

public class Author {
	private String firstName;
	private String lastName;
	private int id;
	
	public Author(){
	}
	
	public Author(String firstName, String lastName, int id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}
