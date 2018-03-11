<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="student.Student,student.StudentIO" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<%

    String id = request.getParameter("id");

    if(id == null) {
    	System.out.println("Error");
        return;
    }

    if(id.length() < 1) {
        System.out.println("Not enough length");
        return;
    }

    try {
        StudentIO.removeStudent(Integer.parseInt(id), out);
    } catch(Exception e) {
        e.printStackTrace();
    }


%>

</body>
</html>
