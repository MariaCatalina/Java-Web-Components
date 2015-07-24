package listener;

import javax.servlet.*;

public class MyContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
	
		/* clasa Gestiune pentru retinerea evidentei cartilor  */
	//	model.Gestiune g = new model.Gestiune();
		
		/* clasa DataBorrowedBook pentru retinerea cartilor imprumutate si returnate de client */
	//	model.DataBorrowedBook d = new model.DataBorrowedBook();
		
//		ServletContext ctx = event.getServletContext();
		
//		ctx.setAttribute("gestiune", g);
//		ctx.setAttribute("tableUser",d);
	}

}
