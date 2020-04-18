<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:bundle basename="resources">
    <fmt:message key="allItems" var="allItems"/>
    <fmt:message key="auction" var="auction"/>
    <fmt:message key="main" var="main"/>
    <fmt:message key="myItems" var="myItems"/>
</fmt:bundle>
<html>
<head>
    <script   src="https://code.jquery.com/jquery-3.4.1.min.js"   integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="   crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css?family=Kelly+Slab|Russo+One&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Rubik+Mono+One&display=swap" rel="stylesheet">
</head>
<style>
    <jsp:directive.include file="css/style.css"/>
</style>
<body>
<div class="header">
    <div class="row">
        <div class="col-md-3">
            <div class="menu-item">
                <a href="/fs/main">${auction}</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="menu-item">
                <a href="/fs/main">${main}</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="menu-item">
                <a href="/fs/items">${allItems}</a>
            </div>
        </div>
        <div class="col-md-3">
            <div class="menu-item">
                <a href="/fs/manageItems">${myItems}</a>
            </div>
        </div>
    </div>
</div>

<br><br><br>
<a href="/fs/changeLanguage?language=ru">Русский</a><br>
<a href="/fs/changeLanguage?language=en">English</a><br>
<br><BR><br>

<div class="container">