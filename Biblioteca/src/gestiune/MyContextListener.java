package gestiune;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		gestiune.Gestiune g = new gestiune.Gestiune();
		ServletContext ctx = event.getServletContext();
		ctx.setAttribute("gestiune", g);
	}

}
