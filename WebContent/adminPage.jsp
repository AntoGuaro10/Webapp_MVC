<%-- 
    Document   : adminPage
    Created on : 31-mag-2020, 10.20.18
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin JSP page</title>
        <link rel="stylesheet" href="style/adminStyle.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
        <script src="script/admin.js" type="text/javascript"></script>
    </head>
    <body>
        <div>
            <jsp:useBean id="employee" type="it.unipa.guarisco.model.User" scope="session" />
            <fieldset>
                <legend>Welcome back <h4><jsp:getProperty name="employee" property="username"/></h4> </legend>
                <label for="add">Click for add a new employee </label><button id="add">Add</button>
                <button id="showall">Show all Employees</button>
                <button id="back" onclick="exit()">Logout</button>
            </fieldset>
                
                <div id="result" hidden="">
                    <form id="addform" method="post" action="./AddEmployee">
                        <label for="username">Username: </label><input id="username" type="email" name="username" /><br/>
                        <label for="pass">Password: </label><input id="pass" type="password" name="pass" /><br/>
                        <select id="type" name="type">
                            <option value="cook">Cook</option>
                            <option value="lifeguard">Lifeguard</option>
                            <option value="cashier">Cashier</option>
                        </select>
                        <input type="submit" value="Submit" />
                    </form>
                </div>    
        </div>
                
                <div id="show">
                    
                </div>
    </body>
</html>
