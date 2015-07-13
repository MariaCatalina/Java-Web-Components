import java.util.*;

public class User {
	
	private String email;
	private String password;
	private ArrayList<BorrowedBook> borrowedBook;
	
	/* initializari */
	public User(){
		this.email = new String();
		this.password =  new String();
		this.borrowedBook = new ArrayList<BorrowedBook>();
	}
	
	/*  */
	public void setUser(String email,String password,ArrayList borrwedBook){
		this.email = email;
		this.password = password;
		this.borrowedBook = borrwedBook;
	}
	
	/* inregistreaza o noua carte */
	public void setBookBorrowed(String autor, String titlu, String data){
		BorrowedBook b = new BorrowedBook();
		b.setBookB(autor, titlu, data);
		this.borrowedBook.add(b);
	}
	
	/* returneaza lista de carti imprumutate */
	public ArrayList getUserBorrowed(){
		return borrowedBook;
	}
}
