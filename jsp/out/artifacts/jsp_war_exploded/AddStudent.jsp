<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="student.Student,student.StudentIO" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<%

    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String grade = request.getParameter("grade");

    if(id == null || name == null || grade == null) {
    	System.out.println("Error");
        return;
    }

    if(id.length() < 1 || name.length() < 1 || grade.length() < 1) {
        System.out.println("Not enough length");
        return;
    }

    try {
        StudentIO.addStudent(Student.parse(id, name, grade));
        out.print("Added student " + id + " with name " + name + " - grade: " + grade);
    } catch(Exception e) {
        e.printStackTrace();
    }


%>

</body>
</html>
