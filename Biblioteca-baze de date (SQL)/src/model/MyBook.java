package model;

import java.util.Random;

public class MyBook {

	private String autor;
	private String titlu;
	private int index;
	private int nrExemplare;
	private int nrExemplareImprumutate;

	/* initializari */
	public MyBook(){
		this.autor = new String();
		this.titlu = new String();
		this.nrExemplare = 0;
		this.nrExemplareImprumutate = 0;
	}

	public void setAutor(String nume){
		this.autor = nume;
	}

	public String getAutor(){
		return this.autor;
	}

	public void setTitlu(String titlu){
		this.titlu = titlu;
	}

	public String getTitlu(){
		return this.titlu;
	}

	/* metoda actualizează numarul de exemplare care se adaugă */
	public void setNrExemplare(int nr){
		this.nrExemplare = nr;
	}

	public int getNrExemplare(){
		return this.nrExemplare;
	}

	public void setIndex(int index){
		this.index = index;
	}

	public int getExemplare(){
		return this.nrExemplare;
	}

	/* metoda verifica dacă o carte poate fi împrumutată si actualizează numarul lor */
	public void setExImprumutate(){

		if(nrExemplare > nrExemplareImprumutate){
			this.nrExemplareImprumutate ++;
		}

	}

	public int getNrExemplareImprumutate(){
		return this.nrExemplareImprumutate;
	}

	/* metoda returnează index-ul la care a fost adaugata cartea */
	public int getIndex(){
		return index;
	}

	/* metoda actualizeaza numărul total exemplare care se șterg*/
	public void deleteB(){
		nrExemplare --;
	}

	/* metoda actualizează numărul de exemplare împrumutate */
	public void removeB(){
		nrExemplareImprumutate --;
	}

	/* metoda actualizeaza numarul de exemplare al cartilor duplicate */
	public void setDuplicat(int exemplare){
		this.nrExemplare += exemplare;
	}

	/* metoda inregistreaza o carte */
	public void setBook(String autor,String titlu,int index, int nrExemplare, int nrExemplareImprumutate){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);

		this.autor = autor;
		this.titlu = titlu;
		this.index = index;
		this.nrExemplare = nrExemplare;
		this.nrExemplareImprumutate = nrExemplareImprumutate;
	}
	
	/* metoda verifica daca string-ul introdus poate fi convertit in int */
	public boolean verificaNrExemplare(String numar){
		try {
			Integer.parseInt(numar);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
}
