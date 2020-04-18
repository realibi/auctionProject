<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 19.03.2020
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Courses</title>
</head>
<body>
<h2>Courses</h2>
<c:forEach items="${courses}" var="course">
    Title: <p>${course.title}</p>
    description: <p>${course.description}</p>
    <a href="/fs/course?courseId=${course.id}">More information</a>
    <p>------------------------------------------------</p><br>
</c:forEach>
</body>
</html>
