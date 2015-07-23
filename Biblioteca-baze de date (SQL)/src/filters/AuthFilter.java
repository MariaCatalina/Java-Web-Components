package filters;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AuthFilter implements Filter{

	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		/* filtreaza fiecare request si adauga datele despre logare */
		
		HttpServletRequest request = (HttpServletRequest) req;
    	
    	if (request.isUserInRole("bibliotecar")) { 
   
            request.setAttribute("role","bibliotecar");
    		request.setAttribute("email",request.getUserPrincipal().getName() );
    
    	}
    	else if((request.isUserInRole("client"))){
    		
    		request.setAttribute("role","client");
    		request.setAttribute("email",request.getUserPrincipal().getName() );
    
    	}
        
		chain.doFilter(req, res);
	
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
