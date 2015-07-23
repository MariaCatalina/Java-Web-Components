package model;

import java.text.*;
import java.util.Date;

public class BorrowedBook {
	
	private Date date;
	private MyBook book; 
	private String userEmail;
	
	public BorrowedBook(){
		this.date = new Date();
		this.book = new MyBook();
		this.userEmail = new String();
	}
	
	public MyBook getBook(){
		return book;
	}
	
	public String getUserEmail(){
		return userEmail;
	}
	
	public Date getDate(){
		return date;
	}
	
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(String email,MyBook book, Date data){
	
		this.userEmail = email;
		this.book = book;
		this.date = data;
	}
	
}
