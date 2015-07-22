package model;

import java.util.*;

public class Gestiune {

	ArrayList<MyBook> books;

	private int index;

	public Gestiune() {
		index = 1;
		books = new ArrayList<MyBook>();
	}

	/**
	 * @return structura de date ce conține lista de carți
	 */
	public ArrayList<MyBook> getList() {
		return books;
	}

	public int getIndex() {
		return index;
	}

	/**
	 * metoda adauga in lista cartea daca ca parametru
	 * cauta in lista de carti daca a mai fost aduagata si ii modifica nr de exemplare
	 * @param b - cartea care se adaugă
	 * @throws ConcurrentModificationException
	 */
	public void addB(MyBook b){
		MyBook copy = new MyBook();
		int gasit = 0;
		
		for(MyBook book : books){
			
			if(book.getAutor().equals(b.getAutor()) && book.getTitlu().equals(b.getTitlu())){
				copy = book;
				gasit = 1;
				break;
			}
		}
		/* daca cartea nu a mai fost adaugata */
		if(gasit == 0){
			books.add(b);
		}
		/* altfel modifica numarul de exemplare al cartii deja adugate */
		else{
			books.remove(copy);
			copy.setDuplicat(b.getExemplare());
			books.add(copy);
		}
	}

	/**
	 * metoda caută in listă cartea dupa index și o șterge
	 * @param autor
	 * @param titlu
	 */
	public void deleteB(int index) {

		MyBook b, copy;
		copy = new MyBook(); 
		Iterator<MyBook> it = books.iterator();

		while(it.hasNext()){
			b = new MyBook();
			b = (MyBook) it.next();
			
			if( b.getIndex() == index){
				copy = b;
				break;
			}
		}
		/* o carte imprumutata nu poate fi stearsa */
		if( copy.getNrExemplare() > 1 && (copy.getNrExemplare() - copy.getNrExemplareImprumutate() > 1)){
			books.remove(copy);
			copy.deleteB();
			books.add(copy);
		}
		else
			if(copy.getNrExemplare() == 1 && (copy.getNrExemplare() - copy.getNrExemplareImprumutate() == 1))
				books.remove(copy);
	}

	/**
	 * metoda actualizeaza datele cand este apasat butonul imprumuta
	 * @param index
	 */
	public void imprumuta(int index){

		MyBook b, copy;
		copy = new MyBook(); 
		Iterator<MyBook> it = books.iterator();
		while(it.hasNext()){
			b = new MyBook();
			b = (MyBook) it.next();
			if( b.getIndex() == index){
				copy = b;
				break;
			}
		}
		/* se verifica daca mai sunt exemplare care pot fi imprumutate */
		if(copy.getNrExemplare() > copy.getNrExemplareImprumutate()){
			books.remove(copy);
			copy.setExImprumutate();
			books.add(copy);
		}
	}

	/**
	 * metoda actualizeaza datele cand este apasat butonul returneaza
	 * @param index
	 */
	public void returneaza(int index){
		MyBook b, copy;
		copy = new MyBook(); 
		
		Iterator<MyBook> it = books.iterator();
		while(it.hasNext()){
			b = new MyBook();
			b = (MyBook) it.next();
			
			if( b.getIndex() == index){
				copy = b;
				break;
			}
		}

		if(copy.getNrExemplare() > copy.getNrExemplareImprumutate()){
			books.remove(copy);
			copy.removeB();
			books.add(copy);
		}
	}

	/**
	 * metoda sortează lista dupa autor
	 * @return lista de cărti sortată
	 */
	public ArrayList<MyBook> getSortByAutor(){

		ArrayList<MyBook> copy = new ArrayList<MyBook>();
		copy = books;

		Collections.sort(copy, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				return t1.getAutor().compareTo(t2.getAutor());
			}
		});

		return copy;
	}
	
	/**
	 * metoda sorteaza lista dupa titlu
	 * @return lista sortata
	 */
	public ArrayList<MyBook> getSortByTitlu(){

		ArrayList<MyBook> copy = new ArrayList<MyBook>();
		copy = books;

		Collections.sort(copy, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				return t1.getTitlu().compareTo(t2.getTitlu());
			}
		});

		return copy;
	}

}
