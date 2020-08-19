<%-- 
    Document   : seatinfo2
    Created on : 24-giu-2020, 17.47.20
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seat info JSP Page</title>
        <script>
            function goback(){
                location.replace('./lifeguard.jsp');
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="info" type="it.unipa.guarisco.model.Seat" scope="request" />
        <fieldset style="width: 180px">
            <label>Seat ID: </label> <jsp:getProperty name="info" property="id" /><br/>
            <label>Client ID: </label> <jsp:getProperty name="info" property="NA" /><br/>
            <label>Position X: </label> <jsp:getProperty name="info" property="posX" /><br/>
            <label>Position Y: </label> <jsp:getProperty name="info" property="posY" /><br/>
            <label>Available: </label> <jsp:getProperty name="info" property="available" />
        </fieldset><br/>
        <button onclick="goback()" id="back">Go back to seat overview page</button>
    </body>
</html>
