package model;

import java.util.ArrayList;

public class AutorsListClass {
	
	private ArrayList<model.Author> authorList;
	
	public AutorsListClass(){
		authorList = new ArrayList<>();
	}
	
	public void addAuthor(model.Author a){
		authorList.add(a);
	}
	
	public ArrayList<model.Author> getAuthorList(){
		return authorList;
	}
}
