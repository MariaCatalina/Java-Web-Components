package gestiune;

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
		gestiune.Gestiune g = new gestiune.Gestiune();
		/* clasa DataTable pentru retinerea cartilor imprumutate si returnate de client */
		gestiune.DataTable d = new gestiune.DataTable();
		/* clasa ReturnBooks pentru cartile returnate */
		gestiune.ReturnBooks r = new gestiune.ReturnBooks();
		
		ServletContext ctx = event.getServletContext();
	//	HttpSession s = request.getSession(true);
		
		ctx.setAttribute("gestiune", g);
		ctx.setAttribute("tableUser",d);
		ctx.setAttribute("return", r);
	}

}
