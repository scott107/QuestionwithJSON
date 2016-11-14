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


public class AnswerModel {
	
	public List<String> answers;
	public int id;
	private DBLayer database;
	
	public AnswerModel(int qnumber) {
		id = qnumber;
		try {
			database = new DBLayer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		answers = database.getAnswers(qnumber);
	}
	public void addAnswer(String answer){
		database.addAnswerToDb(answer, id);
	}
}