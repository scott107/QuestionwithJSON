<%@ page import="java.util.List"  import="mvc.models.QuestionsModel" import="mvc.Question" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Questions</title>
</head>
<body>
<h2>Questions</h2>


<%
		QuestionsModel viewModel = (QuestionsModel)request.getAttribute("viewModel");
		// call controller
		if (viewModel == null){
			//I don't know how to transition to the controller without user input, so for now, enter a negative number in the box to proceed. %>
		    <form action="/MVC_app/Questions" method="get">
				Age : <input type="text" name="Qnum"> <BR>
				<input type="submit" value="Calculate"/>
			</form>

		<p><%
		}
        if (viewModel != null) {
        	Question tempQ;
			List questions =viewModel.quest;
			for (int i = 0; i < questions.size(); i++){
				tempQ = (Question)questions.get(i);
				int id = tempQ.id;
				String text = tempQ.question;
				out.print("<p>" + id + ". <a href=\"Answers?id="+id+"\">" + text + "</a></p>");	        
			}
        }
		
        out.println("<form method=\"POST\" action=\"Questions\">");
        out.println("<p><input type=\"text\" name=\"theQuestion\" size=\"50\"> <input type=\"submit\" value=\"Add Question\"></p>");

        out.println("</form>");
%>
    </p>
</body>
</html>