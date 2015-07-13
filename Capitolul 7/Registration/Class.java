import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Class extends HttpServlet{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
		
		/* Prima varianta
		String name = request.getParameter("username");
		request.setAttribute("name","Catalina");
		*/
		
		/* Clasa Person => o adresa de memorie
		 */
		foo.Employee p = new foo.Employee();
		p.setName("Catalina");
		p.setEmpID(10);
		
		
		/* adaugare clasa Dog */
		
//		foo.Dog dog = new foo.Dog();
//		dog.setName("Spike");
//		p.setDog(dog);
//		
		//System.out.println(dog.getName());
		
		request.setAttribute("person", p);
		RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
		view.forward(request, response);
	}
}
