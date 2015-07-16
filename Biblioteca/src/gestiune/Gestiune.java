package gestiune;

import java.util.*;
import java.util.Map.Entry;

public class Gestiune {

	Hashtable<Integer, MyBook> books;

	private int index;

	public Gestiune() {
		index = 1;
		books = new Hashtable<Integer, MyBook>();
	}

	/**
	 * @return structura de date ce conține lista de carți
	 */
	public Hashtable getList() {
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
	public synchronized void addB(MyBook b){

		MyBook copy, addC, sameAutor;
		int gasit = 0;

		addC =  new MyBook();
		sameAutor = new MyBook();

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
					 * daca cartea a fost deja adăugata se face +1 la numarul de
					 * exemplare
					 */
					copy.setNrExemplare();
					addC = copy;
				}
				/* cartea are același autor dar cu titlu diferit, se adaugă in mod separat*/
				else {
					gasit = 2;
					b.setIndex(index);
					sameAutor = b;
					index ++;
				}
			} 
		}
		/* cartea nu a fost adăugata până acum */
		if(gasit == 0){
			b.setIndex(index);
			books.put(index, b);
			index ++;

		}
		else if (gasit == 1){
			books.put(addC.getIndex(), addC);
		}
		else if(gasit == 2){
			books.put(sameAutor.getIndex(), sameAutor);

		}
	}
	/**
	 * metoda caută in listă cartea dupa autor si titlu și o șterge
	 * @param autor
	 * @param titlu
	 * @return 1 - cartea are mai multe exemplare
	 * 		   2 - cartea mai are un singur exemplar
	 * 		   3 - cartea nu există
	 */
	public synchronized int deleteB(String autor, String titlu) {
		MyBook copy, b, cauta;
		int gasit = 0;
		cauta = new MyBook();

		/* se face cautare in lista */

		for (Integer c : books.keySet()) {
			b = new MyBook();
			b = books.get(c);

			/* daca a gasit cartea */
			if (b.getAutor().equals(autor) && b.getTitlu().equals(titlu)) {

				/* o carte împrumutată nu poate fi ștearsa */
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

	/**
	 * metoda sortează lista dupa autor
	 * @return lista de cărti sortată
	 */
	public LinkedList<MyBook> getSortByAutor(){

		LinkedList<MyBook> list = new LinkedList<MyBook>();

		/* copiază în lista cartile din structura de date */
		for(Integer i : books.keySet()){
			list.add(books.get(i));
		}

		Collections.sort(list, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				return t1.getAutor().compareTo(t2.getAutor());
			}
		});

		return list;
	}

	/**
	 * metoda sortează lista de cărti dupa titlu
	 * @return lista sortată
	 */
	public LinkedList<MyBook> getSortByTitlu(){

		LinkedList<MyBook> list = new LinkedList<MyBook>();

		/* copiază în lista cartile din structura de date */
		for(Integer i : books.keySet()){
			list.add(books.get(i));
		}

		Collections.sort(list, new Comparator<MyBook>() {   
			@Override
			public int compare(MyBook t1, MyBook t2) {
				return t1.getTitlu().compareTo(t2.getTitlu());
			}
		});

		return list;
	}
}
