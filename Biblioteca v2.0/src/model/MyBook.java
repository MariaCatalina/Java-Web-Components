package model;


public class MyBook {

	private Author autor;
	private String titlu;
	private int index;
	private int nrExemplare;
	private int nrExemplareImprumutate;

	/* initializari */
	public MyBook(){

	}

	public void setAutor(Author a){
		this.autor = a;
	}

	public Author getAutor(){
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

	public int getNrExemplareImprumutate(){
		return this.nrExemplareImprumutate;
	}

	/* metoda returnează index-ul la care a fost adaugata cartea */
	public int getIndex(){
		return index;
	}
	
	/* metoda inregistreaza o carte */
	public void setBook(Author autor,String titlu,int index, int nrExemplare, int nrExemplareImprumutate){
		
		this.autor = autor;
		this.titlu = titlu;
		this.index = index;
		this.nrExemplare = nrExemplare;
		this.nrExemplareImprumutate = nrExemplareImprumutate;
	}
	
	/* metoda verifica daca string-ul introdus poate fi convertit in int */
	public boolean verifyNumber(String numar){
		try {
			Integer.parseInt(numar);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
}
