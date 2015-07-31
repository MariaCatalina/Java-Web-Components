package model;


public class MyBook {

	private Author autor;
	private String title;
	private int id;
	private int noCopies;
	private int noBorrowedCopies;

	public MyBook(){
	}

	public void setAutor(Author a){
		this.autor = a;
	}

	public Author getAutor(){
		return this.autor;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setNoCopies(int nr){
		this.noCopies = nr;
	}

	public int getNoCopies(){
		return this.noCopies;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
	
	public void setNoBorrowedCopies(int noBorrowedCopies){
		this.noBorrowedCopies = noBorrowedCopies;
	}
	
	public int getNoBorrowedCopies(){
		return this.noBorrowedCopies;
	}

	/* metoda inregistreaza o carte */
	public void setBook(Author autor,String titlu,int index, int nrExemplare, int nrExemplareImprumutate){
		
		this.autor = autor;
		this.title = titlu;
		this.id = index;
		this.noCopies = nrExemplare;
		this.noBorrowedCopies = nrExemplareImprumutate;
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
