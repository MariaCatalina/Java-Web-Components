
public class BorrowedBook {
	private String data;
	private Book book; 
	
	public BorrowedBook(){
		this.data = new String();
		this.book = new Book();
	}
	
	/* metoada inregistreaza o carte imprumutata de un user */
	public void setBookB(String autor,String titlu, String data){
		this.book.setBook(autor, titlu,0, 0);
		this.data = data;
	}
	
	/* returneaza un string cu datele cartii imprumutate */
	public String getBookB(){
		String out = new String();
		out = "Autor: " + book.getAutor() + "Titlu: " + book.getTitlu();
		out += "Data imprumutarii: " + this.data; 
		return out;
	}
}
