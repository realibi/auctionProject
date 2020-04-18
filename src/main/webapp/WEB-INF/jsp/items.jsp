<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:directive.include file="header.jsp"/>
<br><br><br>
<div class="title">All items:</div>
<br>
    <div class="row">
    <c:forEach var="item" items="${items}">
        <div class="col-4">
        <c:choose>
            <c:when test="${item.lastUpdatedUserId==userId}">
            <div class="users-item">
                    <div class="row">
                        <div class="col-12">
                            <div class="info">
                                Name: ${item.name}
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="info">
                                Start price: ${item.startPrice}
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="info">
                                Current price: ${item.currentPrice}
                            </div>
                        </div>
                        <br><br><br>
                        <div class="col-12">
                            <div class="info">
                                You offered the highest price
                            </div>
                        </div>
                        <br><br>
                        <div class="col-12"></div>
                    </div>
            </div>
            </c:when>
            <c:otherwise>
            <div class="item">
                <form action="/fs/offerNewPrice" method="post">
                    <div class="row">
                        <div class="col-12">
                            <div class="info">
                                Name: ${item.name}
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="info">
                                Start price: ${item.startPrice}
                            </div>
                        </div>
                        <div class="col-12">
                            <div class="info">
                                Current price: ${item.currentPrice}
                            </div>
                        </div>
                        <br><br><br>
                        <div class="col-12">
                            <div class="answer">
                                <input type="text" name="newPrice" class="answerInput" placeholder="Your price">
                            </div>
                        </div>
                        <br><br>
                        <div class="col-12">
                            <input hidden type="number" name="itemId" value="${item.id}">
                            <button type="submit" name="subjectId" class="button notactive">Send</button>
                        </div>
                    </div>
                </form>
            </div>
            </c:otherwise>
        </c:choose>

        </div>
    </c:forEach>
</div>
<jsp:directive.include file="footer.jsp"/>
