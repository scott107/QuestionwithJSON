package mvc;

import java.io.ByteArrayInputStream;
import mvc.Question;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DBLayer{
    private Connection con;
    private Statement stmt;
    
    public DBLayer() throws SQLException{
    	System.out.println("DBcreate");
        String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/slnz8b";
        String userID = "slnz8b";
        String password = "tZrFLVzffV";
   
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(java.lang.ClassNotFoundException e) {
            System.out.println(e);
            System.exit(0);
        }
       
        con = DriverManager.getConnection(url,userID,password);
        stmt = con.createStatement();
        try {
        	ResultSet tempRS = stmt.executeQuery("SELECT question FROM questions;");
        	tempRS.next();
        }
        // table is not present, make it
        catch (SQLException e){
            String sqlCmd = "create table Questions (question_id INT NOT NULL AUTO_INCREMENT, question VARCHAR(264), PRIMARY KEY (question_id));";
            stmt.executeUpdate(sqlCmd);
        }
        try {
        	ResultSet tempRS = stmt.executeQuery("SELECT answer FROM answers;");
        	tempRS.next();
        }
        // table is not present, make it
        catch (SQLException e){
            String sqlCmd = "create table Answers (answer_id INT NOT NULL AUTO_INCREMENT, question_id_fk INT NOT NULL, answer VARCHAR(264), PRIMARY KEY (answer_id));";
            stmt.executeUpdate(sqlCmd);
        }
    }
    
    public ArrayList<Question> getQData(){
    	System.out.println("DBGet");
    	ResultSet tempRS = null;
		ArrayList arr = new ArrayList();
		String id = null;
		String question = null;
    	String sqlCmd = "select * from questions;";
    	try {
			tempRS = stmt.executeQuery(sqlCmd);	

			java.sql.ResultSetMetaData rsmd = tempRS.getMetaData();
			int numberColumns = rsmd.getColumnCount();
			boolean next = tempRS.next();

			while (next) {
				int j = 1;
				for (int i = 1; i <= numberColumns; i++) {

					// integer column
					if (i == 1) {
						id = tempRS.getString(i);
					} else if ( i == 2)
						question = tempRS.getString(i);
				}
				j++;
				Question tempQ = new Question(Integer.parseInt(id), question);
				arr.add(tempQ);
				next = tempRS.next();
			}

		} catch (SQLException err) {
			JOptionPane.showMessageDialog(null, err.toString(), "Database error.", JOptionPane.ERROR_MESSAGE);
		}
		return arr;
    }
    public void addQ(Question newq){
    	String sqlCmd = "INSERT into Questions (question) VALUES(\""+newq.question+"\");";
    	try {
			stmt.executeUpdate(sqlCmd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public List getAnswers(int id){
    	String sqlCmd = "select answer from Answers where question_id_fk="+id+";";
		ArrayList arr = new ArrayList();
    	try {
			stmt.executeQuery(sqlCmd);
			ResultSet tempRS = null;
				tempRS = stmt.executeQuery(sqlCmd);	

				java.sql.ResultSetMetaData rsmd = tempRS.getMetaData();
				int numberColumns = rsmd.getColumnCount();
				boolean next = tempRS.next();

				while (next) {
					int j = 1;
					String answer = tempRS.getString(1);
					j++;

					arr.add(answer);
					next = tempRS.next();
				}

			} catch (SQLException err) {
				JOptionPane.showMessageDialog(null, err.toString(), "Database error.", JOptionPane.ERROR_MESSAGE);
			}
			return arr;
    }
    public void addAnswerToDb(String answer, int id){
    	String sqlCmd = "INSERT into Answers (question_id_fk,answer) VALUES("+id+",\""+answer+"\");";
    	try {
			stmt.executeUpdate(sqlCmd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
	
