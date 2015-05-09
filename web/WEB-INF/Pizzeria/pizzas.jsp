<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/base.css' media='all' />
        <link rel='stylesheet' type='text/css' href='css/pizzas.css' media='all' />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/pizzas.js"></script>
        <title>Pizzeria en ligne</title>
    </head>
    <body>

        <%@ include file="/WEB-INF/header.jsp" %>
        <%@ include file="/WEB-INF/nav.jsp" %>
        <%@ include file="/WEB-INF/intro.jsp" %>
       

        <c:if test="${not empty client}">
            <!-- Ajouter order -->
        </c:if>
        
        <c:set var="pizzas" value="${sessionScope.pizzas}"></c:set>
        <c:set var="toppings" value="${sessionScope.toppings}"></c:set>
            
        <section id="pizza">
        <h2>Listes des pizzas disponibles</h2>
        <c:if test="${not empty pizzas}">
            <c:forEach var="pizza" items="${pizzas}">
                <div class="pizza">
                    <h3 class="pizza-name">${pizza.id}. ${pizza.name} - ${pizza.price}€</h3>
                    <input class="select-pizza" type="radio" value="${pizza.id}" name="pizza" id="pizza-${pizza.id}"/>
                    <br/>
                </div>
            </c:forEach>
        </c:if>   
        </section>
         
        <section id="garniture">
        <h2>Liste des garnitures disponibles</h2>            
        <c:if test="${not empty toppings}">
            <c:forEach var="topping" items="${toppings}">
                <p id="topping-${topping.id}">${topping.id}. ${topping.name} - ${topping.price}€</p>
            </c:forEach>
        </c:if>  
        </section>
        
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>