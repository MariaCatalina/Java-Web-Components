package filters;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

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
		
		services.ClientService cService = new ClientService();

		/* acceseaza vechile date din memorie */
		model.User user = cService.getUserData(email);
		
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

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
