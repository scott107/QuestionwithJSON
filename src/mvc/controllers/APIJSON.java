package mvc.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.models.QuestionsModel;

@WebServlet("/APIJSON")
public class APIJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public APIJSON() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     response.setContentType("application/json");
	        // Get the printwriter object from response to write the required json object to the output stream 
	        PrintWriter out = response.getWriter();

	        
	        
			RequestDispatcher rd = null;
			QuestionsModel result;
			result = new QuestionsModel();
			String JSON;
			
			String temp = "{\"questions\": [{\"question\":\"First question\"},{\"question\":\"Second question\"}]}";
			JSON = "{\"questions\": [";
			for (int i = 0; i < result.quest.size(); i++){
				JSON += "{\"question\":\"" + result.quest.get(i).question + "\"}";
				if (i < result.quest.size()-1){
					JSON += ",";
				}
			}
			JSON+="]}";
			System.out.println(JSON);
	        out.print(JSON);
	        out.flush();
	}
}
