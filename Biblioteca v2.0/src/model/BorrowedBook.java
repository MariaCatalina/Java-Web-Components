package model;

import java.util.Date;

public class BorrowedBook {
	
	private int index;
	private Date date;
	private MyBook book; 
	private String userEmail;
	
	public BorrowedBook(){
		this.index = 0;
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
	
	public int getIndex(){
		return index;
	}
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(String email,MyBook book, Date data, int index){
	
		this.userEmail = email;
		this.book = book;
		this.date = data;
		this.index = index;
	}
	
}
