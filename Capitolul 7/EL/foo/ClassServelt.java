package foo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClassServelt extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Person p = new Person();
		p.setName("LeeLU");
		
		Dog d = new Dog();
		d.setName("Clyde");
		
		Toy t1 = new Toy();
		t1.setName("stick");
		
		Toy t2 = new Toy();
		t2.setName("neighbor's cat");
		
		Toy t3 = new Toy();
		t3.setName("Barbie doll head");
		
		d.setToys(new Toy[]{t1,t2,t3});
		p.setDog(d);
		
		request.setAttribute("person", p);
		RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
		view.forward(request, response);
	}
}
