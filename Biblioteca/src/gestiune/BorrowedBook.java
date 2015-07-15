package gestiune;

import java.sql.Date;
import java.text.*;

public class BorrowedBook {
	
	private String date;
	private MyBook book; 
	
	public BorrowedBook(){
		this.date = new String();
		this.book = new MyBook();
	}
	
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(MyBook book){
		/* determina ziua curenta */
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date dateT = new Date(0);
		
		this.book = book;
		this.date = dateFormat.format(dateT);;
	}
	
	/* returneaza un string cu datele cartii imprumutate */
	public String getBookB(){
		String out = new String();
		out = "Autor: " + book.getAutor() + "Titlu: " + book.getTitlu();
		out += "Data imprumutarii: " + this.date; 
		return out;
	}
}
