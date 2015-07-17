package model;

import java.util.*;

public class DataTable {
	private ArrayList<BorrowedBook> users;
	
	public DataTable(){
		users = new ArrayList<BorrowedBook>();
	}
	
	public ArrayList<BorrowedBook> getUser(){
		return users;
	}
	
	/**
	 * metoda adaugă o înregistrare la lista de cărți împrumutate
	 * @param userEmail
	 * @param table
	 * @param index
	 */
	public void addUser(String userEmail,ArrayList<MyBook> books, int index){
		
		MyBook book =  new MyBook();
		BorrowedBook b = new BorrowedBook();
		
		for(MyBook copy : books){
			if(copy.getIndex() == index){
				book = copy;
				break;
			}
		}
		
		b.setBookB(userEmail, book);
		users.add(b);
	}
	
	
	public BorrowedBook getBBook(MyBook b){
		Iterator<BorrowedBook> it = users.iterator();
		BorrowedBook copy ;
		
		while(it.hasNext()){
			copy = new BorrowedBook();
				if(copy.getBook().equalBook(b)){
					return copy;
				}
		}
		return null;
		
	}
	
	/**
	 * metoda șterge cartea din lista de carți împrumutate
	 * @param b
	 */
	public synchronized void removeReturn(MyBook b){
	
		BorrowedBook copy , r;
		r = new BorrowedBook();
	
		Iterator<BorrowedBook> it = users.iterator();
		/* caută în lista cartea selectată */
		while(it.hasNext()){
			copy = new BorrowedBook();
			copy = it.next();
			if(copy.getBook().equalBook(b)){
				r = copy;
			}
		}
		users.remove(r);
	}

}
