<%@ page  import="java.util.List" import="mvc.models.AnswerModel" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answer to Question </title>
</head>
<h2>Answers to Question</h2>
<body>


<%
AnswerModel viewModel = (AnswerModel)request.getAttribute("viewModel");
int id = 0;
if (viewModel != null) {
	List answers =viewModel.answers;
	id = viewModel.id;
	String answer;
	for (int i = 0; i < answers.size(); i++){
		answer = (String)answers.get(i);
		out.print("<p>" + id + "." + answer + "</p>");        
	}
}

        out.println("<form method=\"POST\" action=\"Answers\">");
        out.println("<p><input type=\"text\" name=\"theAnswer\" size=\"50\"> <input type=\"submit\" value=\"Add Answer\"></p>");

        out.println("</form>");
%>
</body>
</html>