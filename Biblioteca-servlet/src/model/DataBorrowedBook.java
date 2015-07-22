package model;

import java.util.*;

public class DataBorrowedBook {
	private ArrayList<BorrowedBook> users;
	
	public DataBorrowedBook(){
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
	/**
	 * 
	 * metoda sterge un user din lista cand este apasat butonul returneaza
	 * @param index
	 */
	public void removeUser(int index){

		BorrowedBook book =  new BorrowedBook();
	
		for(BorrowedBook copy: users){
			if (copy.getBook().getIndex() == index){
				book = copy;
				break;
			}
		}
		users.remove(book);
	}
	
	/**
	 * metoda sorteaza o lista de carti imprumutate dupa data
	 * @param books
	 * @return
	 */
	public ArrayList<BorrowedBook> sortByDate(ArrayList<BorrowedBook> books){
		
		ArrayList<BorrowedBook> copy = new ArrayList<BorrowedBook>();
		
		copy = books;

		Collections.sort(copy, new Comparator<BorrowedBook>() {   
			@Override
			public int compare(BorrowedBook t1, BorrowedBook t2) {
				return t1.getDate().compareTo(t2.getDate());
			}
		});

		return copy;
	}
}
