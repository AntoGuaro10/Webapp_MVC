<%-- 
    Document   : lifeguard
    Created on : 22-giu-2020, 17.30.33
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="script/lifeguard.js" type="text/javascript"></script>
        <link rel="stylesheet" href="style/seatStyle.css" type="text/css">
        <title>Lifeguard view</title>
    </head>
    <body>
        <jsp:useBean id="employee" type="it.unipa.guarisco.model.User" scope="session" />
        <h3>Welcome back <jsp:getProperty name="employee" property="username"/> !</h3>
        <div id="desc">Here you can see the situation of the places available and those already occupied</div><br/>
        <button id="back" onclick="back();">Click to exit</button>
        <div id="result">
            
        </div>
    </body>
</html>
