<%-- 
    Document   : cashier
    Created on : 22-giu-2020, 17.30.43
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="script/cashier.js" type="text/javascript"></script>
        <link rel="stylesheet" href="style/cashierStyle.css" type="text/css">
        <title>Cashier view</title>
    </head>
    <body>
        <h3>Here you can view what the customers had buy </h3><br/>
        <button id="back"  onclick="logout();" >Click to exit</button>
        <div id="results"></div>
    </body>
</html>
