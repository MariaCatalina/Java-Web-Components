package gestiune;

import java.util.ArrayList;

public class ReturnBooks {
	ArrayList<BorrowedBook> books;
	
	public ReturnBooks(){
		books = new ArrayList<BorrowedBook>();
	}
	
	public void setReturn(String userEmail, MyBook b){
		BorrowedBook bb = new BorrowedBook();
		bb.setBookB(userEmail, b);
		books.add(bb);
	}
	
	public ArrayList<BorrowedBook> getReturn(){
		return books;
	}
}

