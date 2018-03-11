<%@ page import="student.StudentIO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="student.Student" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Students</title>
  </head>
  <body>
  <h1>All Students</h1>
  <%
      ArrayList<Student> students = StudentIO.decode();
      for(Student s : students) {
      	out.print(s.toString() + " </br>");
      }
  %>
  <h1>Add Student</h1>
  <form action="AddStudent.jsp" method="post">
      <input type="number" min="0" name="id">
      <input type="text" name="name">
      <input type="number" min="0" max="100" name="grade">
      <input type="submit" value="Add">
  </form>
  <h1>Remove Student</h1>
  <form action="RemoveStudent.jsp" method="post">
    <input type="number" min="0" name="id">
    <input type="submit" value="Remove">
  </form>
  </body>
</html>
