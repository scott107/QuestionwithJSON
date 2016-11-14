package mvc.controllers;
import mvc.DBLayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.Question;
import mvc.models.QuestionsModel;

@WebServlet("/Questions")
public class QuestionController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("controller");
		
		String Qnum = request.getParameter("Qnum");
		RequestDispatcher rd = null;
		QuestionsModel result;
		if (Integer.parseInt(Qnum) < 0){
			result = new QuestionsModel();

		}
		else{

			result = new QuestionsModel();
		}
		
		rd = request.getRequestDispatcher("/views/index.jsp");
		request.setAttribute("viewModel", result);
		rd.forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("got the question");
	    response.setContentType("text/html");
	    
	    PrintWriter out = response.getWriter();
	    System.out.println("about to get the question data");
        String theQuestion = request.getParameter("theQuestion");
		System.out.println(theQuestion);
		Question tempQ = new Question (0, theQuestion);

		RequestDispatcher rd = null;
		QuestionsModel result;
		
		
		
		
		//QuestionsModel result;
		result = new QuestionsModel();
		String JSON;
		System.out.println("JSON");
		JSON = "{\"questions\": [";
		for (int i = 0; i < result.quest.size(); i++){
			JSON += "{\"question\":\"" + result.quest.get(i).question + "\"}";
			if (i < result.quest.size()-1){
				JSON += ",";
			}
		}
		System.out.println(JSON);
		System.out.println("JSON");
		
		
		
		
		
		
		
		
		result = new QuestionsModel();
		result.AddQuestion(tempQ);
		rd = request.getRequestDispatcher("/views/index.jsp");
		request.setAttribute("viewModel", result);
		rd.forward(request, response);
		
		
	}
}
