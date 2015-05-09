<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>
    <div id="connexion" style="text-align:right;">	
        
        <c:choose>
            <c:when test="${not empty sessionScope.client}">
                <c:set var='client' value='${sessionScope.client}'></c:set>
                Connecté sous ${client.clientName} (ID : ${client.clientId}) | Email :  ${client.email} | ${client.address}
                <a href="Pizzeria?action=disconnect">D&eacute;connexion</a>
            </c:when>
            <c:otherwise>
                <a href="Pizzeria?cible=login">Identifiez-vous</a>  |
                <a href="Pizzeria?cible=nouveau">Nouveau client</a>
            </c:otherwise>
            
        </c:choose>
        
    </div>
    <h1>PIZZA &Agrave; LA CARTE !</h1>
</header>
