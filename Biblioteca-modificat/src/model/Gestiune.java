package model;

import java.util.*;
import java.util.Map.Entry;

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
	 * metoda caută in listă, noua carte si o adaugă sau îi modifică numarul de exemplare 
	 * @param b - cartea care se adaugă
	 * @throws ConcurrentModificationException
	 */
	public void addB(MyBook b){
		books.add(b);
	}
	/**
	 * metoda caută in listă cartea dupa autor si titlu și o șterge
	 * @param autor
	 * @param titlu
	 * @return 1 - cartea are mai multe exemplare
	 * 		   2 - cartea mai are un singur exemplar
	 * 		   3 - cartea nu există
	 */
	public void deleteB(int index) {
		/* caut in lista si vad daca mai sunt exemplare
		 * daca = 1 se sterge cartea, daca > 1 se sterge doar un exemplar */
		MyBook b, copy;
		copy = new MyBook(); 
		Iterator it = books.iterator();
		while(it.hasNext()){
			b = new MyBook();
			b = (MyBook) it.next();
			if( b.getIndex() == index){
				copy = b;
				break;
			}
		}
		/* o carte imprumutata nu poate fi stearsa */
		if( copy.getNrExemplare() > 1 && (copy.getNrExemplare() - copy.getExemplareImprumutate() > 1)){
			books.remove(copy);
			copy.deleteB();
			books.add(copy);
		}
		else
			if(copy.getNrExemplare() == 1 && (copy.getNrExemplare() - copy.getExemplareImprumutate() == 1))
				books.remove(copy);
	}

	public void imprumuta(int index){

		MyBook b, copy;
		copy = new MyBook(); 
		Iterator it = books.iterator();
		while(it.hasNext()){
			b = new MyBook();
			b = (MyBook) it.next();
			if( b.getIndex() == index){
				copy = b;
				break;
			}
		}
		
		if(copy.getNrExemplare() > copy.getExemplareImprumutate()){
			books.remove(copy);
			copy.setExImprumutate();
			books.add(copy);
		}
		//caz in care nu mai sunt exemplare
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
