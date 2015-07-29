package model;

import java.util.Date;

public class BorrowedBook {
	
	private int index;
	private Date date;
	private MyBook book; 
	private User user;
	
	public BorrowedBook(){
		this.index = 0;
		this.date = new Date();
		this.book = new MyBook();
		this.user = new User();
	}
	
	public MyBook getBook(){
		return book;
	}
	
	public User getUser(){
		return user;
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getIndex(){
		return index;
	}
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(User user,MyBook book, int index, Date data){
	
		this.user = user;
		this.book = book;
		this.date = data;
		this.index = index;
	}
	
}
