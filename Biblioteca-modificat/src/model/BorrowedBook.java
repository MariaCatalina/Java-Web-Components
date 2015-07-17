package model;

import java.sql.Date;
import java.text.*;
import java.util.Calendar;

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
	//	Date dateT = new Date();
		Calendar cal = Calendar.getInstance();
		this.userEmail = email;
		this.book = book;
		this.date = dateFormat.format(cal.getTime());;
	}
	
	/* returneaza un string cu datele cartii imprumutate */
	public String getBookB(){
		String out = new String();
		out = "Autor: " + book.getAutor() + "Titlu: " + book.getTitlu();
		out += "Data imprumutarii: " + this.date; 
		return out;
	}
}
