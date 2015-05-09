<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='./css/base.css' media='all' />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <title>Pizzeria en ligne</title>
    </head>
    <body>

        <%@ include file="/WEB-INF/header.jsp" %>
        <%@ include file="/WEB-INF/nav.jsp" %>
        <%@ include file="/WEB-INF/intro.jsp" %>
        
        <c:choose>
            <c:when test="${not empty sessionScope.client}">
                <h2>Historique des commandes</h2>
                <c:set var="orders" value="${requestScope.historique}"></c:set>
                <%@ include file="/WEB-INF/displayOrders.jsp" %>
            </c:when>
            <c:otherwise>
                <h2>Vous n'êtes pas connecté !</h2>
            </c:otherwise>
        </c:choose>
       
       
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>