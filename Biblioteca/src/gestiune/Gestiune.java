package gestiune;
 
import java.util.ArrayList;

public class Gestiune {
	
	ArrayList<MyBook> books;
	
	public Gestiune(){
		books = new ArrayList<MyBook>();
	}
	
	public void addB(MyBook b){
		books.add(b);
	}
	
	public String print(){
		String s = new String();
		for (MyBook b:books)
			s+= b.getBook();
		return s;
	}
}
