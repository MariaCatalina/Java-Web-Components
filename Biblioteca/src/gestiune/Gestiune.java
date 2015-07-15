package gestiune;

import java.util.*;
import java.util.Map.Entry;

public class Gestiune {

	Hashtable<Integer, MyBook> books;
	ArrayList<User> users;
	private int index;

	public Gestiune() {
		index = 1;
		books = new Hashtable<Integer, MyBook>();
		users = new ArrayList<User>();
	}

	public void addB(MyBook b) throws ConcurrentModificationException {

		MyBook copy;
		int gasit = 0;

		/* se cauta in lista de carti daca se mai gaseste un exemplar */
		Set<Integer> keys = books.keySet();
		Iterator<Integer> it = keys.iterator();
		
		while(it.hasNext()){
			
			copy = new MyBook();
			copy = books.get(it.next());

			if(copy.getAutor().equals(b.getAutor())) {
				if (copy.getTitlu().equals(b.getTitlu())) {
					gasit = 1;
					books.remove(copy.getIndex());
					/*
					 * daca cartea a fost deja adaugata se face +1 la numarul de
					 * exemplare
					 */
					copy.setNrExemplare();
					books.put(copy.getIndex(), copy);

				}
				/* cartea are acelasi autor dar cu titlu diferit */
				else {
					b.setIndex(index);
					books.put(index, b);
					index ++;
				}
			} 
		}
		/* cartea nu a fost adaugata pana acum */
		if(gasit == 0){
			b.setIndex(index);
			books.put(index, b);
			index ++;

		}
	}

	public int deleteB(String autor, String titlu) {
		MyBook copy, b, cauta;
		int gasit = 0;
		cauta = new MyBook();
		/*
		 * daca cartea are mai mutle exemplare - return 1 
		 * un singur exemplar - return 2 
		 * cartea nu exista - return 3
		 */

		/* se face cautare in lista */

		for (Integer c : books.keySet()) {
			b = new MyBook();
			b = books.get(c);

			/* daca a gasit cartea */
			if (b.getAutor().equals(autor) && b.getTitlu().equals(titlu)) {

				/* o carte imprimutata nu poate fi stearsa */
				if(b.getExemplareImprumutate() < b.getExemplare()){
					gasit = 1;
					/* il sterg din lista */
					books.remove(b.getIndex());

					/* modifica numarul de exemplare */
					b.deleteB();

					if (b.getExemplare() > 0) {
						/* se aduaga cartea cu numarul de exemplare modificat */
						books.put(b.getIndex(), b);
						return 1;

					} else if (b.getExemplare() == 0)
						return 2;
				}
			}
		}
		if(gasit == 0)
			return 3;
		return 0;
	}

	public Hashtable getList() {
		return books;
	}

	public int getIndex() {
		return index;
	}

	
	public LinkedList<MyBook> getSortByAutor(){
		
		LinkedList<MyBook> list = new LinkedList<MyBook>();
		//list = (ArrayList<MyBook>) books.elements();
		
		for(Integer i : books.keySet()){
			list.add(books.get(i));
		}
		
		Collections.sort(list, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				// TODO Auto-generated method stub
			      return t1.getAutor().compareTo(t2.getAutor());
			}
		});
	
		return list;
	}
	
	
	public LinkedList<MyBook> getSortByTitlu(){
		
		LinkedList<MyBook> list = new LinkedList<MyBook>();
		//list = (ArrayList<MyBook>) books.elements();
		
		for(Integer i : books.keySet()){
			list.add(books.get(i));
		}
		
		Collections.sort(list, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				// TODO Auto-generated method stub
			      return t1.getTitlu().compareTo(t2.getTitlu());
			}
		});
	
		return list;
	}
	
	public String print() {
		String s = new String();
		MyBook b;
		for (Integer copy : books.keySet()) {
			b = new MyBook();
			b = books.get(copy);
			s += b.getBook() + "\n";
		}
		return s;
	}
}
