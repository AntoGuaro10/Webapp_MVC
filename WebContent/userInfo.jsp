<%-- 
    Document   : userInfo
    Created on : 28-mag-2020, 12.28.45
    Author     : anton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User info JSP page</title>
        <link rel="stylesheet" href="style/userStyle.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
        <script src="script/userFuncs.js" type="text/javascript"></script> 
    </head>
    <body>
        <jsp:useBean id="user" type="it.unipa.guarisco.model.User" scope="session" />
        <fieldset hidden="">
            <legend>Your credentials:</legend>
            <label>Username: </label><jsp:getProperty name="user" property="username"/><br/>
            <label>Password: </label><jsp:getProperty name="user" property="password"/><br/>
            <button id="order">Order food and drink</button><br/>
            <button id="check">Check your orders</button>
            <button id="update">Update your credentials</button><br/>
            <a href="./LoadSeats" id="book">Book your place!</a><br/>
            <a href="./FreeSeat" id="release">Leave your seat and exit</a>
        </fieldset>
            
        <div id="updateresult" hidden="">
            <form id="updateform" action="./Update" method="post">
                <label for="oldpssw">Insert old password: </label><input id="oldpssw" type="password" name="oldpssw" /><br/><br/>
                <label for="password">Insert new password: </label><input id="password" type="password" name="password" /><br/>
                <input type="submit" value="Update password!" />
            </form>
        </div>
            
            <div id="ordDiv" hidden="">
                <form id="ordForm" action="./MakeOrder" method="post">
                    <label for="menu">Select what you want: </label><br/>
                    <select id="menu" name="menu">
                        <option value="sandwich">Sandwich with ham and cheese</option>
                        <option value="breadfries">Bread with french fries</option>
                        <option value="fries">Only french fries</option>
                        <option value="coca-cola">Coca cola</option>
                        <option value="coffee">Coffee</option>
                        <option value="water">Water</option>
                    </select>
                    <input id="quantity" name="quantity" type="number" min="0" max="5" />
                    <input style="width: 20px; height: 15px" type="text" name="price" id="price" /> € <br/>                  
                    <input id="submit" type="submit" value="Purchase" hidden=""/>
                </form>
                <button id="showPrice">Click to show the price</button>
            </div>    
            
            <div id="checkDiv" hidden=""></div>
            
            
    </body>
</html>
