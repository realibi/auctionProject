<%--
  Created by IntelliJ IDEA.
  User: realibi
  Date: 4/6/20
  Time: 8:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage your items</title>
</head>
<body>
    <h1>Manage your items</h1>

    <h3>Add new item</h3>
    <form method="post" action="/fs/addItem">
        <p>Название товара:</p>
        <input type="text" name="name">
        <p>Стартовая цена:</p>
        <input type="number" name="startPrice">
        <p>Длительность аукциона:</p>
        <input type="number" name="daysOffset" placeholder="Количество дней">

        <p>Category:</p>
        <select name="categoryId">
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>

        <input hidden type="number" value="${userId}">
        <p><button type="submit">Готово</button></p>
    </form>
    <br>
    <hr>
    <br>
    <h3>My items:</h3>
    <c:forEach var="item" items="${userItems}">
        <form method="post" action="/fs/deleteItem">
            <h3>Название: ${item.name}</h3>
            <c:choose>
                <c:when test="${item.endTime>currentDate}">
                    <h5>На аукционе</h5>
                </c:when>
                <c:otherwise>
                    <h5>Не на аукционе</h5>
                </c:otherwise>
            </c:choose>
            <input hidden type="number" name="id" value="${item.id}">
            <button type="submit">Удалить</button>
        </form>
    </c:forEach>
    <br>
    <hr>
    <br>
    <h3>Your last five purchases:</h3>
    <c:forEach var="sale" items="${userSales}">
        <h3>${sale.id}</h3>
    </c:forEach>
</body>
</html>
