package com.example;

import javax.servlet.*;
public class MyServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event){
		System.out.println("a intrat in contexListener");
		/* ask the event for the ServletContext */
		ServletContext sc = event.getServletContext();
		/* use the context to get the init parameter */
		String dogBreed = sc.getInitParameter("breed");
		System.out.println("breed -> "+ dogBreed);
		Dog d = new Dog(dogBreed);
		/* set the attribute */
		sc.setAttribute("dog",d);
	}
	public void contextDestroyed(ServletContextEvent event){
		/* nothing to do here */
	}
}