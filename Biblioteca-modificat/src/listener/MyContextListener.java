package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

public class MyContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		/* clasa Gestiune pentru retinerea evidentei cartilor  */
		model.Gestiune g = new model.Gestiune();
		
		/* clasa DataTable pentru retinerea cartilor imprumutate si returnate de client */
		model.DataTable d = new model.DataTable();
		
		ServletContext ctx = event.getServletContext();
		
		ctx.setAttribute("gestiune", g);
		ctx.setAttribute("tableUser",d);
	}

}
