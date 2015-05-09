<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/base.css' media='all' />
        <link rel='stylesheet' type='text/css' href='css/form.css' media='all' />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <title>Pizzeria en ligne</title>
    </head>
    <body>

        <%@ include file="/WEB-INF/header.jsp" %>
        <%@ include file="/WEB-INF/nav.jsp" %>
        <%@ include file="/WEB-INF/intro.jsp" %>
        
        
        
        <section id="form-section" >
            <h3>Création d'un nouveau compte</h3>
            <form id="form-nouveau" action="Pizzeria">
                <label for="nom">Nom</label>
                <input type="text" name="nom" />
                <br/>
                
                <label for="email">E-mail</label>
                <input type="email" name="email" />
                <br/>
                
                <label for="adresse">Adresse</label>
                <input type="text" name="adresse"/>
                <br/>
                
                <input type="hidden" name="action" value="newClient"/>
                <input class="submit" type="submit" name="submit"/>
                
                
            </form>
        </section>

        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>