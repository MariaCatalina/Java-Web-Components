import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.*;
import javax.servlet.http.*;

public class ServletA extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		/*
		 * din lista favoriteMusic elemente se pot extrage doar daca sunt accesate individual
		String[] favoriteMusic={"Andra","Ozone","Directia 5"};
		request.setAttribute("musicList", favoriteMusic);
		*/
		
		/*
		 * arrayList este afisat complet
		ArrayList<String> favoriteFood = new ArrayList<String>();
		favoriteFood.add("ice cream");
		favoriteFood.add("thai pizza");
		favoriteFood.add("anything in dark chocolate");
		
		request.setAttribute("foodList", favoriteFood);
		*/
		
		HashMap<String,String> musicMap = new HashMap<>();
		musicMap.put("Ambient", "Zero 7");
		musicMap.put("Surf", "Tahiti 80");
		musicMap.put("DJ","TB");
		
		request.setAttribute("musicMap", musicMap);
		
		RequestDispatcher view = request.getRequestDispatcher("/result.jsp");
		view.forward(request, response);
	}
		
}
