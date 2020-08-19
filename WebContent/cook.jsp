<%-- 
    Document   : cook
    Created on : 22-giu-2020, 17.30.50
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/cookStyle.css" />
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="script/cook.js" type="text/javascript"></script>
        <title>Cook view JSP page</title>
    </head>
    <body>
        <h3>Here you can view the orders, cook them and then set their status on "Complete"</h3><br/>
        <button id="back" onclick="exit();">Click to exit</button>
        <div id="results"></div>
    </body>
</html>
