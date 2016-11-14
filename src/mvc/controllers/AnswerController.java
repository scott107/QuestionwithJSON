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
import mvc.models.AnswerModel;
import mvc.models.QuestionsModel;

@WebServlet("/Answers")
public class AnswerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AnswerModel result;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Get");
		
		String Qnum = request.getParameter("id");
		RequestDispatcher rd = null;
		
		result = new AnswerModel(Integer.parseInt(Qnum));
		rd = request.getRequestDispatcher("/views/answers.jsp");
		request.setAttribute("viewModel", result);
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post");
	    response.setContentType("text/html");
	    
	    PrintWriter out = response.getWriter();
	    System.out.println("about to get the answer data");
        String theAnswer = request.getParameter("theAnswer");
        
        int id = result.id;

		RequestDispatcher rd = null;

		
		result = new AnswerModel(id);
		result.addAnswer(theAnswer);
		rd = request.getRequestDispatcher("/views/answers.jsp");
		request.setAttribute("viewModel", result);
		rd.forward(request, response);
		
		
	}
}