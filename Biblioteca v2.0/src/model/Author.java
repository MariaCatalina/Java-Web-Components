package model;

public class Author {
	
	private String firstName;
	private String lastName;
	private int index;
	
	public Author(){
		firstName = new String();
		lastName = new String();
		index = 0;
	}
	
	public Author(String firstName, String lastName, int index){
		this.firstName = new String();
		this.firstName = firstName;
		
		this.lastName = new String();
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
