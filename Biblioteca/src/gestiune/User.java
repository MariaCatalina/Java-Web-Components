package gestiune;
import java.util.*;

public class User {
	
	private String email;
	private ArrayList<BorrowedBook> borrowedBook;
	
	/* initializari */
	public User(){
		this.email = new String();
		this.borrowedBook = new ArrayList<BorrowedBook>();
	}
	
	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
	
	/*  */
	public void setBorroedBook(MyBook book){
		BorrowedBook b = new BorrowedBook();
		b.setBookB(book);
		
		this.borrowedBook.add(b);
	}
	
	/* returneaza lista de carti imprumutate */
	public ArrayList<BorrowedBook> getUserBorrowed(){
		return borrowedBook;
	}
}
