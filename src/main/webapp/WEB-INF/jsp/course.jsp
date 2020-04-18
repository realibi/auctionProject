<%--
  Created by IntelliJ IDEA.
  User: MI
  Date: 19.03.2020
  Time: 20:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Course</title>
</head>
<body>
<p>Course</p>
<p>Title: ${courseName}</p>
<p>description: ${courseDescription}</p>
<p>price: ${coursePrice}tg.</p>
<p>startDate: ${courseStartDate}</p>
<form action="/fs/deleteCourse" method="post">
    <input type="hidden" value="${courseId}" name="courseId" id="courseId">
    <button type="submit">Delete</button>
</form>
</body>
</html>
