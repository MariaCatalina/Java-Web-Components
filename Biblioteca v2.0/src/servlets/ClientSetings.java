package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.ClientService;

public class ClientSetings extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		String email = (String) request.getAttribute("email");
		String firstName = (String) request.getParameter("FirstNameC");
		String lastName = (String ) request.getParameter("LastNameC");
		System.out.println(email);

		ClientService cService = new ClientService();

		/* acceseaza vechile date din memorie */
		model.User user = cService.getUserData(email);

		System.out.println(user.getFirstName()  + " " + user.getLastName());
		
		/* daca clientul vrea sa modifice doar un parte din nume */
		if(firstName.isEmpty()){
			
			if( !lastName.isEmpty() )
				cService.modifyUserData(email,user.getFirstName(), lastName);
		}
		else
			if(lastName.isEmpty()){
				
				if( !firstName.isEmpty() ){
					cService.modifyUserData(email,firstName, user.getLastName());
					
				}
			}
			else if( !firstName.isEmpty() && !lastName.isEmpty()){
					cService.modifyUserData(email,firstName, lastName);
			}

		request.setAttribute("userData", user);

		RequestDispatcher view = request.getRequestDispatcher("/pageJSP.jsp");
		view.forward(request, response);

	}
}
