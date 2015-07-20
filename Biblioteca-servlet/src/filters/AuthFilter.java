package filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class AuthFilter implements Filter{
	
	private FilterConfig fc;
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		/* filtreaza fiecare request si adauga datele despre logare */
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		
    	
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
		this.fc = config;	
	}

}
