<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<c:choose>
    <c:when test="${not empty orders}">

        <c:forEach var="order" items="${orders}">
            <h3>${order.id} : ${order.date}</h3>

            <c:set var="pizzas" value="${order.pizzaOrders}"></c:set>
            <c:if test="${not empty pizzas}">
                <c:forEach var="pizza" items="${pizzas}">
                    <div class="pizza">
                        <h4 class="pizza-name">${pizza.num} 
                            : ${pizza.quantity} ${pizza.name} à ${pizza.price}€ chacunes</h4>
                        <br/>

                        <c:set var="toppings" value="${pizza.toppings}"></c:set>
                        <c:if test="${not empty toppings}">
                            <h5>Garnitures</h5>
                            <c:forEach var="topping" items="${toppings}">
                                <c:forEach var="topping" items="${toppings}">
                                    <p id="topping-${topping.id}">${topping.id}. ${topping.name} - ${topping.price}€</p>
                                </c:forEach>
                            </c:forEach>

                        </c:if>
                    </div>
                    <div>
                       <h4>Prix total : ${order.totalAmount} </h4>
                       <c:if test="${not empty boolOrder}">
                           <button <button onclick="location.href='FrontController?action=endCommand'">
                                    class="deliver">Livraison</button>
                       </c:if>
                    </div>
                </c:forEach>
            </c:if>

        </c:forEach>
    </c:when>

    <c:otherwise>
        <h3>Aucune commandes</h3>
    </c:otherwise>
</c:choose>

      