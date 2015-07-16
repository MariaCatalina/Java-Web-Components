package gestiune;


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
		this.index = 0;
		this.nrExemplare = 0;
		this.nrExemplareImprumutate = 0;
	}
	
	public void setAutor(String nume){
		this.autor = nume;
	}
	
	public String getAutor(){
		return this.autor;
	}
	
	public String getTitlu(){
		return this.titlu;
	}
	
	public void setTitlu(String titlu){
		this.titlu = titlu;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getExemplare(){
		return this.nrExemplare;
	}
	
	public int getExemplareImprumutate(){
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
	
	/* metoda actualizează numarul de exemplare care se adaugă */
	public void setNrExemplare(){
		this.nrExemplare++;
	}
	
	/* metoda verifica dacă o carte poate fi împrumutată si actualizează numarul lor */
	public int setExImprumutate(){
		if(nrExemplare > nrExemplareImprumutate){
			this.nrExemplareImprumutate ++;
			return 1;
		}
		else
			return 0;
	}
	
	/* metoda comparea doua cărți */
	public boolean equalBook(MyBook b){
		if(this.getAutor().equals(b.getAutor()) && this.getTitlu().equals(b.getTitlu()))
			return true;
		
		return false;
	}
	
	/* metoda inregistreaza o carte */
	public void setBook(String autor,String titlu, int nrExemplare, int nrExemplareImprumutate){
		this.autor = autor;
		this.titlu = titlu;
		this.index ++;
		this.nrExemplare = nrExemplare;
		this.nrExemplareImprumutate = nrExemplareImprumutate;
	}
}
