package model;

import java.text.*;
import java.util.Date;

public class BorrowedBook {
	
	private String date;
	private MyBook book; 
	private String userEmail;
	
	public BorrowedBook(){
		this.date = new String();
		this.book = new MyBook();
		this.userEmail = new String();
	}
	
	public MyBook getBook(){
		return book;
	}
	
	public String getUserEmail(){
		return userEmail;
	}
	
	public String getDate(){
		return date;
	}
	
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(String email,MyBook book){
		/* determina ziua curenta */
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateT = new Date();
		
		this.userEmail = email;
		this.book = book;
		this.date = dateFormat.format(dateT);;
	}
	
}
