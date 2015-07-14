package gestiune;


public class MyBook {
	
	private String autor;
	private String titlu;
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
	
	public String getTitlu(){
		return this.titlu;
	}
	
	public void setTitlu(String titlu){
		this.titlu = titlu;
	}
	
	public int getExemplare(){
		return this.nrExemplare;
	}
	
	public int getExemplareImprumutate(){
		return this.nrExemplareImprumutate;
	}
	
	/* metoda inregistreaza o carte */
	public void setBook(String autor,String titlu, int nrExemplare, int nrExemplareImprumutate){
		this.autor = autor;
		this.titlu = titlu;
		this.nrExemplare = nrExemplare;
		this.nrExemplareImprumutate = nrExemplareImprumutate;
	}
	
	/* metoda returneaza o carte */
	public String getBook(){
		String out = new String();
		
		out = "Autor: " + this.autor + " Titlu: " + this.titlu;
		out += " Numar exemplare " + this.nrExemplare + " Numar exemplare imprumutate: " + this.nrExemplareImprumutate;
		
		return out;
	}
}
