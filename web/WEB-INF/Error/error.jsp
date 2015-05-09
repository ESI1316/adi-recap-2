<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/base.css' media='all' />
        <link rel='stylesheet' type='text/css' href='css/error.css' media='all' />
        <title>Pizzeria en ligne</title>
    </head>
    <body>

        <%@ include file="/WEB-INF/header.jsp" %>
        <%@ include file="/WEB-INF/nav.jsp" %>
        <%@ include file="/WEB-INF/intro.jsp" %>
        
          <h2>Erreur !</h2>
          <p class="error-message">
                <c:out value="${requestScope.error}" />
          </p>

        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>