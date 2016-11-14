package mvc.models;

import mvc.DBLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc.Question;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QuestionsModel {
	
	public List<Question> quest;
	
	private DBLayer database;
	
	public QuestionsModel(int qnumber) {
		/*
		quest = new ArrayList<Question>();
		quest = database.getAnswers(qnumber);
		*/
    }
	public QuestionsModel(){
		try {
			database = new DBLayer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("model");
		quest = new ArrayList<Question>();
		// call db
		quest = database.getQData();	
	}
	public void AddQuestion(Question newQ){
		
		database.addQ(newQ);
	}
}
