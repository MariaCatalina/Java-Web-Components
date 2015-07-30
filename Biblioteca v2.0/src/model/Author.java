package model;

public class Author {
	private String firstName;
	private String lastName;
	private int index;
	
	public Author(){
	}
	
	public Author(String firstName, String lastName, int index){
		this.firstName = firstName;
		this.lastName = lastName;
		this.index = index;
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
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}
}
