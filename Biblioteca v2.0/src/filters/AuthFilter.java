package filters;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;

import model.User;
import services.ClientService;

public class AuthFilter implements Filter{


	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		/* filtreaza fiecare request si adauga datele despre logare */

		HttpServletRequest request = (HttpServletRequest) req;

		String email = request.getUserPrincipal().getName();

		ClientService cService = new ClientService();

		/* acceseaza vechile date din memorie */
		User user;
		RequestDispatcher view;
		try {
			user = cService.getUserData(email);


			if (request.isUserInRole("bibliotecar")) { 

				request.setAttribute("role","bibliotecar");
				request.setAttribute("email",email );

			}
			else if((request.isUserInRole("client"))){

				request.setAttribute("role","client");
				request.setAttribute("email",email);

			}

			request.setAttribute("userName", user);
			chain.doFilter(req, res);
		}
		catch (ClassNotFoundException | SQLException e) {
			view = request.getRequestDispatcher("/pageFormatJSP/catchErrors.jsp");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
