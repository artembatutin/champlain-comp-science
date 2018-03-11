<%--
  Created by IntelliJ IDEA.
  User: artembatutin
  Date: 2018-02-21
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$
  <h1>Add Student</h1>
  <form action="AddStudent.jsp" method="get">
      <input type="number" min="0" name="id">
      <input type="text" name="name">
      <input type="number" min="0" max="100" name="grade">
      <input type="submit" value="Add">
  </form>
  <h1>Remove Student</h1>
  <form action="RemoveStudent.jsp" method="get">
    <input type="number" min="0" name="id">
    <input type="submit" value="Add">
  </form>
  </body>
</html>
