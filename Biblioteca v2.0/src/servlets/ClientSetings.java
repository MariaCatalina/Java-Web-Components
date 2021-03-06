package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.User;
import services.ClientService;

public class ClientSetings extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		String email = (String) request.getAttribute("email");
		String firstName = (String) request.getParameter("FirstNameC");
		String lastName = (String ) request.getParameter("LastNameC");

		ClientService cService = new ClientService();

		/* acceseaza vechile date din memorie */
		User user;
		RequestDispatcher view;
		try {
			user = cService.getUserData(email);
			
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
			view = request.getRequestDispatcher("/pageFormatJSP/pageJSP.jsp");

		} catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}

		view.forward(request, response);

	}
}
